package com.mk.learn.netty.tempsimple;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * @program: learning-demo
 * @description: 服务端的处理类
 * @author: mk_passby
 * @create: 2020-10-06 14:44
 **/
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 读取数据事件
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("Server:"+ctx);
        ByteBuf buf=(ByteBuf) msg;
        System.out.println("客户端发来的消息："+buf.toString(CharsetUtil.UTF_8));
    }

    /**
     * 数据读取完毕事件
     * @param ctx
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx){
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello client(>^ω^<)事件处理完毕",CharsetUtil.UTF_8));
    }

    /**
     * 异常发生事件
     * @param ctx
     * @param t
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,Throwable t){
        ctx.close();
    }
}
