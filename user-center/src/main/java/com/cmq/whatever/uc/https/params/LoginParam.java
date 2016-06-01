package com.cmq.whatever.uc.https.params;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by cuimingqiang on 16/6/1.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginParam {
    private String phone;
    private String password;
    private String device;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }
}
