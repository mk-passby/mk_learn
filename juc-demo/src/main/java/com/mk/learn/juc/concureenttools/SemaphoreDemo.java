package com.mk.learn.juc.concureenttools;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 信号量，表示只能允许多少个线程同时运行(并发线程数的控制)
 *
 * @program: learning-demo
 * @description:
 * @author: mk_passby
 * @create: 2020-07-21 22:55
 **/
public class SemaphoreDemo {

    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < 7; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+":进入，占用3秒" );
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(Thread.currentThread().getName() + ":离开");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            }, "t" + i).start();
        }
    }
}
