package com.wqh.blog.aspect;

import com.wqh.blog.annotation.AnnotationParse;
import com.wqh.blog.domain.User;
import com.wqh.blog.enums.ResultEnum;
import com.wqh.blog.enums.RoleEnum;
import com.wqh.blog.exception.BusinessException;
import com.wqh.blog.exception.LoginException;
import com.wqh.blog.service.UserService;
import com.wqh.blog.util.ResultVOUtil;
import com.wqh.blog.util.WebUtil;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Method;

/**
 * 请求的AOP处理类
 * @author wqh
 */
@Aspect
@Component
public class ControllerAspect {

    private final static Logger logger = LoggerFactory.getLogger(ControllerAspect.class);

    @Autowired
    private UserService userService;
    /**
     * 定义切点
     */
    @Pointcut("execution(public * com.wqh.blog.controller.*.*(..))")
    public void privilege(){}

    /**
     * 权限环绕通知
     * @param joinPoint
     * @throws Throwable
     */
    @ResponseBody
    @Around("privilege()")
    public void isAccessMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        //获取访问目标方法
        MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
        Method targetMethod = methodSignature.getMethod();
        //得到方法的访问权限
        final String methodAccess = AnnotationParse.privilegeParse(targetMethod);

        //如果该方法上没有权限注解，直接调用目标方法
        if(StringUtils.isBlank(methodAccess)){
            joinPoint.proceed();
        }else {
            //获取当前用户的权限
            User currentUser = userService.getCurrentUser();
            logger.info("访问用户，{}",currentUser.toString());
            if(currentUser == null){
                throw new LoginException(ResultEnum.LOGIN_ERROR);
            }
            if(methodAccess.equals(currentUser.getRole().toString())){
                joinPoint.proceed();
            }else {
                throw new BusinessException(ResultEnum.ROLE_ERROR);
            }
        }
    }


}

