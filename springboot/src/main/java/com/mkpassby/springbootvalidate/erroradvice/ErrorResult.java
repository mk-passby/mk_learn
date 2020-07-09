package com.mkpassby.springbootvalidate.erroradvice;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: springcloud-demo
 * @description:
 * @author: mk_passby
 * @create: 2020-06-01 00:17
 **/
public class ErrorResult {

    private List<MyErrorCode> errorCodeList = new ArrayList<>();

    public ErrorResult() {
    }

    public void addError(String errorCode, String message) {
        MyErrorCode error = new MyErrorCode(errorCode, message);
        errorCodeList.add(error);
    }

    public List<MyErrorCode> getErrors() {
        return errorCodeList;
    }

    public void addError(MyErrorCode error) {
        errorCodeList.add(error);
    }

}
