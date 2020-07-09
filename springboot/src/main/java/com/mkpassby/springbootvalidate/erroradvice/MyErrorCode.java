package com.mkpassby.springbootvalidate.erroradvice;

import javax.annotation.Resource;

/**
 * @program: springcloud-demo
 * @description:
 * @author: mk_passby
 * @create: 2020-05-31 22:45
 **/
public class MyErrorCode {
    private String errorCode;
    private String message;

    public MyErrorCode(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }
}
