package com.mk.learn.netty.buf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

/**
 * @program: learning-demo
 * @description:
 * @author: mk_passby
 * @create: 2020-10-06 17:27
 **/
public class NettyByteBuf02 {

    public static void main(String[] args) {
        //创建一个byteBuf
        ByteBuf buffer = Unpooled.copiedBuffer("Hello,world", CharsetUtil.UTF_8);
        //使用相关的方法
        if (buffer.hasArray()) {
            byte[] content = buffer.array();
            //转换字符串
            System.out.println(new String(content, CharsetUtil.UTF_8));
            System.out.println("byteBuf=" + buffer);
            System.out.println(buffer.arrayOffset());
            System.out.println(buffer.readerIndex());
            System.out.println(buffer.writerIndex());
            System.out.println(buffer.capacity());
            //可读取的字节数
            int len = buffer.readableBytes();
            System.out.println("len is :" + len);


        }


    }


}
