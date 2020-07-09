package com.mkpassby.springboot.controller;

import com.mkpassby.springboot.importdemo.User;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: springboot
 * @description:
 * @author: mk_passby
 * @create: 2019-06-30 17:53
 **/
@RestController
public class RestControllerDemo {

  @PostMapping(
      value = "user/properties/to/json",
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE,//出参类型Accept
      consumes = "application/properties+person"//入参类型Content-Type
  )
  public User userToProperties(@RequestBody User user) {
    return user;
  }

  @PostMapping(
      value = "user/json/to/properties",
      consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
      produces = "application/person+properties"
  )
  public User propertiesToUser(@RequestBody User user) {
    return user;
  }
}
