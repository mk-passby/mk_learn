package com.mkpassby.springboot.jvm;

/**
 * @program: springboot
 * @description:
 * @author: mk_passby
 * @create: 2020-02-16 15:29
 **/
public class DefineDeadLock {
    private final Object one=new Object();
    private final Object two=new Object();

    public void getOne() throws InterruptedException {
        synchronized (one){
            Thread.sleep(2000);
            System.out.println("get one,lock one");
            synchronized (two){
                System.out.println("get one,lock two");
            }
        }
    }
    public void getTwo() throws InterruptedException {
        synchronized (two){
            Thread.sleep(2000);
            System.out.println("get two,lock two");
            synchronized (one){
                System.out.println("get two,lock one");
            }
        }
    }

}
