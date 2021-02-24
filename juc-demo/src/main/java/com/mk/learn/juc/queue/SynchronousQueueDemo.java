package com.mk.learn.juc.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @program: learning-demo
 * @description:
 * @author: mk_passby
 * @create: 2020-07-23 19:41
 **/
public class SynchronousQueueDemo {

    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new SynchronousQueue<>();
        new Thread(() -> {
            try {
                blockingQueue.put("A1");
                System.out.println("A1 put");
                blockingQueue.put("A2");
                System.out.println("A2 put");
                blockingQueue.put("A3");
                System.out.println("A3 put");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1").start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println(blockingQueue.take() + "take");
                TimeUnit.SECONDS.sleep(3);
                System.out.println(blockingQueue.take() + "take");
                TimeUnit.SECONDS.sleep(3);
                System.out.println(blockingQueue.take() + "take");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t2").start();
    }
}
