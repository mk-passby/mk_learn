package com.learn.mk.guigu.queue;

/**
 * @program: learning-demo
 * @description: 数组模拟环形队列(可进一步修改)
 * @author: mk_passby
 * @create: 2020-11-15 19:53
 **/
public class CircleQueue {

    private int maxSize;
    private int front;//指向队列头
    private int rear;//指向队列尾
    private int[] arr;

    public CircleQueue(int maxSize) {
        this.maxSize = maxSize;
        this.arr = new int[maxSize];
    }

    /**
     * 队列元素是否满
     */
    public boolean isFull() {
        return (rear+1) % maxSize == front;
    }

    /**
     * 添加队列元素
     */
    public void addQueue(int param) {
        if (isFull()) {
            System.out.println("队列元素已满，无法加入");
            return;
        }
        arr[rear] = param;
        rear = (rear + 1) % maxSize;
    }

    /**
     * 添加队列元素
     */
    public int getQueue(int param) {
        if (isEmpty()) {
            System.out.println("队列元素为空，无法获取");
            throw new RuntimeException("队列元素为空");
        }
        int result = arr[front];
        front = (front + 1) % maxSize;
        return result;
    }

    private boolean isEmpty() {
        return rear == front;
    }


    public static void main(String[] args) {
        CircleQueue circleQueue = new CircleQueue(10);
        for (int i = 0; i < 10; i++) {
            circleQueue.addQueue(i);
        }

    }
}
