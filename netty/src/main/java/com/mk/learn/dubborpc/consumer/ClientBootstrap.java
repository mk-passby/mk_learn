package com.mk.learn.dubborpc.consumer;

import com.mk.learn.dubborpc.netty.NettyClient;
import com.mk.learn.dubborpc.publicInterface.HelloService;

public class ClientBootstrap {

  public static final String providerName = "HelloService#hello#";

  public static void main(String[] args) throws InterruptedException {

    NettyClient consumer = new NettyClient();
    // 创建一个代理对象
    HelloService service = (HelloService) consumer.getBean(HelloService.class, providerName);

    for (; ; ) {
      Thread.sleep(3000);
      System.out.println(service.hello("你好 dubbo ~ "));
    }
  }

}
