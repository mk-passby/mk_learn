package com.learn.mk.socketdemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @program: learning-demo
 * @description: test
 * @author: mk_passby
 * @create: 2019-08-04 14:27
 **/
public class SocketClient {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 8888);
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            printWriter.println("hello server");
            BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
            while (true) {
                String serverData = bufferedReader.readLine();
                if (serverData == null) {
                    break;
                }
                System.out.println("客户端消息：" + serverData);
            }
            printWriter.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
