package com.mk.learn.netty.websocket;

/**
 * @program: learning-demo
 * @description:
 * @author: mk_passby
 * @create: 2020-10-08 11:55
 **/

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import java.net.InetSocketAddress;

/**
 * 基于webSocket的长连接的全双工的交互（真是强大，改变了以往多次请求的约束，现在是长连接了，而且支持全双工通信） 客户端发送给服务端，服务端接收并能响应给客户端.客户端通过前端页面text.html来连接
 * 1.基于webSocket，ws协议.会出现一个101状态码，表示由http协议转成ws协议（协议升级） 2.每次刷新后，新建一个连接，而不是以前那个连接
 * 3.不要以为连接就一定能关闭，在断网或者断电情况，是检测不出来是否断连接的。（所以前面要有心跳机制）
 */
public class MyServer {

    public static void main(String[] args) {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup(8);
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap
                .group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .handler(new LoggingHandler(LogLevel.INFO))
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline pipeline = socketChannel.pipeline();
                        /**
                         * 因为这是基于http协议的，所以使用http编解码器。前面已经讲过了
                         */
                        pipeline.addLast(new HttpServerCodec());
                        //以块的方式去写, 添加 ChunkedWrite处理器
                        pipeline.addLast(new ChunkedWriteHandler());
                        /**
                         *  特别重要，http数据在传输过程是分段的
                         *  HttpObjectAggregator,而他就是将多个段聚合起来。
                         *  所以，你有时会看到，当我们客户端发送数据量大时，会发出多次http请求
                         */
                        pipeline.addLast(new HttpObjectAggregator(8192));
                        /**
                         * 对于webSocket，他的数据传输是以frame（帧）的形式传递
                         * 可以查看WebSocketFrame，他有六个子类
                         * /ws,表示的websocket的地址。
                         * 发出请求方式 ws://localhost:8889/路径
                         * 比如 ws://localhost:8899/hello 这个 /ws
                         */
                        pipeline.addLast(new WebSocketServerProtocolHandler("/hello"));
                        pipeline.addLast(new TextWebSocketFranmeHandler());
                    }
                });
            /**
             * 这里的端口绑定，和之前的端口直接绑定是一样的
             */
            ChannelFuture channelFuture = serverBootstrap.bind(new InetSocketAddress(8899)).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }


    }
}
