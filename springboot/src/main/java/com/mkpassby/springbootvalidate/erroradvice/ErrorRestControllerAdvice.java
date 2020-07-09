package com.mkpassby.springbootvalidate.erroradvice;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @program: springcloud-demo
 * @description: 错误码切面
 * @author: mk_passby
 * @create: 2020-05-31 21:49
 **/
@RestControllerAdvice(annotations = {RestController.class})
public class ErrorRestControllerAdvice {

    private MessageSource messageSource;

    @Autowired
    public ErrorRestControllerAdvice(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(Exception.class)
    public @ResponseBody
    ErrorResult processException(Exception exception) {
        List<FieldError> filedErrorList = null;
        System.out.println("MyException are doing");
        ErrorResult errorResult = new ErrorResult();
        if (exception instanceof BindException) {
            BindException bindException =
                (BindException) exception;
            filedErrorList =
                bindException.getBindingResult().getFieldErrors();
        }
        if (exception instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException argumentNotValidException =
                (MethodArgumentNotValidException) exception;
            filedErrorList =
                argumentNotValidException.getBindingResult().getFieldErrors();
        }

        if (filedErrorList != null) {
            for (FieldError fieldError : filedErrorList) {
                MyErrorCode myErrorCode = new MyErrorCode(
                    fieldError.getCode(),
                    messageSource.getMessage(fieldError, LocaleContextHolder.getLocale()));
                errorResult.addError(myErrorCode);
            }
            return errorResult;
        }
        return errorResult;

    }


}
