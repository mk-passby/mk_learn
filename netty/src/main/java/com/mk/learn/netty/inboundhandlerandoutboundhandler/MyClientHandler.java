package com.mk.learn.netty.inboundhandlerandoutboundhandler;


import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.EncoderException;
import io.netty.util.ReferenceCountUtil;

import java.nio.charset.Charset;

public class MyClientHandler extends SimpleChannelInboundHandler<Long> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Long msg) throws Exception {
        System.out.println(ctx.channel().remoteAddress());
        System.out.println("服务器回送消息 : " + msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("MyClientHandler发送数据");
        //注意如果将123456L 就不会发出数据，为什么？
        /*
        debug
        1) 根据执行流程会去执行 MyByteToLongDecoder
        2) MyByteToLongDecoder 的父类是 MessageToByteEncoder
        3) 有一个方法 write, 这里会判断 是否应该处理该消息 acceptOutboundMessage(msg)
        如果返回true 就去调用下一个hendler的方法(比如这里就是encode),否则就 不处理了

         public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {}
        4) 进行下main的handler处理
         */
        //ctx.writeAndFlush(123456L);//发送一个Int
//
//        ctx.writeAndFlush(123456L);
//        ctx.writeAndFlush(123456L);
//        ctx.writeAndFlush(123456L);
//        ctx.writeAndFlush(123456L);

        //通过，这个方法将 "abcdabcdabcdabcd" 字符串按照 utf-8的方式转成字节，发送
        //可以验证 服务器的解码器的decode方法会被多次调用，
        //这里我们发送了 16个字节，因此被调用2次，哈哈
        ctx.writeAndFlush(Unpooled.copiedBuffer("abcdabcdabcdabcd", Charset.forName("utf-8")));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
