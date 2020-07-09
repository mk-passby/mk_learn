package com.mkpassby.springbootvalidate.validation;

import com.mkpassby.springbootvalidate.validation.constraints.VipNumberValidation;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.util.StringUtils;

/**
 * @program: springboot
 * @description: vipnumber校验器
 * @author: mk_passby
 * @create: 2019-10-09 21:36
 **/
public class VipNumberConstraintValidatorImpl implements
    ConstraintValidator<VipNumberValidation, String> {

    @Override
    public void initialize(VipNumberValidation constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        String[] contents = StringUtils.delimitedListToStringArray(value, "-");
        if (contents.length == 2
            && "Vip".equals(contents[0])
            && contents[1]!=null
            && contents[1].matches("^[0-9]+$")) {
            return true;
        }
        return false;
    }
}
