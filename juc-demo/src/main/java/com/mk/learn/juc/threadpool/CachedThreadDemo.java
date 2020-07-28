package com.mk.learn.juc.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: learning-demo
 * @description: 可缓存的线程池
 * @author: mk_passby
 * @create: 2020-07-26 11:47
 **/
public class CachedThreadDemo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            executorService.submit(() -> {
                System.out.println(Thread.currentThread().getName() + ":doing");
            });
        }

    }
}
