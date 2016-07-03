package com.cmq.whatever.uc.https.results;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.validation.constraints.Null;

/**
 * Created by admin on 16/5/29.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResult<T> {
    private Integer code = HttpStatus.OK.value();
    private String msg = HttpStatus.OK.name();
    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
