package com.mk.learn.juc;

/**
 * @program: learning-demo
 * @description:
 * @author: mk_passby
 * @create: 2020-07-13 23:04
 **/
public class TestAdd {

    public volatile int n;

    public void add() {
        n++;
    }

}
