package com.mk.learn.juc;

/**
 * @program: learning-demo
 * @description:
 * @author: mk_passby
 * @create: 2020-07-19 22:49
 **/
public class SingletonDemo {

    private static volatile SingletonDemo singletonDemo = null;

    private SingletonDemo() {}

    //DCL双端加锁机制
    public SingletonDemo getInstance() {
        if (singletonDemo == null) {
            synchronized (SingletonDemo.class) {
                if (singletonDemo == null) {
                    singletonDemo = new SingletonDemo();
                    return singletonDemo;
                }

            }
        }
        return singletonDemo;
    }

}
