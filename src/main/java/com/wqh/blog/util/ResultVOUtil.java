package com.wqh.blog.util;

import com.wqh.blog.vo.ResultVo;

/**
 * @author wqh
 * @Date 2017/10/30 21:46
 * @Description:
 */
public  class ResultVOUtil {

    public static ResultVo success(Object object){
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(200);
        resultVo.setMsg("成功");
        resultVo.setData(object);
        return resultVo;
    }

    public static ResultVo success(){
        return ResultVOUtil.success(null);
    }

    public static ResultVo error(Integer code,String msg){
        return  new ResultVo(code,msg);
    }
}
