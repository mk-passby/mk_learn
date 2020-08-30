package com.mk.learn.juc.produceconsumer;

/**
 * @program: learning-demo
 * @description: 传统的生产消费模式
 * @author: mk_passby
 * @create: 2020-08-30 18:49
 **/
public class TraditionnaDemo0 {

    public static void main(String[] args) {
        Resource0 resource0 = new Resource0();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    resource0.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "AA").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    resource0.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "BB").start();
    }

}

class Resource0 {

    private int number = 0;

    public synchronized void increment() throws InterruptedException {
        //1.判断，不等于0，不该我执行
        while (number != 0) {
            this.wait();
        }
        //2.干活
        number++;
        System.out.println(Thread.currentThread().getName() + "\t" + number);
        //3.通知
        this.notifyAll();
    }

    public synchronized void decrement() throws InterruptedException {
        //1.判断，等于0，不该我执行
        while (number == 0) {
            this.wait();
        }
        //2.干活
        number--;
        System.out.println(Thread.currentThread().getName() + "\t" + number);
        //3.通知
        this.notifyAll();
    }
}