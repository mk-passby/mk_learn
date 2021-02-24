package com.mk.learn.spring.ioc.factory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @program: learning-demo
 * @description:
 * @author: mk_passby
 * @create: 2020-08-17 21:10
 **/
public class NormalFactory {

    /**
     * 通过工厂创建对象
     * @param className
     * @return
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static Object createObjectByRefle(String className)
        throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        //创建普通对象
        return Class.forName(className).newInstance();
    }

    /**
     * 通过工厂+properties创建对象
     * @param name
     * @return
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws IOException
     */
    public static Object createObjectByProperties(String name)
        throws ClassNotFoundException, IllegalAccessException, InstantiationException, IOException {
        InputStream
            inputStream =
            NormalFactory.class.getResourceAsStream("/factoryObject.properties");
        Properties properties=new Properties();
        properties.load(inputStream);

            //创建普通对象
        return Class.forName(properties.getProperty(name)).newInstance();
    }
}
