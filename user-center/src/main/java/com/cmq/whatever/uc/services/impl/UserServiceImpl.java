package com.cmq.whatever.uc.services.impl;

import com.cmq.whatever.uc.https.params.RegisterParam;
import com.cmq.whatever.uc.https.results.BaseResult;
import com.cmq.whatever.uc.repositories.UserRepository;
import com.cmq.whatever.uc.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by cuimingqiang on 16/5/30.
 */
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    BaseResult result;

    @Autowired
    UserRepository repository;
    @Override
    public BaseResult register(RegisterParam param) {
        result.reset();


        return result;
    }
}
