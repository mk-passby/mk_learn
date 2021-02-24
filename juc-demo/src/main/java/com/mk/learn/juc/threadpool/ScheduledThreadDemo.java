package com.mk.learn.juc.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @program: learning-demo
 * @description: 周期性任务的线程池
 * @author: mk_passby
 * @create: 2020-07-26 11:47
 **/
public class ScheduledThreadDemo {

    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService =
            Executors.newScheduledThreadPool(3);
        for (int i = 0; i < 10; i++) {
            scheduledExecutorService.schedule(() -> {
                System.out.println(Thread.currentThread().getName() + ":doing");
            }, 3, TimeUnit.SECONDS);
        }
        System.out.println("END---");
    }

}
