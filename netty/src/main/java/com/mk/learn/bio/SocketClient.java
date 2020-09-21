package com.mk.learn.bio;

import java.io.IOException;
import java.net.Socket;

/**
 * @program: learning-demo
 * @description:
 * @author: mk_passby
 * @create: 2020-09-21 21:45
 **/
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
