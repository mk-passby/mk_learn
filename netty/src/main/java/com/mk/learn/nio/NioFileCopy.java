package com.mk.learn.nio;

    import java.io.FileInputStream;
    import java.io.FileOutputStream;
    import java.io.IOException;
    import java.nio.channels.FileChannel;

/**
 * @program: learning-demo
 * @description:
 * @author: mk_passby
 * @create: 2020-09-21 21:51
 **/
public class NioFileCopy {
    public static void main(String[] args) throws IOException {
        FileInputStream fis=new FileInputStream("d:\\file01.txt");
        FileOutputStream fos=new FileOutputStream("d:\\file04.txt");
        /*
         * 说明
         * 从两个流中得到两个通道，sourCh ,destCh ，
         * 然后直接调用 transferFrom 完成文件复制
         */
        FileChannel sourCh = fis.getChannel();
        FileChannel destCh = fos.getChannel();

        /*
         * transferFrom 方法可以将两个通道连接起来，进行数据传输
         * @param  src
         *         The source channel
         *
         * @param  position
         *         The position within the file at which the transfer is to begin;
         *         must be non-negative
         *
         * @param  count
         *         The maximum number of bytes to be transferred; must be
         *         non-negative
         *
         * @return  The number of bytes, possibly zero,
         *          that were actually transferred
         */

        destCh.transferFrom(sourCh, 0, sourCh.size());
        sourCh.close();
        destCh.close();
        fis.close();
        fos.close();
        System.out.println("拷贝完毕~~");
    }
}
