package com.cmq.whatever.uc.controllers;

import com.cmq.whatever.uc.https.results.BaseResult;
import com.cmq.whatever.uc.services.ValidatePhoneService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by cuimingqiang on 16/5/31.
 */
@RestController
@RequestMapping(value = "/user",produces = {"application/json"})
public class ValidatePhoneController {

    private static Logger logger = Logger.getLogger(ValidatePhoneController.class);

    @Autowired
    ValidatePhoneService validatePhoneService;

    /**
     * 获取验证码
     * @param phone
     * @return
     */
    @RequestMapping(value = "/getCode/{phone}/{type}",method = {RequestMethod.GET})
    public BaseResult getCode(@PathVariable("phone")String phone, @PathVariable("type")String type){
        logger.info(type);
        return  validatePhoneService.getCode(phone,type);
    }

    /**
     * 验证手机号是否为本人
     * @param map
     * @return
     */
    @RequestMapping(value = "/validateCode",method = {RequestMethod.POST})
    public BaseResult validateCode(@RequestBody Map map){
        logger.info(map);
        String phone = (String) map.get("phone");
        String code = (String) map.get("code");
        return validatePhoneService.validateCode(phone,code);
    }

    /**
     * 验证手机号是否已注册
     * @param phone
     * @return
     */
    @RequestMapping(value = "/validatePhone/{phone}",method = RequestMethod.GET)
    public BaseResult validatePhone(@PathVariable("phone")String phone){
        return validatePhoneService.validatePhone(phone);
    }
}
