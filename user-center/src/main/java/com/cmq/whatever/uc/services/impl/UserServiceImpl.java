package com.cmq.whatever.uc.services.impl;

import com.cmq.whatever.uc.entities.UserEntity;
import com.cmq.whatever.uc.https.params.RegisterParam;
import com.cmq.whatever.uc.https.results.BaseResult;
import com.cmq.whatever.uc.repositories.UserRepository;
import com.cmq.whatever.uc.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by cuimingqiang on 16/5/30.
 */
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    BaseResult result;


    @Autowired
    UserRepository repository;

    @Autowired
    StringRedisTemplate redisTemplate;

    @Override
    public BaseResult register(RegisterParam param) {
        result.reset();

        UserEntity user = new UserEntity();
        user.setPhone(param.getPhone());
        user.setPassword(param.getPassword());
        user.setNickname(param.getNickname());
        user.setRegisterDevice(param.getDevice());
        user.setRegisterDeviceId(param.getDeviceId());
        user.setRegisterTime(new Date());

        user = repository.save(user);
        result.setData(user);
        return result;
    }

    @Override
    public BaseResult validatePhone(String phone) {
        result.reset();
        if(repository.findUserByPhone(phone) != null){
            result.setCode(100);
            result.setMsg("用户已存在");
        }
        return result;
    }
}
