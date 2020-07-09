package com.mkpassby.springboot.importdemo;

import org.springframework.context.annotation.Bean;

/**
 * @program: springboot
 * @description: d
 * @author: mk_passby
 * @create: 2019-06-23 12:03
 **/
public class MyConfig {

  @Bean
  public User getUser(){
    return new User();
  }
}
