package com.mk.learn.spring.ioc.spring.beanlife;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @program: learning-demo
 * @description:
 * @author: mk_passby
 * @create: 2020-10-18 17:28
 **/
public class BeanLifeTest {

    public static void main(String[] args) {
        ApplicationContext applicationContext =
            new ClassPathXmlApplicationContext("springBeanLife.xml");
       //User user = (User) applicationContext.getBean("user");

       ((ClassPathXmlApplicationContext) applicationContext).close();
    }
}
