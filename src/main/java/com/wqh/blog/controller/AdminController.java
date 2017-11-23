package com.wqh.blog.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wqh
 * @Date 2017/11/02 22:19
 * @Description: 后台管理控制器,该可所有的方法只有admin用户可以访问
 */
@RequestMapping(value = "admin")
@RestController
public class AdminController {


}
