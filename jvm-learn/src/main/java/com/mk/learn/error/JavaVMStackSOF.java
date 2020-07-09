package com.mk.learn.error;

/**
 * @program: learning-demo
 * @description:
 * @author: mk_passby
 * @create: 2020-06-21 11:53
 **/
public class JavaVMStackSOF {

    private int stackLength = 1;

    public void stackLeak() {
        stackLength++;
        stackLeak();
    }

    /**
     * VM Args:-Xss128k
     */
    public static void main(String[] args) {
        JavaVMStackSOF oom = new JavaVMStackSOF();
        try {
            oom.stackLeak();
        } catch (Throwable e) {
            System.out.println("stack length is :" + oom.stackLength);
            throw e;
        }
    }
}
