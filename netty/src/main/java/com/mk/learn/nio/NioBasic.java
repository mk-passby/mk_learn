package com.mk.learn.nio;

import java.nio.IntBuffer;
import java.util.Random;

public class NioBasic {
    public static void main(String[] args) {
        //申请buffer长度
        IntBuffer intBuffer = IntBuffer.allocate(3);
        Random random = new Random();
        for (int i = 0; i < intBuffer.capacity(); i++) {
            intBuffer.put(random.nextInt(1000));
        }
        //flip读写切换
        intBuffer.flip();
        while (intBuffer.hasRemaining()) {
            System.out.println(intBuffer.get());
        }


    }
}
