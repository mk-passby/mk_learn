package com.mk.learn.netty.tempsimple;

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
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        ctx.writeAndFlush(Unpooled.copiedBuffer(Calendar.getInstance().getTime()+"hello channelRead",CharsetUtil.UTF_8));

//        //对于耗时的操作，可以使用eventloop中的队列，提交新线程执行，自定义的普通任务
        ctx.channel().eventLoop().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(10);
                    ctx.writeAndFlush(Unpooled.copiedBuffer(Calendar.getInstance().getTime()+"hello execute等待10s后",CharsetUtil.UTF_8));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        ctx.channel().eventLoop().schedule(new Runnable() {
            @Override
            public void run() {
                ctx.writeAndFlush(Unpooled.copiedBuffer(Calendar.getInstance().getTime()+"hello schedule等待5s后",CharsetUtil.UTF_8));
            }
        },15,TimeUnit.SECONDS);
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
