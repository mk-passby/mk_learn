package com.mk.learn.spring.ioc.spring.beanlife;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @program: learning-demo
 * @description:
 * @author: mk_passby
 * @create: 2020-10-18 17:30
 **/
public class User implements BeanNameAware, BeanFactoryAware, ApplicationContextAware,
    BeanPostProcessor, InitializingBean, DisposableBean {

    private String userName;
    private int age;

    public String getUserName() {
        System.out.println("User:getUserName");
        return userName;
    }

    public void setUserName(String userName) {
        System.out.println("User:setUserName");
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    private void init(){
        System.out.println("User:init");
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("BeanNameAware:setBeanName");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("BeanFactoryAware:setBeanFactory");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("ApplicationContextAware:setApplicationContext");
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName)
        throws BeansException {
        System.out.println("BeanPostProcessor:postProcessBeforeInitialization");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName)
        throws BeansException {
        System.out.println("BeanPostProcessor:postProcessAfterInitialization");
        return bean;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean:afterPropertiesSet");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean:destroy");
    }

    public void destroyUser() throws Exception {
        System.out.println("User:destroyUser");
    }
}
