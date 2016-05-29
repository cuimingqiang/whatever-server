package com.cmq.whatever.uc.https.results;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * Created by admin on 16/5/29.
 */

@Component
public class BaseResult<T> {
    private Integer code;
    private String errMsg;
    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void reset(){
        code = HttpStatus.OK.value();
        errMsg = HttpStatus.OK.name();
    }
}
