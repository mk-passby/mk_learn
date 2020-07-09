package com.mkpassby.springboot.importdemo;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @program: springboot
 * @description:
 * @author: mk_passby
 * @create: 2019-06-23 12:27
 **/
public class UserImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

  @Override
  public void registerBeanDefinitions(AnnotationMetadata annotationMetadata,
      BeanDefinitionRegistry beanDefinitionRegistry) {

    beanDefinitionRegistry.registerBeanDefinition("User",new RootBeanDefinition(User.class));
  }
}
