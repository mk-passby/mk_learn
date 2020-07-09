package com.mk.learn;

/**
 * @program: learning-demo
 * @description: 栈溢出
 * @author: mk_passby
 * @create: 2020-05-12 22:51
 **/
public class StackOverFlowTest {
    private int deep;
    public void test(){
        deep++;
        test();
    }

    public int getDeep() {
        return deep;
    }

    //-Xss65K
    public static void main(String[] args) {
        StackOverFlowTest stackOverFlowTest=new StackOverFlowTest();
        stackOverFlowTest.test();
    }
}
