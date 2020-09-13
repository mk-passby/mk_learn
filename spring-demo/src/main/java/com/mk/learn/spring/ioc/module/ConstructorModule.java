package com.mk.learn.spring.ioc.module;

/**
 * @program: learning-demo
 * @description:
 * @author: mk_passby
 * @create: 2020-09-13 22:15
 **/
public class ConstructorModule {
    private int age;
    private String naem;

    public ConstructorModule(int age, String naem) {
        this.age = age;
        this.naem = naem;
    }

    public int getAge() {
        return age;
    }

    public String getNaem() {
        return naem;
    }
}
