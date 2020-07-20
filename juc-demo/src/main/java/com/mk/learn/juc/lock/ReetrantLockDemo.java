package com.mk.learn.juc.lock;

import java.util.concurrent.TimeUnit;

/**
 * @program: learning-demo
 * @description:
 * @author: mk_passby
 * @create: 2020-07-20 22:18
 **/
public class ReetrantLockDemo {

    public static void main(String[] args) {
        //synchronized可重入
        Sport sport = new Sport();
        new Thread(() -> {
            sport.walk();
        }, "T1").start();
        new Thread(() -> {
            sport.walk();
        }, "T2").start();
    }
}
class Sport {

    public synchronized void walk() {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ": walking");
        run();
    }

    public synchronized void run() {
        System.out.println(Thread.currentThread().getName() + ": running");
    }
}
