package com.mk.learn.netty.heartbeattest;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;
import java.util.concurrent.TimeUnit;
/**
 * @program: learning-demo
 * @description:
 * @author: mk_passby
 * @create: 2020-10-06 20:53
 **/
/**
 * 心跳机制：判断是否还处于连接状态。
 * 提问：1.比如说之前的多客户端通信demo，当客户端断开与服务器连接的时候会触发handlerRemoved方法，
 * 那么我们就知道该服务的状态了。为什么还需要心跳包去感知呢？
 * 真实情况远比我们想象中的复杂，比如我们的客户端是移动手机并且已经建立好了连接，当打开飞行模式（或者强制关机）的时候
 * 我们就无法感知当前连接已经断开了（handlerRemoved不会触发的），
 *
 * 测试的时候，就使用 前面的 GroupChatClient 即可，注意需要连接到 Server才会出效果
 */
/**
 * 测试：这里就写client了，可以直接用Third包下的Client。
 */
public class MyServer {
    public static void main(String[] args) throws  Exception{
        //事件循环组
        EventLoopGroup bossGroup=new NioEventLoopGroup();
        EventLoopGroup workerGroup=new NioEventLoopGroup();
        try{
            ServerBootstrap serverBootstrap=new ServerBootstrap();
            /**
             * handler()用于bossGroup
             * childHandler()用于workerGroup
             */
            serverBootstrap.group(bossGroup,workerGroup).channel(NioServerSocketChannel.class).
                handler(new LoggingHandler(LogLevel.INFO)).
                childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) {
                        ChannelPipeline pipeline=ch.pipeline();
                        /*
                         * 处理空闲状态事件的处理器
                         * IdleStateHandler三个参数分别对应：readerIdleTime，writerIdleTime，allIdleTime
                         * readerIdleTime：表示多长时间没有读，就发送一个心跳包检验是否连接。
                         * writerIdleTime：表示多长时间没有写，就发送一个心跳包检验是否连接。
                         * allIdleTime：表示过多长时间没有读写，就发送一个心跳包检验是否连接。
                         */
                        //说明handler 的机制
                        /*
                         * Triggers an {@link IdleStateEvent} when a {@link Channel} has not performed read, write, or both operation for a while.
                         *
                         * 当IdleStateEvent 触发后，就会传递给管道的下一个handler MyServerHandler去处理，通过回调 Handler的  userEventTriggered
                         * 因此，对 IdleStateEvent事件的处理，我们要重写 MyServerHandler的  userEventTriggered 就可以.
                         */
                        pipeline.addLast(new IdleStateHandler(3,5,7, TimeUnit.SECONDS));
                        //对空闲检测进一步处理的Handler
                        pipeline.addLast(new MyServerHandler());
                    }
                });
            ChannelFuture channelFuture=serverBootstrap.bind(7000).sync();
            channelFuture.channel().closeFuture().sync();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
