package com.mk.learn.juc.lock;

import java.util.concurrent.TimeUnit;

/**
 * 死锁
 *
 * @program: learning-demo
 * @description:
 * @author: mk_passby
 * @create: 2020-07-28 21:12
 **/
public class DeadLockDemo {
    public static void main(String[] args) {
        DeadLock deadLock = new DeadLock();

        new Thread(() -> {
            deadLock.getA();
        }, "A").start();
        new Thread(() -> {
            deadLock.getB();
        }, "B").start();


    }
}

class DeadLock {

    private Object a = new Object();
    private Object b = new Object();

    public Object getA() {
        synchronized (b) {
            try {
                System.out.println(Thread.currentThread().getName() + "\t持有" + b + ",想要获得" + a);
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (a) {
                System.out.println(Thread.currentThread().getName() + "\t持有" + a);
            }
            return a;
        }
    }

    public Object getB() {
        synchronized (a) {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (b) {
                System.out.println(Thread.currentThread().getName() + "\t持有" + b);
            }
            return b;
        }

    }

}

