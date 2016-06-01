package com.cmq.whatever.uc.services.impl;

import com.cmq.whatever.uc.entities.UserEntity;
import com.cmq.whatever.uc.https.params.LoginParam;
import com.cmq.whatever.uc.https.params.RegisterParam;
import com.cmq.whatever.uc.https.results.BaseResult;
import com.cmq.whatever.uc.repositories.UserJPARepository;
import com.cmq.whatever.uc.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by cuimingqiang on 16/5/30.
 */
@Service
public class UserServiceImpl implements UserService{
    private static Logger logger = Logger.getLogger(UserServiceImpl.class);

    @Autowired
    BaseResult result;


    @Autowired
    UserJPARepository repository;

    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public BaseResult register(RegisterParam param) {
        result.reset();
        String keyToken = String.format("registerToken#%s",param.getPhone());
        String registerToken = redisTemplate.opsForValue().get(keyToken);
        if(registerToken == null || !registerToken.equals(param.getRegisterToken())){
            result.setCode(100);
            result.setMsg("注册不合法");
        }else {
            UserEntity user = new UserEntity();
            user.setToken(UUID.randomUUID().toString());
            user.setPhone(param.getPhone());
            user.setPassword(param.getPassword());
            user.setNickname(param.getNickname());
            user.setRegisterDevice(param.getDevice());
            user.setRegisterDeviceId(param.getDeviceId());
            user.setRegisterTime(new Date());

            user = repository.save(user);
            result.setData(user);

            redisTemplate.delete(keyToken);

//            try {
//                String userString = objectMapper.writer().writeValueAsString(user);
//                redisTemplate.opsForValue().set(user.getPhone(), userString,30, TimeUnit.DAYS);
//            }catch (Exception e){
//                e.printStackTrace();
//            }


        }
        return result;
    }

    @Override
    public BaseResult users() {
        result.reset();
        return result;
    }

    @Override
    public BaseResult login(LoginParam param) {
        result.reset();
        String userString = redisTemplate.opsForValue().get(param.getPhone());
        UserEntity user = null;
        if(userString != null){
            try {
                user = objectMapper.readerFor(UserEntity.class).readValue(userString);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        if(user == null){
            user = repository.findUserByPhone(param.getPhone());
            try {
                redisTemplate.opsForValue().set(param.getPhone(),objectMapper.writeValueAsString(user));
            }catch (Exception e){
                e.printStackTrace();
            }

        }

        if(user != null) {
            redisTemplate.expire(param.getPhone(), 30, TimeUnit.DAYS);
            if(!param.getPassword().equals(user.getPassword())){
                result.setMsg("密码错误");
                result.setCode(100);
            }else {
                if(user.getToken() != null){
                    redisTemplate.delete(user.getToken());
                }
                user.setToken(UUID.randomUUID().toString());
                redisTemplate.opsForValue().set(user.getToken(),user.getuId().toString(),30,TimeUnit.DAYS);
            }
        }else {
            result.setCode(100);
            result.setMsg("账号不存在");
        }

        result.setData(user);
        return result;
    }

    @Bean
    public ObjectMapper getObjectWriter(){
        return new ObjectMapper();
    }
}
