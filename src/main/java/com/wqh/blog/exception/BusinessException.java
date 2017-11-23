package com.wqh.blog.exception;

import com.wqh.blog.enums.ResultEnum;

/**
 * @author wqh
 * @Date 2017/10/19 17:19
 * @Description: 业务异常
 */
public class BusinessException extends RuntimeException{
    private Integer code;

    BusinessException(ResultEnum resultEnum){
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public BusinessException( Integer code,String message) {
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
