package com.mk.learn.netty.codec;

import com.mk.learn.netty.codec.StudentPoJO.Student;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

/**
 * @program: learning-demo
 * @description: 服务端的处理类
 * @author: mk_passby
 * @create: 2020-10-06 14:44
 **/
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 读取数据事件
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //读取从客户端发送的student
        StudentPoJO.Student student = (Student) msg;
        System.out.println("客户端发送的数据是：" + student);
    }

    /**
     * 数据读取完毕事件
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello client(>^ω^<)事件处理完毕", CharsetUtil.UTF_8));
    }

    /**
     * 异常发生事件
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable t) {
        ctx.close();
    }
}
