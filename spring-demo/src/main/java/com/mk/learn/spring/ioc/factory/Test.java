package com.mk.learn.spring.ioc.factory;

import com.mk.learn.spring.ioc.module.User;
import java.io.IOException;

/**
 * @program: learning-demo
 * @description:
 * @author: mk_passby
 * @create: 2020-08-27 22:40
 **/
public class Test {

    public static void main(String[] args)
        throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        //直接new创建对象
        //User user=new User();
        Test test = new Test();
        System.out.println("通过new获取对象：" + test);
        System.out.println(test);
        //反射创建对象
        User user = test.createUserByRefle();
        System.out.println("通过反射获取对象：" + user);
        //反射工厂创建对象
        User user1 = (User) NormalFactory.createObjectByRefle(User.class.getName());
        System.out.println("通过工厂反射获取对象：" + user1);
        //反射工厂+properties创建对象
        User user2 = (User) NormalFactory.createObjectByProperties("user");
        System.out.println("通过properties获取对象：" + user2);
    }

    private User createUserByRefle()
        throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        //创建普通对象
        return (User) Class.forName("com.mk.learn.spring.ioc.module.User").newInstance();
    }
}
