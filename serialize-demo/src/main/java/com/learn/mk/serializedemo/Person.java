package com.learn.mk.serializedemo;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import java.io.Serializable;

/**
 * @program: learning-demo
 * @description:
 * @author: mk_passby
 * @create: 2019-08-04 23:13
 **/
public class Person implements Serializable {

    private static final long serialVersionUID = -3617824425220761709L;
    @Protobuf(fieldType = FieldType.STRING, order = 1)
    private String userName;
    @Protobuf(fieldType = FieldType.INT32, order = 2)
    private int age;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "this is toString:Person{" +
            "userName='" + userName + '\'' +
            ", age=" + age +
            '}';
    }
}
