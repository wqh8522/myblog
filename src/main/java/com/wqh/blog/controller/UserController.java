package com.wqh.blog.controller;

import com.wqh.blog.domain.User;
import com.wqh.blog.enums.ResultEnum;
import com.wqh.blog.enums.RoleEnum;
import com.wqh.blog.exception.BusinessException;
import com.wqh.blog.service.UserService;
import com.wqh.blog.util.Constants;
import com.wqh.blog.util.MatcherUtil;
import com.wqh.blog.util.ResultVOUtil;
import com.wqh.blog.util.UserUtil;
import com.wqh.blog.vo.LoginVO;
import com.wqh.blog.vo.ResultVo;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;

/**
 * @author wanqh
 * @date 2017/11/23 21:29
 * @description: 用户控制器
 */
@RestController
@RequestMapping(value = "u")
public class UserController {

    @Autowired
    private UserService userService;
    /**
     * 用户注册
     * @param user
     * @param bindingResult
     * @return
     */
    @PostMapping("register")
    public ResultVo register(@Valid User user, BindingResult bindingResult){
        if(bindingResult.hasErrors() || StringUtils.isBlank(user.getEmail())){
            throw new BusinessException(ResultEnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }
        //验证用户名邮箱是否存在
        User vaidate_user = new User();
        vaidate_user.setUsername(user.getUsername());
        User is_user= userService.get(vaidate_user);
        if(is_user != null && StringUtils.isNotBlank(is_user.getId())){
            throw new BusinessException(ResultEnum.PARAM_ERROR.getCode(),"用户名已经被注册");
        }
        User vaidate_user1 = new User();
        vaidate_user1.setEmail(user.getEmail());
        is_user= userService.get(vaidate_user1);
        if(is_user != null && StringUtils.isNotBlank(is_user.getId())){
            throw new BusinessException(ResultEnum.PARAM_ERROR.getCode(),"邮箱已经被注册");
        }
        //设置权限为用户
        user.setRole(RoleEnum.USER);
        //使用BCrypt加密密码
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        userService.save(user);
        return ResultVOUtil.success();
    }

    /**
     * 用户登录，使用用户名或者邮箱都可以登录
     * @param usernameOrEmail
     * @param password
     * @return
     */
    @PostMapping("login")
    public ResultVo login(@RequestParam(value = "usernameOrEmail",required = false) String usernameOrEmail,
                          @RequestParam(value = "password", required = true) String password,
                          HttpServletRequest request){
        Boolean is_email = MatcherUtil.matcherEmail(usernameOrEmail);
        User user = new User();
        if(is_email){
            user.setEmail(usernameOrEmail);
        }else {
            user.setUsername(usernameOrEmail);
        }
        User query_user = userService.get(user);
        if(query_user == null){
            return ResultVOUtil.error("400","用户名或邮箱错误");
        }
        //验证密码
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        boolean is_password = encoder.matches(password, query_user.getPassword());
        if(!is_password){
            //密码错误，返回提示
            return ResultVOUtil.error("400","密码错误");
        }
        //用户登录成功，将用户保存到session中，然后返回token
       /* HttpSession session = request.getSession();
        String token = Constants.getUUID();
        LoginVO loginVO = new LoginVO();
        loginVO.setToken(token);
        loginVO.setUsername(query_user.getUsername());
        loginVO.setId(query_user.getId());
        loginVO.setEmail(query_user.getEmail());
        loginVO.setRole(query_user.getRole().toString());
        session.setAttribute("user",loginVO);*/
        //拼装accessToken

    String jwtToken = Jwts.builder().setSubject(query_user.getUsername()).claim("roles", query_user.getRole().toString())
            .setIssuedAt(new Date())
            .signWith(SignatureAlgorithm.HS256, "secretkey").compact();
        return ResultVOUtil.success("bearer;"+jwtToken);
}

    @RequestMapping("test")
    public ResultVo test(String token){
        Boolean validate = UserUtil.validate(token);
        return ResultVOUtil.success(validate);
    }

}
