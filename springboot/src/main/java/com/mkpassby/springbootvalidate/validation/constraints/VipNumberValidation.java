package com.mkpassby.springbootvalidate.validation.constraints;

import com.mkpassby.springbootvalidate.validation.VipNumberConstraintValidatorImpl;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * 自定义校验
 *
 * @program: springboot
 * @description:
 * @author: mk_passby
 * @create: 2019-10-09 21:23
 **/
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(
    validatedBy = {VipNumberConstraintValidatorImpl.class}
)
public @interface VipNumberValidation {

    String message() default "{com.mkpassby.springbootvalidate.validation.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
