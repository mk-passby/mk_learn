package com.mk.learn.juc.produceconsumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 生产一个消费一个
 * @program: learning-demo
 * @description: 传统生产消模型
 * @author: mk_passby
 * @create: 2020-07-23 19:56
 **/
public class TraditionalDemo {

    //交换打印线程
    public static void main(String[] args) {
        Resource resource = new Resource();
        new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    resource.increase();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1").start();
        new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    resource.decrease();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t2").start();
    }


}

class Resource {

    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increase() throws InterruptedException {
        lock.lock();
        try {
            //不等于0，不该我执行
            while (number != 0) {
                condition.await();
            }
            number++;
            System.out.println(Thread.currentThread().getName() + ":number is " + number);
            condition.signalAll();
        } finally {
            lock.unlock();
        }

    }

    public void decrease() throws InterruptedException {
        lock.lock();
        try {
            //不等于1，不该我执行
            while (number != 1) {
                condition.await();
            }
            number--;
            System.out.println(Thread.currentThread().getName() + ":number is " + number);
            condition.signalAll();
        } finally {
            lock.unlock();
        }

    }
}
