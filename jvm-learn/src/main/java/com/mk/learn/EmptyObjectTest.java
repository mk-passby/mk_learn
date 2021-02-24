package com.mk.learn;

import org.openjdk.jol.info.ClassLayout;

/**
 * @program: learning-demo
 * @description: 对象测试
 * @author: mk_passby
 * @create: 2020-05-08 22:12
 **/
public class EmptyObjectTest {

    public static void main(String[] args) {
        EmptyObject emptyObject = new EmptyObject();
        System.out.println(ClassLayout.parseInstance(emptyObject).toPrintable());
    }
}
