package com.cmq.whatever.uc.controllers;

import com.cmq.whatever.uc.entities.UserEntity;
import com.cmq.whatever.uc.https.params.LoginParam;
import com.cmq.whatever.uc.https.params.RegisterParam;
import com.cmq.whatever.uc.https.results.BaseResult;
import com.cmq.whatever.uc.services.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

/**
 * Created by admin on 16/5/29.
 */
@RestController
@RequestMapping(value = "/user",produces = {"application/json"})
public class UserController {

    private static Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @Autowired
    RedisTemplate redisTemplate;
    /**
     * 注册用户
     * @param param
     * @return
     */
    @RequestMapping(value = "/register",method = {RequestMethod.POST})
    public BaseResult register(@RequestBody RegisterParam param){

        return userService.register(param);
    }

    @RequestMapping(value = "/login",method = {RequestMethod.POST})
    public BaseResult login(@RequestBody LoginParam param){
        return userService.login(param);
    }

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public void test(){
       UserEntity entity = new UserEntity();
        entity.setToken("asdasdasd");
        redisTemplate.opsForSet().add("a",entity);
        UserEntity userEntity = (UserEntity) redisTemplate.opsForSet().pop("a");
        logger.info(userEntity.getToken());

    }

    @RequestMapping(value = "/users",method = RequestMethod.GET)
    public BaseResult users(){
        return userService.users();
    }
}
