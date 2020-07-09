package com.mkpassby.springboot.config;

import com.mkpassby.springboot.messageconvert.PropertiesToUserConverter;
import java.util.List;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @program: springboot
 * @description:
 * @author: mk_passby
 * @create: 2019-06-30 18:45
 **/
@Configuration
public class Test1 extends WebMvcConfigurationSupport {
  @Override
  public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    converters.add(new PropertiesToUserConverter());
    super.configureMessageConverters(converters);
  }
}
