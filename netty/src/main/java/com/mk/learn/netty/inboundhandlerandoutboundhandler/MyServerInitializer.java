package com.mk.learn.netty.inboundhandlerandoutboundhandler;


import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;


public class MyServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        //入站handler进行解码
        pipeline.addLast(new MyByteToLongDecoder());

        //也可以使用MyByteToLongDecoder2 extends ReplayingDecoder来进行解码
        //更加方便
        //pipeline.addLast(new MyByteToLongDecoder2());

        //出栈handler进行编码
        pipeline.addLast(new MyLongToByteEncoder());
        //自定义的handler入站hander在 MyByteToLongDecoder 后调用
        pipeline.addLast(new MyServerHandler());
    }
}
