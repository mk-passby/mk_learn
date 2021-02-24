package com.mk.learn.spring.ioc.spring.beanlife;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @program: learning-demo
 * @description:
 * @author: mk_passby
 * @create: 2020-10-18 18:00
 **/
public class User1 implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName)
        throws BeansException {
        System.out.println("User1:postProcessBeforeInitialization");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName)
        throws BeansException {
        System.out.println("User1:postProcessAfterInitialization");
        return bean;
    }
}
