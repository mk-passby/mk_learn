package com.learn.mk.clone;

import java.io.IOException;

/**
 * @program: learning-demo
 * @description:
 * @author: mk_passby
 * @create: 2019-09-04 22:42
 **/
public class CloneDemo {

    public static void main(String[] args)
        throws IOException, ClassNotFoundException, CloneNotSupportedException {
        Child childA=new Child();
        childA.setAge("49");
        childA.setName("AAA");
        Child childB=new Child();
        childB.setAge("20");
        childB.setName("BBB");
        childA.setChild(childB);


        Child childC=childA.deepClone();
        System.out.println("深克隆完后------");
        System.out.println(childC);
        System.out.println(childA);
        changed(childC);
        System.out.println("修改C后----");
        System.out.println(childC);
        System.out.println(childA);

        //实现Clone的是浅克隆，未克隆对象的引用,对象中的引用对象和clone的结果对象指向同一对象
        Child childC1= (Child) childA.clone();
        System.out.println("浅克隆完后------");
        System.out.println(childC1);
        System.out.println(childA);
        changed(childC1);
        System.out.println("修改C1后----");
        System.out.println(childC1);
        System.out.println(childA);


    }

    private static void changed(Child childC) {
        childC.setName("CCC");
        childC.setAge("22");
        childC.getChild().setName("Inner");
    }

}
