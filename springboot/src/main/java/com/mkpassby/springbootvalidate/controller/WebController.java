package com.mkpassby.springbootvalidate.controller;

import com.mkpassby.springbootvalidate.domain.AAAA;
import com.mkpassby.springbootvalidate.domain.User;
import com.mkpassby.springbootvalidate.erroradvice.MyException;
import com.mkpassby.springbootvalidate.validation.constraints.VipNumberValidation;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: springboot
 * @description:
 * @author: mk_passby
 * @create: 2019-10-09 20:48
 **/
@RestController
public class WebController {

    @PostMapping("/getUser")
    public User getUser(@Valid @RequestBody User user) {
        return user;
    }

    @GetMapping("/test")
    public String test() {
        return "hello";
    }

    @GetMapping("nullPointTest")
    public String nullPoint() {
        throw new NullPointerException();
    }

    @GetMapping("myExceptionTest")
    public String myExceptionTest(@Valid  AAAA a) {
        System.out.println(a);
        //throw new MyException("error.1301", "bbb");
        return "{error.1301}";
    }
}
