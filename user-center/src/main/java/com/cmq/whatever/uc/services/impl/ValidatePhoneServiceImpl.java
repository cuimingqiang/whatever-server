package com.cmq.whatever.uc.services.impl;

import com.cmq.whatever.uc.https.results.BaseResult;
import com.cmq.whatever.uc.repositories.UserJPARepository;
import com.cmq.whatever.uc.services.ValidatePhoneService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Created by cuimingqiang on 16/5/31.
 */
@Service
public class ValidatePhoneServiceImpl implements ValidatePhoneService{

    private static Logger logger = Logger.getLogger(ValidatePhoneServiceImpl.class);
    private static final String CODE = "code";


    @Autowired
    UserJPARepository repository;

    @Cacheable(cacheNames = CODE,keyGenerator = "keyGenerator")
    @Override
    public BaseResult getCode(String phone, String type) {
        BaseResult result = new BaseResult();
        logger.info("-----getcode");
        if("type".equals("register")){

        }
        result.setData(String.format("%d%d%d%d",getAbsInt(),getAbsInt(),getAbsInt(),getAbsInt()));
        return result;
    }


    private void sendCode(String phone){

    }

    @Override
    public BaseResult validateCode(String phone, String code) {
        BaseResult result = new BaseResult();

        return result;
    }

    @Override
    public BaseResult validatePhone(String phone) {
        BaseResult result = new BaseResult();
        return result;
    }


    public int getAbsInt(){
        return 1;
    }


}
