package com.cmq.whatever.uc.services;

/**
 * Created by cuimingqiang on 16/5/31.
 */
public interface ValidatePhoneService {
    Object getCode(String phone, String type)throws Exception;

    Object validateCode(String phone, String code)throws Exception;

    Boolean validatePhone(String phone);
}
