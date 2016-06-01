package com.cmq.whatever.uc.services.impl;

import com.cmq.whatever.uc.https.results.BaseResult;
import com.cmq.whatever.uc.repositories.UserJPARepository;
import com.cmq.whatever.uc.services.ValidatePhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by cuimingqiang on 16/5/31.
 */
@Service
public class ValidatePhoneServiceImpl implements ValidatePhoneService{
    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    UserJPARepository repository;

    @Autowired
    BaseResult result;

    @Autowired
    Random random;

    @Override
    public BaseResult getCode(String phone, String type) {
        result.reset();
        if("register".equals(type)){
            if(repository.findUserByPhone(phone) != null){
                result.setCode(100);
                result.setMsg("用户已存在");
                return result;
            }
            sendCode(phone);
        }else {
            result.setCode(100);
            result.setMsg("请求不合法");
        }

        return result;
    }


    private void sendCode(String phone){
        String key = String.format("code#%s",phone);
        String code = redisTemplate.opsForValue().get(key);
        if(code == null) {
            code = String.format("%d%d%d%d", getAbsInt(), getAbsInt(), getAbsInt(), getAbsInt());
            redisTemplate.opsForValue().set(key,code,15*60, TimeUnit.SECONDS);
            result.setData(code);
        }else {
            result.setCode(100);
            result.setMsg("你已经获取过验证码了");
        }
    }

    @Override
    public BaseResult validateCode(String phone, String code) {
        result.reset();
        String key = String.format("code#%s",phone);
        String redisCode = redisTemplate.opsForValue().get(key);
        if(redisCode == null){//redis 里找不到验证码,表面已失效,1,过时 2已使用过
            result.setCode(100);
            result.setMsg("验证码已失效,请重新获取验证码!");
        }else if(!redisCode.equals(code)){
            result.setCode(100);
            result.setMsg("验证码不正确!");
        }else{
            Map map = new HashMap<>();
            String keyToken = String.format("registerToken",phone);
            map.put(keyToken, UUID.randomUUID().toString());
            redisTemplate.opsForValue().set(String.format("%s#%s",keyToken,phone),(String) map.get(keyToken),60*60,TimeUnit.SECONDS);
            redisTemplate.opsForValue().getOperations().delete(key);

            result.setData(map);
        }
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

    public int getAbsInt(){
        return Math.abs(random.nextInt()%10);
    }

    @Bean
    public Random getRandom(){
        return  new Random();
    }
}
