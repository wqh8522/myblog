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
import com.wqh.blog.util.Constants;
import com.wqh.blog.util.ImageUtil;
import com.wqh.blog.util.ResultVOUtil;
import com.wqh.blog.util.UserUtil;
import com.wqh.blog.vo.ArticleVO;
import com.wqh.blog.vo.MarkDVo;
import com.wqh.blog.vo.ResultVo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author wanqh
 * @date 2017/11/26 17:03
 * @description: 文章控制器
 */

@RestController
@RequestMapping
public class ArticleController {

    private final Logger logger = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    private ArticleService articleService;

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @Value("${img.location}")
    private String location;

    /**
     * @param pageNo 页码
     * @return
     */
    @ApiOperation(value = "首页文章列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页码", required = true, dataType = "String", paramType = "path")
    })
    @GetMapping("/articles/{pageNo}")
    public ResultVo articleList(@RequestParam("pageNo") String pageNo) {
        Page<Article> page = articleService.findPage(new Page<Article>(Integer.parseInt(pageNo), 1), new Article());
        return ResultVOUtil.success(page);
    }

    /**
     * 发表文章，需要登陆
     *
     * @param article
     * @return 返回发表文章的id和标题
     */
    @ApiOperation(value = "发表文章")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "article", value = "文章实体，title，content，tag不能为空", required = true, dataType = "Article"),
            @ApiImplicitParam(name = "catrgoryId", value = "分类id", required = true, dataType = "String")
    })
    @PostMapping("/article/insert")
    public ResultVo insert(@Valid Article article,
                           @RequestParam(value = "catrgoryId") String catrgoryId,
                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BusinessException(ResultEnum.PARAM_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        //处理作者信息
        User currentUser = userService.getCurrentUser();
        if (currentUser == null || StringUtils.isBlank(currentUser.getId())) {
            return ResultVOUtil.error(ResultEnum.PARAM_ERROR.getCode(), "请先登录");
        }
        article.setAuthor(currentUser);
        //处理分类信息
        if (StringUtils.isBlank(catrgoryId)) {
            return ResultVOUtil.error(ResultEnum.PARAM_ERROR.getCode(), "请选择分类");
        }
        Category category = categoryService.get(catrgoryId);
        if (category == null) {
            return ResultVOUtil.error(ResultEnum.PARAM_ERROR.getCode(), "请选择正确的分类");
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
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "根据id查看文章详情")
    @ApiImplicitParam(name = "id", value = "文章id", required = true, dataType = "String")
    @GetMapping("/article/{id}")
    public ResultVo getArticleById(@PathVariable("id") String id) {

        logger.info(id);
        Article article = articleService.get(id);
        if (article == null) {
            return ResultVOUtil.error(ResultEnum.ARTICLE_IS_DELETE);
        }
        //更新文章查看数量
        article.addLookCount();
        articleService.update(article);
        return ResultVOUtil.success(article);
    }


    /**
     * 根据用户名查询用户的文章列表
     *
     * @param username
     * @return
     */
    @ApiOperation(value = "根据用户名查询用户的文章列表，需要分页")
    @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String", paramType = "path")
    @GetMapping("/articles/{username}")
    public ResultVo getArticleByAuthor(@PathVariable("username") String username) {

        return ResultVOUtil.success();
    }

    /**
     * 直接MultipartFile使用上传图片 需要登录
     *
     * @return
     */
    @PostMapping("/article/img")
    public MarkDVo uploadImg(@RequestParam("editormd-image-file") MultipartFile multipartFile,
                             @RequestParam(value = "callback",required = false)String callback )  {
        if (multipartFile.isEmpty() || StringUtils.isBlank(multipartFile.getOriginalFilename())) {
           throw new BusinessException(ResultEnum.IMG_NOT_EMPTY);
        }
        String contentType = multipartFile.getContentType();
        if (!contentType.contains("")) {
            throw new BusinessException(ResultEnum.IMG_FORMAT_ERROR);
        }
        String root_fileName = multipartFile.getOriginalFilename();
        logger.info("上传图片:name={},type={}", root_fileName, contentType);
        //处理图片
        User currentUser = userService.getCurrentUser();
//        if (currentUser == null || StringUtils.isBlank(currentUser.getUsername())) {
//            throw new BusinessException(ResultEnum.LOGIN_ERROR);
//        }
        //获取路径
        String return_path = ImageUtil.getFilePath(currentUser);
        String filePath = location + return_path;
        logger.info("图片保存路径={}", filePath);
        String file_name = null;
        try {
            file_name = ImageUtil.saveImg(multipartFile, filePath);
            MarkDVo markDVo = new MarkDVo();
            markDVo.setSuccess(0);
            if(StringUtils.isNotBlank(file_name)){
                markDVo.setSuccess(1);
                markDVo.setMessage("上传成功");
                markDVo.setUrl(return_path+File.separator+file_name);
                markDVo.setCallback(callback);
            }
            logger.info("返回值：{}",markDVo);
            return markDVo;
        } catch (IOException e) {
            throw new BusinessException(ResultEnum.SAVE_IMG_ERROE);
        }
    }

    /**
     * 以Base64编码形式上传文章中的图片
     *
     * @return
     */
    @PostMapping("/article/imgBase64")
    public ResultVo imgBase64(@RequestParam(value = "imgBase64") String img) {
        if(StringUtils.isBlank(img)){
            return ResultVOUtil.error(ResultEnum.IMG_NOT_EMPTY);
        }
        //处理图片
        User currentUser = userService.getCurrentUser();
        if (currentUser == null || StringUtils.isBlank(currentUser.getUsername())) {
            return ResultVOUtil.error(ResultEnum.LOGIN_ERROR);
        }
        //获取路径
        String return_path = ImageUtil.getFilePath(currentUser);
        String filePath = location + return_path;
        logger.info("图片保存路径={}", filePath);
        String file_name = null;
        try {
            file_name = ImageUtil.saveImg(img,filePath);

        } catch (Exception e) {
            throw new BusinessException(ResultEnum.SAVE_IMG_ERROE);
        }
        return ResultVOUtil.success(return_path+File.separator+file_name);
    }


    public void getImg(String filePath){
    }
}
