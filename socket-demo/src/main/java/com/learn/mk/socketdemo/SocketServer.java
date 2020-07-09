package com.learn.mk.socketdemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.BreakIterator;

/**
 * @program: learning-demo
 * @description: test
 * @author: mk_passby
 * @create: 2019-08-04 14:32
 **/
public class SocketServer {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(8888);
            while (true) {
                //等待一个接受
                Socket socket = serverSocket.accept();
                new Thread(() -> {
                    try {
                        BufferedReader reader = new BufferedReader(
                            new InputStreamReader(socket.getInputStream()));
                        PrintWriter printWriter = new PrintWriter(
                            new OutputStreamWriter(socket.getOutputStream()));
                        while (true) {
                            String clientData = reader.readLine();
                            if (clientData == null) {
                                break;
                            }
                            System.out.println("服务端消息：" + clientData);
                            printWriter.println("hello.mike");
                            printWriter.flush();
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        } catch (IOException e) {
        } finally {
            if (serverSocket != null) {
                serverSocket.close();
            }
        }
    }

}
