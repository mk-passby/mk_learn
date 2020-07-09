package com.learn.mk.multicastDemo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

/**
 * @program: learning-demo
 * @description: client
 * @author: mk_passby
 * @create: 2019-08-04 15:16
 **/
public class MulticastClient {

    public static void main(String[] args) {
        connect();
    }

    static void connect() {
        try {
            InetAddress group = InetAddress.getByName("224.1.2.3");
            MulticastSocket multicastSocket = new MulticastSocket(8888);
            multicastSocket.joinGroup(group);
            byte[] bytes = new byte[1024];
            while (true) {
                DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length);
                multicastSocket.receive(datagramPacket);
                String msg = new String(datagramPacket.getData());
                System.out.println("接受到msg：" + msg);
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
