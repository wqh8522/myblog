package com.wqh.blog.exception;

import com.wqh.blog.enums.ResultEnum;

/**
 * @author wqh
 * @Date 2017/10/19 17:19
 * @Description: 用户登录异常
 */
public class LoginException extends RuntimeException{
    private String code;

    public LoginException(ResultEnum resultEnum){
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public LoginException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
