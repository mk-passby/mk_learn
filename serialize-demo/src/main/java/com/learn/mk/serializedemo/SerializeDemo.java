package com.learn.mk.serializedemo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

/**
 * @program: learning-demo
 * @description:
 * @author: mk_passby
 * @create: 2019-08-04 23:17
 **/
public class SerializeDemo {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        serializePerson();
        deSerializePerson();
        return;


    }

    private static void deSerializePerson() throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(
            new FileInputStream(new File("person")));
        Person person = (Person) objectInputStream.readObject();
        System.out.println("反序列化：" + person);
    }

    private static void serializePerson() throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(
            new FileOutputStream(new File("person")));
        Person person = new Person();
        person.setAge(22);
        person.setUserName("TEST");
        objectOutputStream.writeObject(person);
        System.out.println("序列化成功");
        objectOutputStream.close();
    }
}
