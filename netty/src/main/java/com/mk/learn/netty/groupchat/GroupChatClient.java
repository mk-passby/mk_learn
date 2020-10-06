package com.mk.learn.netty.groupchat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import java.util.Scanner;

/**
 * @program: learning-demo
 * @description:
 * @author: mk_passby
 * @create: 2020-10-06 20:06
 **/
public class GroupChatClient {

    private final String host;
    private final int port;

    public GroupChatClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void run() {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(eventLoopGroup)
            .channel(NioSocketChannel.class)
            .handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    ChannelPipeline pipeline = socketChannel.pipeline();
                    pipeline.addLast("DECODER", new StringDecoder());
                    pipeline.addLast("ENCODER", new StringEncoder());
                    pipeline.addLast(new GroupChatClientHandler());
                }
            });
        try {
            ChannelFuture channelFuture = bootstrap.connect(host, port).sync();
            //得到channel
            Channel channel = channelFuture.channel();
            System.out.println("---------------" + channel.localAddress() + "-------------"+channel.hashCode());
            //客户端扫描器
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()) {
                String string = scanner.nextLine();
                //通过channel发送给服务器
                channel.writeAndFlush(string + "\n");
            }


        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        GroupChatClient chatClient=new GroupChatClient("127.0.0.1",7000);
        chatClient.run();
    }

}
