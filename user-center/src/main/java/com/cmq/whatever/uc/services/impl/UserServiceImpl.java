package com.cmq.whatever.uc.services.impl;

import com.cmq.whatever.uc.https.params.LoginParam;
import com.cmq.whatever.uc.https.params.RegisterParam;
import com.cmq.whatever.uc.https.results.BaseResult;
import com.cmq.whatever.uc.repositories.UserJPARepository;
import com.cmq.whatever.uc.services.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by cuimingqiang on 16/5/30.
 */
@Service
public class UserServiceImpl implements UserService{
    private static Logger logger = Logger.getLogger(UserServiceImpl.class);


    @Autowired
    UserJPARepository repository;


    @Override
    public BaseResult register(RegisterParam param) {
        BaseResult result = new BaseResult();

        return result;
    }

    @Override
    public BaseResult users() {
        BaseResult result = new BaseResult();
        return result;
    }

    @Override
    public BaseResult login(LoginParam param) {
        BaseResult result = new BaseResult();


        return result;
    }

}
