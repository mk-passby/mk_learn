package com.mk.learn.juc.concureenttools;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @program: learning-demo
 * @description:
 * @author: mk_passby
 * @create: 2020-07-21 21:57
 **/
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " is running");
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " done");
        }, "t10").start();
        for (int i = 0; i <5 ; i++) {
            TimeUnit.SECONDS.sleep(2);
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " is running");
                countDownLatch.countDown();
                System.out.println(Thread.currentThread().getName() + " done");
            }, "t" + i).start();

        }
        countDownLatch.await();
        System.out.println("end-----------------");
    }

}
