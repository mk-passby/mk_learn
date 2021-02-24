package com.mk.learn.juc.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 固定长度的线程池
 *
 * @program: learning-demo
 * @description:
 * @author: mk_passby
 * @create: 2020-07-26 11:35
 **/
public class FixedThreadPoolDemo {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            executorService.submit(() -> {
                System.out.println(Thread.currentThread().getName() + ":doing");
            });
        }

    }
}
