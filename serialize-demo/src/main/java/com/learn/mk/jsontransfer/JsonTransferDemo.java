package com.learn.mk.jsontransfer;

import com.alibaba.fastjson.JSON;
import com.baidu.bjf.remoting.protobuf.Codec;
import com.baidu.bjf.remoting.protobuf.ProtobufProxy;
import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;
import com.learn.mk.serializedemo.Person;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * @program: learning-demo
 * @description:
 * @author: mk_passby
 * @create: 2019-09-04 22:06
 **/
public class JsonTransferDemo {

    private static Person getPerson() {
        Person person = new Person();
        person.setUserName("AAA");
        person.setAge(18);
        return person;
    }

    public static void main(String[] args) throws IOException {
        Person person = getPerson();
        transferByJackson(person);
        transferByFastJson(person);
        transferByProtoBuf(person);
        transferByHession(person);


    }

    private static void transferByHession(Person person) throws IOException {

        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        HessianOutput hessianOutput=new HessianOutput(byteArrayOutputStream);
        hessianOutput.writeObject(person);
        HessianInput hessianInput=new HessianInput(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
        Person person1= (Person) hessianInput.readObject(Person.class);
        System.out.println("hessian序列化："+person1);

    }

    private static void transferByProtoBuf(Person person) throws IOException {
        Codec<Person> personCodec = ProtobufProxy.create(Person.class, false);
        byte[] bytes = personCodec.encode(person);
        Person person1 = personCodec.decode(bytes);
        System.out.println("protobuf序列化："+person1);

    }

    private static void transferByFastJson(Person person) {
        String jsonResult = JSON.toJSONString(person);
        System.out.println("fastJson序列化：" + jsonResult);
        Person person1 = JSON.parseObject(jsonResult, Person.class);
        System.out.println("fastJson反序列化：" + person1);


    }

    private static void transferByJackson(Person person) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        byte[] writeBytes = objectMapper.writeValueAsBytes(person);
        System.out.println("Jackson序列化成功：" + writeBytes.length);

        //readValue还有很多其他方式
        Person person1 = objectMapper.readValue(writeBytes, Person.class);
        System.out.println(person1);
    }
}
