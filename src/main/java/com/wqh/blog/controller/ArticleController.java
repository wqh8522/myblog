package com.wqh.blog.controller;

import com.wqh.blog.domain.Article;
import com.wqh.blog.domain.Category;
import com.wqh.blog.domain.Page;
import com.wqh.blog.domain.User;
import com.wqh.blog.enums.ResultEnum;
import com.wqh.blog.exception.BusinessException;
import com.wqh.blog.service.ArticleService;
import com.wqh.blog.service.CategoryService;
import com.wqh.blog.service.UserService;
import com.wqh.blog.util.ResultVOUtil;
import com.wqh.blog.vo.ArticleVO;
import com.wqh.blog.vo.ResultVo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author wanqh
 * @date 2017/11/26 17:03
 * @description: 文章控制器
 */

@RestController
@RequestMapping
public class ArticleController  {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    /**
     *
     * @param pageNo 页码
     * @return
     */
    @ApiOperation(value = "首页文章列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo",value = "页码",required = true,dataType = "String",paramType = "path")
    })
    @GetMapping("/articles/{pageNo}")
    public ResultVo articleList(@RequestParam("pageNo")String pageNo){
        Page<Article> page = articleService.findPage(new Page<Article>(Integer.parseInt(pageNo), 1), new Article());
        return ResultVOUtil.success(page);
    }

    /**
     * 发表文章，需要登陆
     * @param article
     * @return 返回发表文章的id和标题
     */
    @ApiOperation(value = "发表文章")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "article",value = "文章实体，title，content，tag不能为空",required = true,dataType = "Article"),
            @ApiImplicitParam(name = "catrgoryId",value = "分类id",required = true,dataType = "String")
    })
    @PostMapping("/article/insert")
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
        ArticleVO articleVO = new ArticleVO();
        articleVO.setId(article.getId());
        articleVO.setTitle(article.getTitle());
        return ResultVOUtil.success(articleVO);
    }

    /**
     * 根据id获取文章信息
     * @param id
     * @return
     */
    @ApiOperation(value = "根据id查看文章详情")
    @ApiImplicitParam(name = "id",value = "文章id",required = true,dataType = "String")
    @GetMapping("/article/{id}")
    public ResultVo getArticleById(@PathVariable("id") String id){
        Article article = articleService.get(id);
        if(article == null){
            return ResultVOUtil.error(ResultEnum.ARTICLE_IS_DELETE);
        }
        //更新文章查看数量
        article.addLookCount();
        articleService.update(article);
        return ResultVOUtil.success(article);
    }


    /**
     * 根据用户名查询用户的文章列表
     * @param username
     * @return
     */
    @ApiOperation(value = "根据用户名查询用户的文章列表，需要分页")
    @ApiImplicitParam(name = "username",value = "用户名",required = true,dataType = "String",paramType = "path")
    @GetMapping("/article/{username}")
    public ResultVo getArticleByAuthor(@PathVariable("username") String username){

        return ResultVOUtil.success();
    }
}
