package com.wqh.blog.handle;

import com.wqh.blog.enums.ResultEnum;
import com.wqh.blog.exception.BusinessException;
import com.wqh.blog.util.ResultVOUtil;
import com.wqh.blog.vo.ResultVo;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author wqh
 * @Date 2017/10/30 22:34
 * @Description: 统一处理控制层的异常
 */
@ControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultVo handle(Exception e){
        if (e instanceof  BusinessException){
            BusinessException businessException  = (BusinessException) e;
            return ResultVOUtil.error(businessException.getCode(),businessException.getMessage());
        }else {
            //未知错误
            e.printStackTrace();
            return  ResultVOUtil.error(ResultEnum.UNKNOWN_ERROR.getCode(),ResultEnum.UNKNOWN_ERROR.getMsg());
        }

    }
}
