package com.mk.learn.spring.ioc.spring;

import com.google.gson.Gson;
import com.mk.learn.spring.ioc.module.ConstructorModule;
import com.mk.learn.spring.ioc.module.SetterModule;
import com.mk.learn.spring.ioc.module.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @program: learning-demo
 * @description:
 * @author: mk_passby
 * @create: 2020-09-13 21:34
 **/
public class SpringIocTest  {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
            "springIoc.xml");
        User user = (User) applicationContext.getBean("user");
        System.out.println("user.getName() = " + user.getName());
        System.out.println("user.getAge() = " + user.getAge());
        //测试IOC
        SetterModule setterDemo = (SetterModule) applicationContext.getBean("setterModule");
        System.out.println("new Gson().toJson(setterModule) = " + new Gson().toJson(setterDemo));

        ConstructorModule constructorDemo = (ConstructorModule) applicationContext.getBean("constructorDemo");
        System.out.println("constructorDemo.getAge() = " + constructorDemo.getAge());


    }
}
