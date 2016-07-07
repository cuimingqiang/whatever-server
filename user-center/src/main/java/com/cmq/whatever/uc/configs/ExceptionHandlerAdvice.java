package com.cmq.whatever.uc.configs;

import com.cmq.whatever.uc.https.results.BaseResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by admin on 16/7/3.
 */
@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(value = Exception.class)
    public Object exception(Exception exception){
        BaseResult baseResult = new BaseResult();
        baseResult.setMsg(exception.getMessage());
        System.out.println("-----------exception----------");
        System.out.println(exception.getCause());
        System.out.println("-----------exception----------");
        return baseResult;
    }


}
