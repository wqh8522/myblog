package com.wqh.blog.controller;

import com.wqh.blog.domain.Article;
import com.wqh.blog.domain.Category;
import com.wqh.blog.domain.User;
import com.wqh.blog.enums.ResultEnum;
import com.wqh.blog.exception.BusinessException;
import com.wqh.blog.service.ArticleService;
import com.wqh.blog.service.CategoryService;
import com.wqh.blog.service.UserService;
import com.wqh.blog.util.ResultVOUtil;
import com.wqh.blog.vo.ResultVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author wanqh
 * @date 2017/11/26 17:03
 * @description: 文章控制器
 */

@RestController
@RequestMapping("article")
public class ArticleController  {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    /**
     * 首页文章列表
     * @return
     */
    public ResultVo articleList(){

        return ResultVOUtil.success();
    }

    /**
     * 发表文章，需要登陆
     * @param article
     * @return
     */
    @PostMapping("insert")
    public ResultVo insert(@Valid Article article,
                           @RequestParam(value = "catrgoryId") String catrgoryId,
                           BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new BusinessException(ResultEnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }
        //处理作者信息
        User currentUser = userService.getCurrentUser();
        if(currentUser == null || StringUtils.isBlank(currentUser.getId())){
            return ResultVOUtil.error(ResultEnum.PARAM_ERROR.getCode(),"请先登录");
        }
        article.setAuthor(currentUser);
        //处理分类信息
        if(StringUtils.isBlank(catrgoryId)){
            return ResultVOUtil.error(ResultEnum.PARAM_ERROR.getCode(),"请选择分类");
        }
        Category category = categoryService.get(catrgoryId);
        if(category == null){
            return ResultVOUtil.error(ResultEnum.PARAM_ERROR.getCode(),"请选择正确的分类");
        }
        article.setCategory(category);
        articleService.save(article);
        return ResultVOUtil.success();
    }

}
