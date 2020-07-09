package com.mk.learn;

import static org.junit.Assert.*;

import org.junit.Test;

public class StackOverFlowTestTest {
    @Test
    public void test(){
        System.out.println("AAAA");
        StackOverFlowTest stackOverFlowTest=new StackOverFlowTest();
        try {
            stackOverFlowTest.test();
        }catch (Throwable e){
            e.printStackTrace();
            System.out.println("result:"+stackOverFlowTest.getDeep());
        }
    }

}