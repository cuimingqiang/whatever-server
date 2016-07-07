package com.cmq.whatever.uc.services.impl;

import com.cmq.whatever.uc.entities.SMSEntity;
import com.cmq.whatever.uc.exceptions.HttpRequestException;
import com.cmq.whatever.uc.repositories.SMSJPARepository;
import com.cmq.whatever.uc.repositories.UserJPARepository;
import com.cmq.whatever.uc.services.ValidatePhoneService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by cuimingqiang on 16/5/31.
 */
@Service
public class ValidatePhoneServiceImpl implements ValidatePhoneService{

    private static Logger logger = Logger.getLogger(ValidatePhoneServiceImpl.class);
    private static final String CODE = "code";

    @Bean
    public static Random random(){
        return new Random();
    }

    @Autowired
    UserJPARepository userJPARepository;

    @Autowired
    SMSJPARepository smsjpaRepository;

    @Autowired
    Random random;

    @Autowired
    @Qualifier(value = "codeCacheManager")
    RedisCacheManager cacheManager;

    @Cacheable(value = CODE,key = "#phone",cacheManager = "codeCacheManager")
    @Override
    public Object getCode(String phone, String type) throws Exception{
        String result;
        if(type.equals("register")){
            Object user = userJPARepository.findUserByPhone(phone);
            if(user != null)
                throw new HttpRequestException("用户已注册",100);
        }
        result = getCode();
        SMSEntity entity = smsjpaRepository.findSMSByPhone(phone);
        if(entity == null)entity = new SMSEntity();
        entity.code = result;
        entity.phone = phone;
        entity.createTime = new Date(System.currentTimeMillis());
        smsjpaRepository.saveAndFlush(entity);
        return result;
    }

    @Caching(evict = {
                   @CacheEvict(value = CODE,key = "#phone",cacheManager = "codeCacheManager")},
            put = {
                   @CachePut(value = CODE,key = "#phone",cacheManager = "codeCacheManager")
            }
    )
    @Override
    public Object validateCode(String phone, String code) throws Exception{
        Cache.ValueWrapper wrapper = cacheManager.getCache(CODE).get(phone);
        if(wrapper == null || wrapper.get() == null || !wrapper.get().equals(code)){
            throw new HttpRequestException("验证码已过期",100);
        }
        Map map = new HashMap<>();
        map.put("registerToken",UUID.randomUUID().toString());
        return map;
    }

    @Override
    public Boolean validatePhone(String phone) {

        return null;
    }


    private String getCode(){
        return String.format("%d%d%d%d",getAbsInt(),getAbsInt(),getAbsInt(),getAbsInt());
    }

    public int getAbsInt(){
        return  Math.abs(random.nextInt()%10);
    }

}
