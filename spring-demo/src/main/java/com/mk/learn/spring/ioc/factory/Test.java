package com.mk.learn.spring.ioc.factory;

import com.mk.learn.spring.ioc.module.User;

/**
 * @program: learning-demo
 * @description:
 * @author: mk_passby
 * @create: 2020-08-27 22:40
 **/
public class Test {

    public static void main(String[] args)
        throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        //直接new创建对象
        //User user=new User();
        Test test = new Test();
        //反射创建对象
        User user = test.createUserByRefle();

    }

    private User createUserByRefle()
        throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        //创建普通对象
        return (User) Class.forName("com.mk.learn.spring.ioc.module.User").newInstance();
    }

}
