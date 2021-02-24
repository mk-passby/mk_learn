package com.mk.learn.juc.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @program: learning-demo
 * @description:
 * @author: mk_passby
 * @create: 2020-08-30 23:20
 **/
public class ThreadDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        new Thread1().start();
        new Thread(new Runnable1()).start();
        FutureTask task = new FutureTask(new Callable1());
        new Thread(task).start();
        System.out.println(task.get());
    }
}

class Thread1 extends Thread {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "Thread1 start");
    }
}


class Runnable1 implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "Thread2 start");
    }
}

class Callable1 implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        TimeUnit.SECONDS.sleep(5);
        System.out.println(Thread.currentThread().getName() + "Thread3 start");
        return 0;
    }
}