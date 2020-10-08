package com.mk.learn.netty.buf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * @program: learning-demo
 * @description:
 * @author: mk_passby
 * @create: 2020-10-06 17:27
 **/
public class NettyByteBuf01 {

    public static void main(String[] args) {
        //创建一个byteBuf
        //说明
        //1.创建一个对象，该对象包含byte[10]的数组
        //2.在netty中的byteBuf，不需要使用flip反转
        //底层维护了readerIndex(已经读取的index)，writerIndex(已写的区域)
        ByteBuf buffer = Unpooled.buffer(10);
        for (int i = 0; i < 10; i++) {
            buffer.writeByte(i);
        }
        System.out.println("capacity="+buffer.capacity());
        //第一种读法
//        for (int i = 0; i < 10; i++) {
//            System.out.println(buffer.getByte(i));
//        }
        //第二种读法
        for (int i = 0; i < 10; i++) {
            System.out.println(buffer.readByte());
        }

    }



}
