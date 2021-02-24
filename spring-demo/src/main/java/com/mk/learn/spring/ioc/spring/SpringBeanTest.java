package com.mk.learn.spring.ioc.spring;

import com.mk.learn.spring.ioc.module.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @program: learning-demo
 * @description:
 * @author: mk_passby
 * @create: 2020-09-13 20:34
 **/
public class SpringBeanTest {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
            "springBean.xml");
        User user = (User) applicationContext.getBean("user");
        System.out.println("通过spring创建Bean" + user);
        //User user1 =  applicationContext.getBean(User.class);
        //System.out.println(user1);
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println("BeanDefinitionName is:" + beanDefinitionName);
        }
        String[] beanNamesForTypes = applicationContext.getBeanNamesForType(User.class);
        for (String beanNamesForType : beanNamesForTypes) {
            System.out.println("beanNamesForType = " + beanNamesForType);
        }


    }

}
