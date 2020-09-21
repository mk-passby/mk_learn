package com.mk.learn.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @program: learning-demo
 * @description:服务端
 * @author: mk_passby
 * @create: 2020-09-21 22:10
 **/
public class NIOServer {

    public static void main(String[] args) throws IOException {
        //1.得到一个serverSocketChannal
        ServerSocketChannel serverSocketChannel=ServerSocketChannel.open();
        //2.得到一个selector
        Selector selector = Selector.open();
        //3.绑定一个端口号
        //serverSocketChannel.bind(new InetSocketAddress(6666));
        serverSocketChannel.socket().bind(new InetSocketAddress(6666));
        //4.设置非阻塞模式
        serverSocketChannel.configureBlocking(false);
        //5.把serverSocketChannel对象注册给selector
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        //6.干活
        while (true){
            //6.1 监听客户端
            // 如果使用 selector.select() 就会阻塞在这里的
            // nio非阻塞式的优势
            if (selector.select(1000)==0){
                System.out.println("Server:等待了1秒，无客户端连接");
                continue;
            }
            //6.2 得到SelectionKey,判断通道里的事件
            Iterator<SelectionKey> keyIterator=selector.selectedKeys().iterator();
            if (keyIterator.hasNext()) {
                SelectionKey selectionKey = keyIterator.next();
                //客户端连接请求事件
                if(selectionKey.isAcceptable()){
                    SocketChannel socketChannel=serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector,SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                }
                //读取客户端数据事件
                if(selectionKey.isReadable()){
                    SocketChannel channel=(SocketChannel) selectionKey.channel();
                    ByteBuffer buffer=(ByteBuffer) selectionKey.attachment();
                    channel.read(buffer);
                    System.out.println("接收到客户端数据："+new String(buffer.array()));
                }
                // 6.3 手动从集合中移除当前key,防止重复处理
                keyIterator.remove();
            }
        }
    }
}
