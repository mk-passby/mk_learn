package com.mk.learn.nio;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @program: learning-demo
 * @description:
 * @author: mk_passby
 * @create: 2020-09-21 21:45
 **/
public class NIOFileOper01 {

    /**
     * 使用FileChannel，ByteBuffer将字符串写入文件
     */
    public static void main(String[] args) throws IOException {

        String str = "hello,这是一个测试案例~";
        String filePath = NIOFileOper01.class.getResource("/").getPath();
        FileOutputStream fos = new FileOutputStream(filePath + File.separator + "001.txt");
        //使用FileChannel 通道
        //fos.getChannle 返回的是 FileChanel 的实现子类 FileChannelImpl
        //可以追下源代码
        FileChannel fc = fos.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
      /*
       *NIO 中的通道是从输出流对象里通过 getChannel 方法获取到的，该通道是双向的，既可
       以读，又可以写。在往通道里写数据之前，必须通过 put 方法把数据存到 ByteBuffer 中，然
         后通过通道的 write 方法写数据。在 write 之前，需要调用 flip 方法翻转缓冲区，把内部重置
         到初始位置，这样在接下来写数据时才能把所有数据写到通道里
       */
        buffer.put(str.getBytes());
        buffer.flip();
        fc.write(buffer);
        fos.close();

    }
}
