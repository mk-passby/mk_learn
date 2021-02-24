package com.mk.learn.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @program: learning-demo
 * @description:
 * 实例要求: 使用 FileChannel(通道) 和 方法  read , write，
 * 完成文件的拷贝 拷贝一个文本文件 1.txt  , 放在项目下即可
 * @author: mk_passby
 * @create: 2020-09-21 21:50
 **/
public class NIOFileOPer03 {
    public static void main(String[] args) throws IOException {
        File file=new File("D:/file01.txt");
        FileInputStream fileInputStream=new FileInputStream(file);
        FileChannel channel = fileInputStream.getChannel();
        ByteBuffer byteBuffer=ByteBuffer.allocate((int) file.length());
        //将channel里面的数据读到buffer中
        channel.read(byteBuffer);
        //将数据读到byteBuffer中，然后写出
        FileOutputStream fileOutputStream=new FileOutputStream("D:/file02.txt");
        FileChannel outputStreamChannel = fileOutputStream.getChannel();
        //byteBuffer由写操作flip成读操作
        byteBuffer.flip();
        outputStreamChannel.write(byteBuffer);
        outputStreamChannel.close();
        fileInputStream.close();
    }
}
