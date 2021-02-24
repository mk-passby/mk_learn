package com.mk.learn.nio.qqchat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.text.SimpleDateFormat;
import java.util.Iterator;

/**
 * @program: learning-demo
 * @description:
 * @author: mk_passby
 * @create: 2020-09-21 22:30
 **/
public class GroupChatServer {
    private Selector selector;
    private ServerSocketChannel listenerChannel;
    private static final int PORT = 6667; //服务器端口

    public GroupChatServer() {
        try {
            // 得到选择器
            selector = Selector.open();
            // 打开监听通道
            listenerChannel = ServerSocketChannel.open();
            // 绑定端口
            listenerChannel.socket().bind(new InetSocketAddress(PORT));
            // 设置为非阻塞模式
            listenerChannel.configureBlocking(false);
            // 将选择器绑定到监听通道并监听 accept 事件
            listenerChannel.register(selector, SelectionKey.OP_ACCEPT);
            printInfo("服务器 ok.......");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listen() {
        try {
            while (true) { //不停轮询
                int count = selector.select();//获取就绪 channel
                if (count > 0) {
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        // 监听到 accept
                        if (key.isAcceptable()) {
                            SocketChannel sc = listenerChannel.accept();
                            //非阻塞模式
                            sc.configureBlocking(false);
                            //注册到选择器上并监听 read
                            sc.register(selector, SelectionKey.OP_READ);

                            //System.out.println(sc.getRemoteAddress().toString().substring(1) + "online ...");
                            System.out.println(sc.socket().getRemoteSocketAddress().toString().substring(1) + " 上线 ...");
                            //将此对应的 channel 设置为 accept,接着准备接受其他客户端请求
                            key.interestOps(SelectionKey.OP_ACCEPT);
                        }
                        //监听到 read
                        if (key.isReadable()) {
                            readData(key); //读取客户端发来的数据
                        }
                        //一定要把当前 key 删掉，防止重复处理
                        iterator.remove();
                    }
                } else {
                    System.out.println("waitting ...");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readData(SelectionKey key) {
        SocketChannel channel = null;
        try {
            // 得到关联的通道
            channel = (SocketChannel) key.channel();
            //设置 buffer 缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            //从通道中读取数据并存储到缓冲区中
            int count = channel.read(buffer);
            //如果读取到了数据
            if (count > 0) {
                //把缓冲区数据转换为字符串
                String msg = new String(buffer.array());

                printInfo(msg);
                //将关联的 channel 设置为 read，继续准备接受数据
                key.interestOps(SelectionKey.OP_READ);
                sendInfoToOtherClients(channel, msg); //向所有客户端广播数据
            }
            buffer.clear();
        } catch (IOException e) {
            try {
                //当客户端关闭 channel 时，进行异常如理
                //printInfo(channel.getRemoteAddress().toString().substring(1) + "offline...");
                printInfo(channel.socket().getRemoteSocketAddress().toString().substring(1) + " 离线了 ...");
                key.cancel(); //取消注册
                channel.close(); //关闭通道
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    public void sendInfoToOtherClients(SocketChannel except, String msg) throws IOException {
        System.out.println("服务器进行消息转发 ...");
        //转发数据到所有的 SocketChannel 中
        for (SelectionKey key : selector.keys()) {
            Channel targetchannel = key.channel();
            //排除自身
            if (targetchannel instanceof SocketChannel && targetchannel != except) {
                SocketChannel dest = (SocketChannel) targetchannel;
                //把数据存储到缓冲区中
                ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
                //往通道中写数据
                dest.write(buffer);
            }
        }
    }

    private void printInfo(String str) { //显示消息
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("服务器接收到消息 时间: [" + sdf.format(new java.util.Date()) + "] -> " + str);
    }

    public static void main(String[] args) {
        GroupChatServer server = new GroupChatServer();
        server.listen();
    }
}
