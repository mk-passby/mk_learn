package com.mkpassby.springbootvalidate.domain;

import com.mkpassby.springbootvalidate.validation.constraints.VipNumberValidation;

/**
 * @program: springcloud-demo
 * @description:
 * @author: mk_passby
 * @create: 2020-05-31 23:34
 **/
public class AAAA {
    @VipNumberValidation
    private String v;

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }
}
