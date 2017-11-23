package com.wqh.blog.vo;

/**
 * @author wqh
 * @Date 2017/10/30 21:39
 * @Description: http请求统一的返回类
 */
public class ResultVo {


    /**返回状态码*/
    private Integer code;
    /**返回的信息*/
    private String msg;
    /**返回的数据*/
    private Object data;

    public ResultVo() {
    }

    public ResultVo(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
