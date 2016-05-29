package com.cmq.whatever.uc.controllers;

import com.cmq.whatever.uc.https.params.RegisterParam;
import com.cmq.whatever.uc.https.results.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by admin on 16/5/29.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    BaseResult result;

    @RequestMapping(value = "/register",method = {RequestMethod.POST})
    public BaseResult register(@RequestBody RegisterParam param){
        result.reset();

        return result;
    }
}
