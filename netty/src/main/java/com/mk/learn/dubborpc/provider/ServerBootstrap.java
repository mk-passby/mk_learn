package com.mk.learn.dubborpc.provider;


import com.mk.learn.dubborpc.netty.NettyServer;

public class ServerBootstrap {

  public static void main(String[] args) {
    NettyServer.startServer("127.0.0.1", 7002);

  }

}
