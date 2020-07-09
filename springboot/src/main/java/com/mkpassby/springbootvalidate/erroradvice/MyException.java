package com.mkpassby.springbootvalidate.erroradvice;

import com.sun.javafx.binding.StringFormatter;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.ResourceBundle;
import org.springframework.context.MessageSourceResolvable;

/**
 * @program: springcloud-demo
 * @description:
 * @author: mk_passby
 * @create: 2020-05-31 22:19
 **/
public class MyException extends Exception{
    private String errorCode;
    private String[] messages;
    private static final ResourceBundle mySource= ResourceBundle.getBundle("i18n/myError");
    public MyException(String errorCode, String... message) {
        this.errorCode = errorCode;
        this.messages = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getMessages() {
        if (messages==null||messages.length==0){
            return mySource.getString(errorCode);
        }
        return MessageFormat.format(mySource.getString(errorCode),messages);
    }
}
