package com.mk.learn.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @program: learning-demo
 * @description:使用ByteBuffer(缓冲) 和 FileChannel(通道)， 将 file01.txt 中的数据读入到程序，并显示在控制台屏幕
 * @author: mk_passby
 * @create: 2020-09-21 21:47
 **/
public class NIOFileOPer02 {
    public static void main(String[] args) throws IOException {
        File file=new File("D:/file01.txt");
        FileInputStream fileInputStream=new FileInputStream(file);
        FileChannel channel = fileInputStream.getChannel();
        ByteBuffer byteBuffer= ByteBuffer.allocate((int) file.length());
        channel.read(byteBuffer);
        System.out.println(new String(byteBuffer.array(),"UTF-8"));
        fileInputStream.close();
    }
}
