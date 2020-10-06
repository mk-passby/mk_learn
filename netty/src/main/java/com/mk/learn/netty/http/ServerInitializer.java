package com.mk.learn.netty.http;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @program: learning-demo
 * @description:
 * @author: mk_passby
 * @create: 2020-10-06 16:12
 **/
public class ServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        //1.向管道加入处理器，得到管道
        //HttpServerCodec，netty提供的http的编解码器
        socketChannel.pipeline().addLast("自定义httpServer",new HttpServerCodec());
        //2.添加自己的自定义的handler
        socketChannel.pipeline().addLast("自定义HttpServerHandler",new HttpServerHandler());

    }
}
