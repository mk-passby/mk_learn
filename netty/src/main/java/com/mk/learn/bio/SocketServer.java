package com.mk.learn.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @program: learning-demo
 * @description:
 * @author: mk_passby
 * @create: 2020-09-21 21:43
 **/
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
