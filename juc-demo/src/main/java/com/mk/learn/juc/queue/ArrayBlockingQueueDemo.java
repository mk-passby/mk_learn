package com.mk.learn.juc.queue;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @program: learning-demo
 * @description:
 * @author: mk_passby
 * @create: 2020-07-23 19:36
 **/
public class ArrayBlockingQueueDemo {

    public static void main(String[] args) {
        ArrayBlockingQueue<String> arrayBlockingQueue=new ArrayBlockingQueue(2);
        arrayBlockingQueue.add("AA");
        arrayBlockingQueue.add("AA");
        arrayBlockingQueue.add("AA");
    }

}
