package com.mk.learn.netty.groupchat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: learning-demo
 * @description:
 * @author: mk_passby
 * @create: 2020-10-06 19:43
 **/
public class GroupChatServerHandler extends SimpleChannelInboundHandler<String> {

    //GlobalEventExecutor.INSTANCE是全局时间执行器，是一个单例
    private static ChannelGroup channelGroup = new DefaultChannelGroup(
        GlobalEventExecutor.INSTANCE);
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    //定义一个channel组，拿到所有的channel
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s)
        throws Exception {

        //1.获取到当前的channel
        Channel channel = channelHandlerContext.channel();
        channelGroup.forEach(ch -> {
            if (channel != ch) {
                ch.writeAndFlush(
                    "[客户]" + channel.remoteAddress() + "发送消息：" + s + System.lineSeparator());
            } else {
                ch.writeAndFlush("[自己]发送消息：" + s + System.lineSeparator());
            }
        });
    }

    /**
     * 一旦连接，第一个被执行
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush(simpleDateFormat.format(new Date()) + "\n"
            + "[客户端]" + channel.remoteAddress() + "加入聊天\n");
        //将channel加入到channelGroup中
        channelGroup.add(channel);
    }

    /**
     * 表示channel处于活动状态，提示XXX上线
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(simpleDateFormat.format(new Date()) +"---"+ctx.channel().hashCode()+ "\n");
        System.out.println(ctx.channel().remoteAddress() + "上线了");
    }

    /**
     * 表示channel处于非活动状态，提示XXX下线
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(simpleDateFormat.format(new Date()) + "\n");
        System.out.println(ctx.channel().remoteAddress() + "下线了");
    }

    /**
     * 断开连接，将xx客户离开推送给当前在线的用户
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(simpleDateFormat.format(new Date()) + "\n");
        channelGroup.writeAndFlush("[客户端]" + channel.remoteAddress() + "离开了聊天\n");
        //执行handlerRemoved，channelGroup会减少，不需要手动删除
        System.out.println("当前channelGroup的个数是：" + channelGroup.size());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
