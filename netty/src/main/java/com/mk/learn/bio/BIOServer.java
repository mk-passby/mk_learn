package com.mk.learn.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: learning-demo
 * @description:
 * @author: mk_passby
 * @create: 2020-09-21 21:42
 **/
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
