package com.mk.learn.juc.thread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @program: learning-demo
 * @description:
 * @author: mk_passby
 * @create: 2020-08-30 22:31
 **/
public class UnSafeDemo {

    public static void main(String[] args) {
        List<Double> list = new ArrayList<>();
        //线程不安全
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                list.add(Math.random() * 1000);
                System.out.println(list);
            }, String.valueOf(i)).start();
        }

//        //Collections.synchronizedList(list)保证线程安全
        List<Double> list1 = Collections.synchronizedList(list);
//        for (int i = 0; i < 30; i++) {
//            new Thread(() -> {
//                list1.add(Math.random() * 1000);
//                System.out.println(list1);
//            }, String.valueOf(i)).start();
//        }
//        Thread.sleep(2000);
//        System.out.println(list1.size());

    }
}
