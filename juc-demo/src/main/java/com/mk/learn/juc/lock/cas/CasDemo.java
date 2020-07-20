package com.mk.learn.juc.lock.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: learning-demo
 * @description: cas演示
 * @author: mk_passby
 * @create: 2020-07-13 23:37
 **/
public class CasDemo {

    public static void main(String[] args) {
        AtomicInteger atomicInteger=new AtomicInteger(10);
        System.out.println("compareAndSet result:"+atomicInteger.compareAndSet(10, 100));
        System.out.println("getAtomicInteger:"+atomicInteger.get());
        System.out.println("compareAndSet result:"+atomicInteger.compareAndSet(10, 100));
        System.out.println("getAtomicInteger:"+atomicInteger.get());
    }

}
