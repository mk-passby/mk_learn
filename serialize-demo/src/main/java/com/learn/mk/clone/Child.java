package com.learn.mk.clone;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * @program: learning-demo
 * @description:
 * @author: mk_passby
 * @create: 2019-09-04 22:43
 **/
public class Child implements Serializable, Cloneable {

    private static final long serialVersionUID = -6176230484460015828L;
    private String name;
    private String age;
    private Child child;

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Child getChild() {
        return child;
    }

    public void setChild(Child child) {
        this.child = child;
    }

    public Child deepClone() throws IOException, ClassNotFoundException {
        //序列化
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(this);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
            byteArrayOutputStream.toByteArray());
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        Child child = (Child) objectInputStream.readObject();
        return child;
    }

    @Override
    public String toString() {
        return "Child{" +
            "name='" + name + '\'' +
            ", age='" + age + '\'' +
            ", child=" + child +
            '}';
    }
}
