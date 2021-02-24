package com.mk.learn.netty.inboundhandlerandoutboundhandler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class MyByteToLongDecoder extends ByteToMessageDecoder {

    /**
     *必须实现的方法，对这个方法的调用将会重复进行，直到确定没有新的元素被添加到该List，或者该ByteBuf中没有更多可读取的字节时为止。如果该List不为空，那么它的内容将会被传递给ChannelPipeline中的下一个ChannelInboundHandler
     * @param ctx
     * @param in : ByteBuf包含了传入数据
     * @param out : List用来添加解码后的消息
     * @throws Exception
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("decode1 invoked!");

        System.out.println(in.readableBytes());

        //在调用readLong()方法前必须验证所输入的ByteBuf是否具有足够的数据
        if (in.readableBytes() >= 8) {
            //把数据处理后的数据转成Long 传递到下一个handler
            out.add(in.readLong());
        }


    }
}
