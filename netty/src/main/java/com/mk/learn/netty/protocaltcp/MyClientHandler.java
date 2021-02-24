package com.mk.learn.netty.protocaltcp;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.Charset;

public class MyClientHandler extends SimpleChannelInboundHandler<MessageProtocol> {

    private int count;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < 10; ++i) {
            String messageToBeSent = "晚上吃火锅 ";
            byte[] content = messageToBeSent.getBytes(Charset.forName("utf-8"));
            int length = messageToBeSent.getBytes(Charset.forName("utf-8")).length;

            MessageProtocol messagProtocol = new MessageProtocol();
            messagProtocol.setLength(length);
            messagProtocol.setContent(content);

            ctx.writeAndFlush(messagProtocol);
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageProtocol msg) throws Exception {
        int length = msg.getLength();
        byte[] content = msg.getContent();

        System.out.println("客户端接收到的消息： ");

        System.out.println("长度: " + length);
        System.out.println("内容：" + new String(content, Charset.forName("utf-8")));

        System.out.println("客户端接受到的消息数量：" + (++this.count));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
