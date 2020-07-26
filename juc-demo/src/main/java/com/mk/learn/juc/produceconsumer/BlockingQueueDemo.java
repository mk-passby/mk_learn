package com.mk.learn.juc.produceconsumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * 堵塞队列生消
 *
 * @program: learning-demo
 * @description:
 * @author: mk_passby
 * @create: 2020-07-23 22:19
 **/
public class BlockingQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        //生产一个消费一个
       // demo1();
        BlockingQueueResource<String> queueResource = new BlockingQueueResource<>(
            new SynchronousQueue());
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                queueResource.produce("AA" + i);
            }
        }, "t1").start();
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                queueResource.consumer();
            }
        }, "t2").start();

        //控制生产消费者的中止
       // TimeUnit.SECONDS.sleep(5);
        // queueResource.stop();
    }


    private static void demo1() {
        SynchronousQueue<String> synchronousQueue = new SynchronousQueue<>();
        new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    System.out.println("生产一个：" + Thread.currentThread().getName());
                    synchronousQueue.put(Thread.currentThread().getName());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1").start();
        new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    System.out.println(Thread.currentThread().getName() + ":sleep 3s");
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(
                        Thread.currentThread().getName() + "消费：" + synchronousQueue.take());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t2").start();
    }

}

class BlockingQueueResource<T> {

    private BlockingQueue<T> blockingQueue = null;
    private volatile boolean FLAG = true;

    public BlockingQueueResource(BlockingQueue blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public boolean produce(T t) {
        while (FLAG) {
            try {
                TimeUnit.SECONDS.sleep(3);
                boolean result = blockingQueue.offer(t, 2, TimeUnit.SECONDS);
                System.out.println(
                    Thread.currentThread().getName()
                        + "：blockingQueue 生产" + t + " status:" + result);
                return result;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("FLAG :" + FLAG + ",停止生产");
        return false;
    }

    public T consumer() {
        while (FLAG) {
            try {
                T t = blockingQueue.poll(5, TimeUnit.SECONDS);
                System.out.println(
                    Thread.currentThread().getName() + "：取出结果--->" + t);
                if (t == null) {
                    System.out.println("超过5s没有消费，模拟退出");
                    FLAG = false;
                }
                return t;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        System.out.println("FLAG :" + FLAG + ",停止消费");
        return null;
    }
    public void stop(){
        this.FLAG=false;
    }


}
