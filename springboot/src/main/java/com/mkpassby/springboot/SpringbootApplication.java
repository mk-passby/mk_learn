package com.mkpassby.springboot;

import com.mkpassby.springboot.importdemo.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
	}
	@GetMapping("/")
  public String getTest(){
	  return "aasdfadfas";
  }
  @GetMapping("/user")
  public User getUser(){
    return new User();
  }
}
