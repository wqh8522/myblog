package com.wqh.blog.exception;

import com.wqh.blog.enums.ResultEnum;

/**
 * @author wqh
 * @Date 2017/10/19 17:19
 * @Description: 业务异常
 */
public class BusinessException extends RuntimeException{
    private String code;

    BusinessException(ResultEnum resultEnum){
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public BusinessException( String code,String message) {
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
