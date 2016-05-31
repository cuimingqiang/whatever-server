package com.cmq.whatever.uc.services;

import com.cmq.whatever.uc.https.results.BaseResult;

/**
 * Created by cuimingqiang on 16/5/31.
 */
public interface ValidatePhoneService {
    BaseResult getCode(String phone, String type);

    BaseResult validateCode(String phone, String code);

    BaseResult validatePhone(String phone);
}
