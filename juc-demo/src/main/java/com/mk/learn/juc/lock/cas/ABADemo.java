package com.mk.learn.juc.lock.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @program: learning-demo
 * @description:
 * @author: mk_passby
 * @create: 2020-07-19 22:54
 **/
public class ABADemo {

    private static AtomicReference<String> atomicReference = new AtomicReference<>("AA");
    private static AtomicStampedReference<String> atomicStampedReference =
        new AtomicStampedReference<>("AAA", 1);

    public static void main(String[] args) {
        //ABA问题(AAA线程修改内容为BB,在修改为AA，BBB线程执行的时候感知不到过程有修改)
        new Thread(() -> {
            atomicReference.compareAndSet("AA", "BB");
            System.out.println(Thread.currentThread().getName() + "\t" + atomicReference.get());
            atomicReference.compareAndSet("BB", "AA");
            System.out.println(Thread.currentThread().getName() + "\t" + atomicReference.get());
        }, "T1").start();
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicReference.compareAndSet("AA", "cc");
            System.out.println(Thread.currentThread().getName() + "\t" + atomicReference.get());
        }, "T2").start();

        //AtomicStampedReference
        new Thread(() -> {
            atomicStampedReference.compareAndSet(
                "AAA",
                "BBB",
                1,
                2);
            System.out.println(Thread.currentThread().getName() + "\t"
                + atomicStampedReference.getReference());
            atomicStampedReference.compareAndSet(
                "BBB",
                "AAA",
                2,
                3);
            System.out.println(Thread.currentThread().getName() + "\t"
                + atomicStampedReference.getReference());
        }, "T3").start();
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicStampedReference.compareAndSet(
                "AAA",
                "CCC",
                1,
                4);
            System.out.println(Thread.currentThread().getName() + "\t"
                + atomicStampedReference.getReference());
        }, "T4").start();
    }

}
