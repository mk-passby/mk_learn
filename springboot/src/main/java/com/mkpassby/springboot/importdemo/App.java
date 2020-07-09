package com.mkpassby.springboot.importdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

/**
 * @program: springboot
 * @description: test
 * @author: mk_passby
 * @create: 2019-06-23 11:57
 **/
@SpringBootApplication
@Import(UserImportBeanDefinitionRegistrar.class)
public class App {

    public static void main(String[] args) {
        ConfigurableApplicationContext configurableApplicationContext = SpringApplication
            .run(App.class, args);
        System.out.println(configurableApplicationContext.getBean(User.class));
        System.out.println(configurableApplicationContext.getBean(User.class).getName());
        configurableApplicationContext.close();
    }
}
