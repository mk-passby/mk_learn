package com.mk.learn.juc.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁：循环比较获取直到成功为止,没有类似wait的阻塞
 *
 * @program: learning-demo
 * @description:
 * @author: mk_passby
 * @create: 2020-07-20 22:34
 **/
public class SpinLockDemo {

    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void getLock() {
        System.out.println(Thread.currentThread().getName() + ":try get lock");

        while (!atomicReference.compareAndSet(null, Thread.currentThread())) {
        }
    }

    public void unLock() {
        System.out.println(Thread.currentThread().getName() + ":release Lock");
        while (!atomicReference.compareAndSet(Thread.currentThread(), null)) {
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SpinLockDemo demo = new SpinLockDemo();
        new Thread(() -> {
            demo.getLock();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            demo.unLock();
        }, "t1").start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(() -> {
            demo.getLock();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            demo.unLock();
        }, "t2").start();
    }

}
