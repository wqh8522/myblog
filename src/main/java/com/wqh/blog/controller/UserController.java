package com.wqh.blog.controller;

import com.wqh.blog.domain.User;
import com.wqh.blog.enums.ResultEnum;
import com.wqh.blog.exception.BusinessException;
import com.wqh.blog.service.UserService;
import com.wqh.blog.util.ResultVOUtil;
import com.wqh.blog.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author wqh
 * @Date 2017/10/20 15:18
 * @Description: 用户控制器
 */
@RestController
@RequestMapping("/u")
@Slf4j
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    /**
     * 保存用户信息
     * @param user
     * @param bindingResult
     * @return
     */
    @PostMapping(value = "save")
    public ResultVo saveUser(@Valid User user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.error("【用户注册】参数不正确, user={}", user);
            throw new BusinessException(ResultEnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }
        User saveUser = userService.save(user);
        log.info("用户注册成功 , user={}", user);
        return ResultVOUtil.success(saveUser);
    }

}
