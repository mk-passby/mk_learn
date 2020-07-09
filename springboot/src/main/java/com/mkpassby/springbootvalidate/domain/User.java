package com.mkpassby.springbootvalidate.domain;

import com.mkpassby.springbootvalidate.validation.constraints.VipNumberValidation;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import org.springframework.lang.NonNull;

/**
 * @program: springboot
 * @description:
 * @author: mk_passby
 * @create: 2019-10-09 20:44
 **/
public class User {
    @NotNull
    private String userName;
    @NonNull
    @VipNumberValidation
    private String vipNumber;
    @Max(value = 200)
    private int years;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getYears() {
        return years;
    }

    public void setYears(int years) {
        this.years = years;
    }

    public String getVipNumber() {
        return vipNumber;
    }

    public void setVipNumber(String vipNumber) {
        this.vipNumber = vipNumber;
    }
}
