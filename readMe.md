## BIO
### BIO 基本介绍

- Java BIO 就是传统的java io 编程，其相关的类和接口在 java.io 
- BIO(blocking I/O) 同步阻塞，服务器实现模式为一个连接一个线程，即客户端有连接请求时服务器端就需要启动一个线程进行处理，如果这个连接不做任何事情会造成不必要的线程开销，可以通过线程池机制改善
- BIO方式适用于连接数目比较小且固定的架构，这种方式对服务器资源要求比较高，并发局限于应用中，JDK1.4以前的唯一选择，程序简单易理解
### lBIO 工作机制

- BIO编程简单流程

   1)服务器端启动一个ServerSocket

  2)客户端启动Socket对服务器进行通信，默认情况下服务器端需要对每个客户 建立一个线程与之通讯

  3)客户端发出请求后, 先咨询服务器是否有线程响应，如果没有则会等待，或者被拒绝

  4)如果有响应，客户端线程会等待请求结束后，在继续执行

### 应用实例

1)使用BIO模型编写一个服务器端，监听6666端口，当有客户端连接时，就启动一个线程与之通讯。

2)要求使用线程池机制改善，可以连接多个客户端.服务器端可以接收客户端发送的数据

- 示例代码：

```java
package com.learn.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BIOServer {
    public static void main(String[] args) throws Exception {
        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
        ServerSocket server = new ServerSocket(6666);
        System.out.println("tomcat服务启动...");
        while (true) {
            final Socket socket = server.accept();
            System.out.println("连接到一个客服端");
            newCachedThreadPool.execute(() -> {
                //处理socket
                handler(socket);
            });
        }

    }
    /**
     * 处理类
     *
     * @param socket
     */
    public static void handler(Socket socket) {
        try {
            byte[] bytes = new byte[1024];
            InputStream inputStream = socket.getInputStream();

            while (true) {
                int read = inputStream.read(bytes);
                if (read != -1) {
                    System.out.println(new String(bytes, 0, read, "UTF-8"));
                } else {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                System.out.println("断开和客户端的连接..");
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
```



### BIO存在的问题

1. 每个请求对应需要创建独立的线程，处理数据
2. 并发量大，系统资源占用大
3. 建立连接后，如果没有数据可读的时候，线程就阻塞在read操作上，造成资源浪费

### windows下telnet基本使用

- 输入链接(Socket)：`telnet 127.0.0.1 6666`
- 发送一行信息
  - 在弹出的输入框中首先需要切换到输入模式`CTRL+]`，默认是输入后直接发送

### socket连接的创建方式

- SocketServer

```java
package com.learn.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
    public static void main(String[] args) throws IOException {
        ServerSocket socketServer = new ServerSocket(3333);
        Socket socket = socketServer.accept();
        InputStream inputStream = socket.getInputStream();
        byte[] bytes = new byte[1024];
        while(true){
            int len;
            while ((len = inputStream.read(bytes)) != -1) {
                System.out.println(new String(bytes, 0, len));
            }
        }

    }
}

```

- SocketClient

```java
package com.learn.bio;

import java.io.IOException;
import java.net.Socket;

public class SocketClient {
    public static void main(String[] args) {
        new Thread(() -> {
            Socket socket = null;
            try {
                Thread.sleep(2000);
                socket = new Socket("127.0.0.1", 3333);
                socket.getOutputStream().write("test socket".getBytes());

            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }
}

```

## NIO

### NIO基本介绍

- Java NIO的非阻塞模式，使一个线程从某通道发送请求或者读取数据，但是它仅能得到目前可用的数据，如果目前没有数据可用时，就什么都不会获取，而<font color="##FF0000">**不是保持线程阻塞**</font>，所以直至数据变的可以读取之前，该线程可以继续做其他的事情

- NIO是可以做到用一个线程来处理多个操作的。假设有10000个请求过来,根据实际情况，可以分配50或者100个线程来处理。不像之前的阻塞IO那样，非得分配10000个
- HTTP2.0使用了多路复用的技术，做到同一个连接并发处理多个请求，而且并发请求的数量比HTTP1.1大了好几个数量级

### NIO 三大核心原理

1)每个channel 都会对应一个Buffer

2)Selector 对应一个线程， 一个线程对应多个channel(连接)

3)该图反应了有三个channel 注册到 该selector //程序

4)程序切换到哪个channel 是有事件决定的, Event 就是一个重要的概念

5)Selector 会根据不同的事件，在各个通道上切换

6)Buffer 就是一个内存块 ， 底层是有一个数组

7)数据的读取写入是通过Buffer, 这个和BIO , BIO 中要么是输入流，或者是
 输出流, 不能双向，但是NIO的Buffer 是可以读也可以写, 需要 flip 方法切换

8)channel 是双向的, 可以返回底层操作系统的情况, 比如Linux ， 底层的操作系统
 通道就是双向的.

#### Buffer

- 缓冲区（Buffer）：缓冲区本质上是一个可以读写数据的内存块，可以理解成是一个容器对象(含数组)，该对象提供了一组方法，可以更轻松地使用内存块，，缓冲区对象内置了一些机制，能够跟踪和记录缓冲区的状态变化情况。Channel提供从文件、网络读取数据的渠道，但是读取或写入的数据都必须经由Buffer

- Buffer类定义了所有的缓冲区都具有的四个属性来提供关于其所包含的数据元素的信息
| 属性     | 描述                                                         |
| -------- | ------------------------------------------------------------ |
| Capacity | 容量，即可以容纳的最大数据量；在缓冲区创建时被设定并且不能改变 |
| Limit    | 表示缓冲区的当前终点，不能对缓冲区超过极限的位置进行读写操作。且极限是可以修改的 |
| Position | 位置，下一个要被读或写的元素的索引，每次读写缓冲区数据时都会改变改值，为下次读写作准备 |
| Mark     | 标记                                                         |



- Buffer类相关方法

```java
public abstract class Buffer {
    //JDK1.4时，引入的api
    public final int capacity( )//返回此缓冲区的容量
    public final int position( )//返回此缓冲区的位置
    public final Buffer position (int newPositio)//设置此缓冲区的位置
    public final int limit( )//返回此缓冲区的限制
    public final Buffer limit (int newLimit)//设置此缓冲区的限制
    public final Buffer mark( )//在此缓冲区的位置设置标记
    public final Buffer reset( )//将此缓冲区的位置重置为以前标记的位置
    public final Buffer clear( )//清除此缓冲区, 即将各个标记恢复到初始状态，但是数据并没有真正擦除, 后面操作会覆盖
    public final Buffer flip( )//反转此缓冲区
    public final Buffer rewind( )//重绕此缓冲区
    public final int remaining( )//返回当前位置与限制之间的元素数
    public final boolean hasRemaining( )//告知在当前位置和限制之间是否有元素
    public abstract boolean isReadOnly( );//告知此缓冲区是否为只读缓冲区
 
    //JDK1.6时引入的api
    public abstract boolean hasArray();//告知此缓冲区是否具有可访问的底层实现数组
    public abstract Object array();//返回此缓冲区的底层实现数组
    public abstract int arrayOffset();//返回此缓冲区的底层实现数组中第一个缓冲区元素的偏移量
    public abstract boolean isDirect();//告知此缓冲区是否为直接缓冲区
}

```



##### ByteBuffer

对于 Java 中的基本数据类型(boolean除外)，都有一个 Buffer类型与之相对应，最常用的自然是ByteBuffer 类（二进制数据），该类的主要方法如下

```java
public abstract class ByteBuffer {
    //缓冲区创建相关api
    public static ByteBuffer allocateDirect(int capacity)//创建直接缓冲区
    public static ByteBuffer allocate(int capacity)//设置缓冲区的初始容量
    public static ByteBuffer wrap(byte[] array)//把一个数组放到缓冲区中使用
    //构造初始化位置offset和上界length的缓冲区
    public static ByteBuffer wrap(byte[] array,int offset, int length)
     //缓存区存取相关API
    public abstract byte get( );//从当前位置position上get，get之后，position会自动+1
    public abstract byte get (int index);//从绝对位置get
    public abstract ByteBuffer put (byte b);//从当前位置上添加，put之后，position会自动+1
    public abstract ByteBuffer put (int index, byte b);//从绝对位置上put
 }

```



#### Channel

1)NIO的通道类似于流，但有些区别如下：

- 通道可以同时进行读写，而流只能读或者只能写

- 通道可以实现异步读写数据

- 通道可以从缓冲读数据，也可以写数据到缓冲

2)BIO 中的 stream 是单向的，例如 FileInputStream 对象只能进行读取数据的操作，而 NIO 中的通道(Channel)是双向的，可以读操作，也可以写操作。

3)Channel在NIO中是一个接口 `public interface Channel extends Closeable{} `

4)常用的 Channel 类有：`FileChannel`、`DatagramChannel`、`ServerSocketChannel` 和 `SocketChannel`。【`ServerSocketChanne` 类似 `ServerSocket` , `SocketChannel` 类似 `Socket`】

5)`FileChannel` 用于文件的数据读写，`DatagramChannel` 用于 UDP 的数据读写，`ServerSocketChannel` 和 `SocketChannel` 用于 TCP 的数据读写。

##### FileChannel类

- public int read(ByteBuffer dst) ，从通道读取数据并放到缓冲区中
- public int write(ByteBuffer src) ，把缓冲区的数据写到通道中
- public long transferFrom(ReadableByteChannel src, long position, long count)，从目标通道中复制数据到当前通道
- public long transferTo(long position, long count, WritableByteChannel target)，把数据从当前通道复制给目标通道



##### 应用实例1-本地文件写数据

- 使用ByteBuffer(缓冲) 和 FileChannel(通道)， 将 `hello,这是一个测试案例~`写入到file01.txt中

  ```java
  package com.learn.nio;
  
  import java.io.File;
  import java.io.FileOutputStream;
  import java.io.IOException;
  import java.nio.ByteBuffer;
  import java.nio.channels.FileChannel;
  
  public class NIOFileOper01 {
  
      /*
      使用FileChannel，ByteBuffer将字符串写入文件
       */
      public static void main(String[] args) throws IOException {
  
          String str = "hello,这是一个测试案例~";
          String filePath= NIOFileOper01.class.getResource("/").getPath();
          FileOutputStream fos = new FileOutputStream(filePath+ File.separator+"001.txt");
          //使用FileChannel 通道
          //fos.getChannle 返回的是 FileChanel 的实现子类 FileChannelImpl
          //可以追下源代码
          FileChannel fc = fos.getChannel();
          ByteBuffer buffer = ByteBuffer.allocate(1024);
        /*
         *NIO 中的通道是从输出流对象里通过 getChannel 方法获取到的，该通道是双向的，既可
         以读，又可以写。在往通道里写数据之前，必须通过 put 方法把数据存到 ByteBuffer 中，然
           后通过通道的 write 方法写数据。在 write 之前，需要调用 flip 方法翻转缓冲区，把内部重置
           到初始位置，这样在接下来写数据时才能把所有数据写到通道里
         */
          buffer.put(str.getBytes());
          buffer.flip();
          fc.write(buffer);
          fos.close();
  
      }
  }
  ```

##### 应用实例2-本地文件读数据

- 使用ByteBuffer(缓冲) 和 FileChannel(通道)， 将 file01.txt 中的数据读入到程序，并显示在控制台屏幕

```java
package com.learn.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/*
使用ByteBuffer(缓冲) 和 FileChannel(通道)， 将 file01.txt 中的数据读入到程序，并显示在控制台屏幕
 */
public class NIOFileOPer02 {
    public static void main(String[] args) throws IOException {
        File file=new File("D:/file01.txt");
        FileInputStream fileInputStream=new FileInputStream(file);
        FileChannel channel = fileInputStream.getChannel();
        ByteBuffer byteBuffer=ByteBuffer.allocate((int) file.length());
        channel.read(byteBuffer);
        System.out.println(new String(byteBuffer.array(),"UTF-8"));
        fileInputStream.close();
    }
}
```

##### 应用实例3-使用一个Buffer完成文件读取

- 使用 FileChannel(通道) 和 方法  read , write，完成文件的拷贝

```java
package com.learn.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/*
实例要求:
使用 FileChannel(通道) 和 方法  read , write，完成文件的拷贝
拷贝一个文本文件 1.txt  , 放在项目下即可
 */
public class NIOFileOPer03 {
    public static void main(String[] args) throws IOException {
        File file=new File("D:/file01.txt");
        FileInputStream fileInputStream=new FileInputStream(file);
        FileChannel channel = fileInputStream.getChannel();
        ByteBuffer byteBuffer=ByteBuffer.allocate((int) file.length());
        //将channel里面的数据读到buffer中
        channel.read(byteBuffer);
        //将数据读到byteBuffer中，然后写出
        FileOutputStream fileOutputStream=new FileOutputStream("D:/file02.txt");
        FileChannel outputStreamChannel = fileOutputStream.getChannel();
        //byteBuffer由写操作flip成读操作
        byteBuffer.flip();
        outputStreamChannel.write(byteBuffer);
        outputStreamChannel.close();
        fileInputStream.close();
    }
}
```

##### 应用实例4-拷贝文件transferFrom 方法

- 使用 FileChannel(通道) 和 方法  transferFrom ，完成文件的拷贝

```java
package com.learn.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class NioFileCopy {
    public static void main(String[] args) throws IOException {
        FileInputStream fis=new FileInputStream("d:\\file01.txt");
        FileOutputStream fos=new FileOutputStream("d:\\file04.txt");
        /*
         * 说明
         * 从两个流中得到两个通道，sourCh ,destCh ，
         * 然后直接调用 transferFrom 完成文件复制
         */
        FileChannel sourCh = fis.getChannel();
        FileChannel destCh = fos.getChannel();

        /*
         * transferFrom 方法可以将两个通道连接起来，进行数据传输
         * @param  src
         *         The source channel
         *
         * @param  position
         *         The position within the file at which the transfer is to begin;
         *         must be non-negative
         *
         * @param  count
         *         The maximum number of bytes to be transferred; must be
         *         non-negative
         *
         * @return  The number of bytes, possibly zero,
         *          that were actually transferred
         */

        destCh.transferFrom(sourCh, 0, sourCh.size());
        sourCh.close();
        destCh.close();
        fis.close();
        fos.close();
        System.out.println("拷贝完毕~~");
    }
}
```
