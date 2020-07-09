## 实现一个序列化

1.实现Serializable接口

2.ObjectInputStream、ObjectOutputSream



- 深拷贝：复制对应的所有变量和引用对应的实例，即引用对象为一个新对象而不是原来的对象

  - 实现Cloneable接口，且重写clone()方法，将对象的引用克隆一次，传值

    ```JAVA
     	@Override
        public Object clone() throws CloneNotSupportedException {  		
    		// 调用父类clone接口复制变量：
            Student3 student = (Student3) super.clone();
            // 本来是浅复制，现在将Teacher对象复制一份并重新set进来
            student.setTeacher((Teacher2) student.getTeacher().clone());
            return student;
        }
    
    ```

  - 序列化实现深克隆

    ```java
    public Object deepClone() throws IOException, ClassNotFoundException {
            //序列化
            ByteArrayOutputStream baos=new ByteArrayOutputStream();
            ObjectOutputStream oos=new ObjectOutputStream(baos);
    
            oos.writeObject(this);
    
            //反序列化
            ByteArrayInputStream bais=new 						ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois=new ObjectInputStream(bais);
            return ois.readObject();
        }
    ```

    

- 浅拷贝：复制对象的所有变量，但是引用的对象还是以前的对象。
  
  - 实现Cloneable接口，直接调用clone()方法

## 序列化技术：

- JACKSON


```XML
		<dependency>
            <groupId>org.codehaus.jackson</groupId>
            <artifactId>jackson-mapper-asl</artifactId>
            <version>1.9.13</version>
        </dependency>
```

  ```JAVA
ObjectMapper mapper=new ObjectMapper();
          byte[] writeBytes=null;
          Long start=System.currentTimeMillis();
          writeBytes=mapper.writeValueAsBytes(person);//序列化
          Person person1=mapper.readValue(writeBytes,Person.class);//反序列化
          System.out.println(person1);
  ```

  - FastJson

```XML
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>fastjson</artifactId>
            <version>1.2.31</version>
        </dependency>
```

```JAVA
String result=JSON.toJSONString(person);//序列化
Person person=JSON.parseObject(text,Person.class);//反序列化
```

  - ProtoBuf

```java
public class Person implements Serializable{
	private static final long serialVersionUID = 5711515415445643416L;
    @Protobuf(fieldType = FieldType.STRING,order = 1)
    private String name;

    @Protobuf(fieldType = FieldType.INT32,order = 2)
    private int age;
```

```xml
        <dependency>
            <groupId>com.baidu</groupId>
            <artifactId>jprotobuf</artifactId>
            <version>2.1.2</version>
        </dependency>
```

```JAVA
Codec<Person> personCodec= ProtobufProxy.create(Person.class,false);//此处较耗时
byte[] bytes=personCodec.encode(person);//序列化
Person person1=personCodec.decode(bytes);//反序列化
```

  - Hession

```XML
        <dependency>
            <groupId>com.caucho</groupId>
            <artifactId>hessian</artifactId>
            <version>4.0.38</version>
        </dependency>
```

```java
ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
HessianOutput ho=new HessianOutput(byteArrayOutputStream);
ho.writeObject(person);//序列化

HessianInput hi=new HessianInput(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
Person person=(Person)hi.readObject();//反序列化

```

