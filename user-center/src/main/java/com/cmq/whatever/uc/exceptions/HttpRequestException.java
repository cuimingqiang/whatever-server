package com.cmq.whatever.uc.exceptions;

/**
 * Created by admin on 16/7/3.
 */
public class HttpRequestException extends Exception{
    private Integer code;

    public HttpRequestException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
