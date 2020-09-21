package com.mk.learn.nio.qqchat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * @program: learning-demo
 * @description:
 * @author: mk_passby
 * @create: 2020-09-21 22:31
 **/
public class GroupChatClient {
    //服务器地址
    private final String HOST = "127.0.0.1";
    //服务器端口
    private int PORT = 6667;
    private Selector selector;
    private SocketChannel socketChannel;
    private String userName;

    public GroupChatClient() throws IOException {
        //得到选择器
        selector = Selector.open();
        //连接远程服务器
        socketChannel = SocketChannel.open(new InetSocketAddress(HOST, PORT));
        //设置非阻塞
        socketChannel.configureBlocking(false);
        //注册选择器并设置为 read
        socketChannel.register(selector, SelectionKey.OP_READ);
        //得到客户端 IP 地址和端口信息，作为聊天用户名使用
        userName = socketChannel.getLocalAddress().toString().substring(1);
        System.out.println(userName + " is ok ~");
    }

    //向服务器端发送数据
    public void sendInfo(String info) throws Exception {
        //如果控制台输入 exit 就关闭通道，结束聊天
        if (info.equalsIgnoreCase("exit")) {
            socketChannel.write(ByteBuffer.wrap(info.getBytes()));
            socketChannel.close();
            socketChannel = null;
            return;
        }
        info = userName + " 说: " + info;
        try {
            //往通道中写数据
            socketChannel.write(ByteBuffer.wrap(info.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //从服务器端接收数据
    public void readInfo() {
        try {
            int readyChannels = selector.select();
            //有可用通道
            if (readyChannels > 0) {
                Set selectedKeys = selector.selectedKeys();
                Iterator keyIterator = selectedKeys.iterator();
                while (keyIterator.hasNext()) {
                    SelectionKey sk = (SelectionKey) keyIterator.next();
                    if (sk.isReadable()) {
                        //得到关联的通道
                        SocketChannel sc = (SocketChannel) sk.channel();
                        //得到一个缓冲区
                        ByteBuffer buff = ByteBuffer.allocate(1024);
                        //读取数据并存储到缓冲区
                        sc.read(buff);
                        //把缓冲区数据转换成字符串
                        String msg = new String(buff.array());
                        System.out.println(msg.trim());
                    }
                    keyIterator.remove(); //删除当前 SelectionKey，防止重复处理
                }
            } else {
                //会检测到没有可用的channel ，可以退出
                System.out.println("没有可用channel ...");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception  {
        //创建一个聊天客户端对象
        GroupChatClient chatClient = new GroupChatClient();
        new Thread() { //单独开一个线程不断的接收服务器端广播的数据
            public void run() {
                while (true) {
                    chatClient.readInfo();
                    try { //间隔 3 秒
                        Thread.currentThread().sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        Scanner scanner = new Scanner(System.in);
        //在控制台输入数据并发送到服务器端
        while (scanner.hasNextLine()) {
            String msg = scanner.nextLine();
            chatClient.sendInfo(msg.trim());
        }
    }
}
