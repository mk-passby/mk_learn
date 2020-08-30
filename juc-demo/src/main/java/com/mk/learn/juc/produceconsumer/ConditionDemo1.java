package com.mk.learn.juc.produceconsumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: learning-demo
 * @description: 多个线程交替执行
 * @author: mk_passby
 * @create: 2020-07-23 20:44
 **/
public class ConditionDemo1 {

    public static void main(String[] args) {
        Resource1 resource1 = new Resource1();
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                resource1.printT1(i);
            }
        }, "t1").start();
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                resource1.printT2(i);
            }
        }, "t2").start();
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                resource1.printT3(i);
            }
        }, "t3").start();
    }

}

class Resource1 {

    private int num;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();


    public void printT1(int i) {
        lock.lock();
        try {
            while (num != 0) {

                condition.await();

            }
            num = 1;
            System.out.println(Thread.currentThread().getName() + "：开始执行"+i);
            condition1.signal();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            lock.unlock();
        }

    }

    public void printT2(int i) {
        lock.lock();
        try {
            while (num != 1) {

                condition1.await();

            }
            num = 2;
            System.out.println(Thread.currentThread().getName() + "：开始执行"+i);
            condition2.signal();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            lock.unlock();
        }
    }

    public void printT3(int i) {
        lock.lock();
        try {
            while (num != 2) {
                condition2.await();
            }
            num = 0;
            System.out.println(Thread.currentThread().getName() + "：开始执行"+i);
            condition.signal();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            lock.unlock();
        }
    }
}