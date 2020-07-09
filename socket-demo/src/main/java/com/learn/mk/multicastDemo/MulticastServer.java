package com.learn.mk.multicastDemo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

/**
 * @program: learning-demo
 * @description: multicastt
 * @author: mk_passby
 * @create: 2019-08-04 15:06
 **/
public class MulticastServer {

    public static void main(String[] args) {
        //地址范围：224.0.0.0-239.255.255.255
        try {
            InetAddress group = InetAddress.getByName("224.1.2.3");
            MulticastSocket multicastSocket = new MulticastSocket();
            for (int i = 0; i < 10; i++) {
                String data = "hello,index" + i;
                byte[] bytes = data.getBytes();
                multicastSocket.send(new DatagramPacket(bytes, bytes.length, group, 8888));
                TimeUnit.SECONDS.sleep(2);
            }

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
