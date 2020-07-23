package com.mk.learn.juc.concureenttools;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * 循环屏障，所有的线程都到达后会一起唤醒
 * @program: learning-demo
 * @description:
 * @author: mk_passby
 * @create: 2020-07-21 22:44
 **/
public class CyclicBarrierDemo {

    public static void main(String[] args) throws InterruptedException {
        CyclicBarrier cyclicBarrier=new CyclicBarrier(5);
        for (int i = 0; i <10 ; i++) {
            TimeUnit.SECONDS.sleep(1);
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+":come in");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+":end");
            }, "t"+i).start();
        }
    }

}
