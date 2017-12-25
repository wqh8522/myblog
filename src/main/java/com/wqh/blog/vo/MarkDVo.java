package com.wqh.blog.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wanqh
 * @date 2017/12/21 16:02
 * @description: markdown上传图片VO类
 * 格式
 *     {"success": 1, "message":"上传成功","url":"/resources/upload/" + attach.getOriginalFilename() + "\"}
 *     {"success":0}
 */
@Data
public class MarkDVo implements Serializable {


    private int success;
    private String message;
    private String url;

    private String callback;

}
