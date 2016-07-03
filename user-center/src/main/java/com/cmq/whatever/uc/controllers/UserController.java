package com.cmq.whatever.uc.controllers;

import com.cmq.whatever.uc.https.params.LoginParam;
import com.cmq.whatever.uc.https.params.RegisterParam;
import com.cmq.whatever.uc.services.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    public Object register(@RequestBody RegisterParam param){

        return userService.register(param);
    }

    @RequestMapping(value = "/login",method = {RequestMethod.POST})
    public Object login(@RequestBody LoginParam param){
        return userService.login(param);
    }


    @RequestMapping(value = "/users",method = RequestMethod.GET)
    public Object users(){
        return userService.users();
    }
}
