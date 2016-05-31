package com.cmq.whatever.uc.controllers;

import com.cmq.whatever.uc.https.params.RegisterParam;
import com.cmq.whatever.uc.https.results.BaseResult;
import com.cmq.whatever.uc.services.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by admin on 16/5/29.
 */
@RestController
@RequestMapping(value = "/user",produces = {"application/json"})
public class UserController {

    private static Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    UserService userService;

    /**
     * 注册用户
     * @param param
     * @return
     */
    @RequestMapping(value = "/register",method = {RequestMethod.POST})
    public BaseResult register(@RequestBody RegisterParam param){

        return userService.register(param);
    }

    /**
     * 验证手机号是否已注册
     * @param phone
     * @return
     */
    @RequestMapping(value = "/validatePhone/{phone}",method = RequestMethod.GET)
    public BaseResult validatePhone(@PathVariable("phone")String phone){
        return userService.validatePhone(phone);
    }
}
