package com.wqh.blog.controller;

import com.wqh.blog.domain.Article;
import com.wqh.blog.domain.Category;
import com.wqh.blog.domain.User;
import com.wqh.blog.enums.ResultEnum;
import com.wqh.blog.exception.BusinessException;
import com.wqh.blog.service.ArticleService;
import com.wqh.blog.service.CategoryService;
import com.wqh.blog.util.ResultVOUtil;
import com.wqh.blog.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @author wqh
 * @Date 2017/11/02 21:43
 * @Description:
 */
@RestController
@RequestMapping(value = "/article")
@Slf4j
public class ArticleController {

    private static final Logger log = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CategoryService categoryService;


    /**
     * 获取文章列表，也就是博客首页
     * @return
     */
    @GetMapping(value = "list/{page_num}")
    public ResultVo list(@PathVariable("page_num")Integer page_num){
        //分页查询文章，根据发表的时间排序
        Page<Article> articlePage = articleService.findAll(
                new PageRequest(Integer.valueOf(page_num-1),20,
                new Sort(Sort.Direction.DESC,"createTime")));
        return ResultVOUtil.success(articlePage);
    }

    /**
     * 添加文章,需要做登陆才能访问
     * @param article
     * @return
     */
    @PostMapping(value = "save")
    public ResultVo save(@Valid Article article,
                         @RequestParam(value = "category_id") String categoryId,
                         BindingResult bindingResult,
                         HttpServletRequest request){
        if(bindingResult.hasErrors()){
            log.error("【添加博客】参数错误，{}",article);
            throw new BusinessException(ResultEnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }
        //TODO 处理用户信息
        User user = (User) request.getSession().getAttribute("user");
        if(user == null){
            throw new BusinessException(ResultEnum.LOGIN_ERROR.getCode(),ResultEnum.LOGIN_ERROR.getMsg());
        }
        article.setAuthor(user);
        //处理分类信息
        Category category = categoryService.findOne(categoryId);
        if (category == null){
            throw new BusinessException(ResultEnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }
        article.setCategory(category);
        Article save = articleService.save(article);
        return ResultVOUtil.success(save);
    }

    /**
     * 根据id查询文章详情
     * @param id
     * @return
     */
    @GetMapping(value = "find/{id}")
    public ResultVo findById(@PathVariable(value = "id")String id){
        Article article = articleService.findOne(id);
        return  ResultVOUtil.success(article);
    }

    @GetMapping(value = "{name}")
    public ResultVo findByUser(@PathVariable(value = "name")String name,
                               @PathVariable(value = "num")String num){
//        articleService.
        return ResultVOUtil.success();
    }



}
