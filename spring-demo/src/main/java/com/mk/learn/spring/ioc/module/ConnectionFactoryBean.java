package com.mk.learn.spring.ioc.module;

import java.sql.Connection;
import java.sql.DriverManager;
import org.springframework.beans.factory.FactoryBean;

/**
 * @program: learning-demo
 * @description:
 * @author: mk_passby
 * @create: 2020-09-13 22:26
 **/
public class ConnectionFactoryBean implements FactoryBean<Connection> {

    @Override
    public Connection getObject() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection= DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/test?useSSL=false",
            "root",
            "xxxx");
        return connection;
    }

    @Override
    public Class<?> getObjectType() {
        return Connection.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
