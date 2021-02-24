package com.mk.learn.dubborpc.provider;


import com.mk.learn.dubborpc.publicInterface.HelloService;

/**
 * 实现类
 */
public class HelloServiceImpl implements HelloService {

  @Override
  public String hello(String msg) {
    System.out.println("收到客户端消息: " + msg);
    return msg != null ? msg + " -----> hello client." : "hello client.";
  }
}
