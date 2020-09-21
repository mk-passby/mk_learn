package com.mk.learn.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @program: learning-demo
 * @description: 网络客户端程序
 * @author: mk_passby
 * @create: 2020-09-21 22:18
 **/
public class NIOClient {
    public static void main(String[] args) throws  Exception{
        //1. 得到一个网络通道
        SocketChannel channel=SocketChannel.open();
        //2. 设置非阻塞方式
        channel.configureBlocking(false);
        //3. 提供服务器端的IP地址和端口号
        InetSocketAddress address=new InetSocketAddress("127.0.0.1",6666);
        //4. 连接服务器端
        if(!channel.connect(address)){
            //nio非阻塞式
            while(!channel.finishConnect()){
                System.out.println("客户端: 因为连接需要时间，客户端不会阻塞，可以做个计算工作...");
            }
        }
        //连接成功了..
        //5. 得到一个缓冲区并存入数据
        String msg="hello,尚硅谷";
        ByteBuffer writeBuf = ByteBuffer.wrap(msg.getBytes());
        //6. 发送数据
        channel.write(writeBuf);
        System.in.read();
    }
}
