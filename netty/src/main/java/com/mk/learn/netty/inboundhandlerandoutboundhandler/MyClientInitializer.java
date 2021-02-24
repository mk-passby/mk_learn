package com.mk.learn.netty.inboundhandlerandoutboundhandler;


import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;


public class MyClientInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();


        //入站handler进行解码
        pipeline.addLast(new MyByteToLongDecoder());

        //出站handler进行编码
        pipeline.addLast(new MyLongToByteEncoder());

        //自定义的一个入站hander
        pipeline.addLast(new MyClientHandler());

    }
}
