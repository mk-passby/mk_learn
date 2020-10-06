package com.mk.learn.netty.tempsimple;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * @program: learning-demo
 * @description: 客户端事件处理
 * @author: mk_passby
 * @create: 2020-10-06 14:49
 **/
public class NettyClientHandler extends ChannelInboundHandlerAdapter {

    /**
     * 读取事件
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println("服务端发送消息："+ byteBuf.toString(CharsetUtil.UTF_8));
    }

    /**
     * 通道就绪事件
     * @param ctx
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx){
        System.out.println("Client:"+ctx);
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello, server ^(*￣(oo)￣)^",CharsetUtil.UTF_8));
    }
}
