package com.mkpassby.springboot.config;

import com.mkpassby.springboot.messageconvert.PropertiesToUserConverter;
import com.mkpassby.springboot.messageconvert.UserToPropertiesConverter;
import java.util.List;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @program: springboot
 * @description:
 * @author: mk_passby
 * @create: 2019-06-25 22:23
 **/
@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer {
  @Override
  public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    converters.add(new PropertiesToUserConverter());

  }
  @Override
  public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
    converters.add(new PropertiesToUserConverter());

  }

}
