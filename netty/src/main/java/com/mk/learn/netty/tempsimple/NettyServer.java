package com.mk.learn.netty.tempsimple;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @program: learning-demo
 * @description: netty服务端
 * @author: mk_passby
 * @create: 2020-10-06 14:36
 **/
public class NettyServer {

    public static void main(String[] args) throws InterruptedException {
        //1.创建一个线程组，接受客户端连接请求
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //2.创建一个线程组，处理网络操作
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        //3.创建服务器端启动助手来配置
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        //4.设置两个线程组
        serverBootstrap.group(bossGroup, workerGroup)
            //5.使用NioServerSocketChannel作为服务器端通道的实现
            .channel(NioServerSocketChannel.class)
            //6.设置线程队列中等待连接的个数
            .option(ChannelOption.SO_BACKLOG, 128)
            //7.保持活动连接状态
            .childOption(ChannelOption.SO_KEEPALIVE, true)
            //8. 创建一个通道初始化对象
            .childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    socketChannel.pipeline().addLast(new NettyServerHandler());
                }
            });
        System.out.println("......Server is ready......");
        //10. 绑定端口 bind方法是异步的  sync方法是同步阻塞的
        ChannelFuture cf = serverBootstrap.bind(6668).sync();
        System.out.println("......Server is starting......");
        //注册监听器
        cf.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                if (cf.isSuccess()){
                    System.out.println("监听成功");
                }else{
                    System.out.println("监听失败");
                }
            }
        });

        //11. 关闭通道，关闭线程组
        cf.channel().closeFuture().sync(); //异步
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
        System.out.println("......Server is shutdownGracefully......");
    }
}
