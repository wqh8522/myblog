package com.wqh.blog.controller;

import com.github.tobato.fastdfs.conn.FdfsWebServer;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.wqh.blog.domain.Article;
import com.wqh.blog.domain.Category;
import com.wqh.blog.domain.Page;
import com.wqh.blog.domain.User;
import com.wqh.blog.enums.ResultEnum;
import com.wqh.blog.exception.BusinessException;
import com.wqh.blog.service.ArticleService;
import com.wqh.blog.service.CategoryService;
import com.wqh.blog.service.UserService;
import com.wqh.blog.util.*;
import com.wqh.blog.vo.ArticleVO;
import com.wqh.blog.vo.MarkDVo;
import com.wqh.blog.vo.ResultVo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.io.*;
import java.nio.file.Paths;
import java.util.Date;

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

    private final ResourceLoader resourceLoader;

    @Autowired
    private QiniuUtil qiniuUtil;

    @Autowired
    private FastFileStorageClient storageClient;

    @Autowired
    private FdfsWebServer fdfsWebServer;

    public ArticleController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }


    /**
     * @param pageNo 页码
     * @return
     */
    @ApiOperation(value = "首页文章列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页码", required = true, dataType = "String", paramType = "path")
    })
    @GetMapping(value = "/articles/{pageNo}")
    public ResultVo articleList(@PathVariable("pageNo") Integer pageNo) {
        Page<Article> page = articleService.findPage(new Page<Article>(pageNo,10), new Article());
        return ResultVOUtil.success(page);
    }

    /**
     * 发表文章，需要登陆
     *
     * @param article_Vo
     * @return 返回发表文章的id和标题
     */
    @ApiOperation(value = "发表文章")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "article", value = "文章实体，title，content，tag不能为空", required = true, dataType = "Article"),
            @ApiImplicitParam(name = "categoryId", value = "分类id", required = true, dataType = "String")
    })
    @PostMapping(value = "/article/insert",produces = "application/json;charset=UTF-8")
    public ResultVo insert( @Valid @RequestBody ArticleVO article_Vo,
                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BusinessException(ResultEnum.PARAM_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        Article article = new Article();
        //处理作者信息
        User currentUser = userService.getCurrentUser();
        if (currentUser == null || StringUtils.isBlank(currentUser.getId())) {
            return ResultVOUtil.error(ResultEnum.PARAM_ERROR.getCode(), "请先登录");
        }
        article.setAuthor(currentUser);
        //处理分类信息
        Category category = categoryService.get(article_Vo.getCatrgoryId());
        if (category == null) {
            return ResultVOUtil.error(ResultEnum.PARAM_ERROR.getCode(), "请选择正确的分类");
        }
        article.setCategory(category);
        article.setTag(article_Vo.getTag());
        article.setTitle(article_Vo.getTitle());
        article.setContent(article_Vo.getContent());
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
    @GetMapping("/{username}/list")
    public ResultVo getArticleByAuthor(@PathVariable("username") String username) {

        return ResultVOUtil.success();
    }


    /**
     * 用户删除文章，这里做逻辑删除,需要登录
     * @param article_id
     * @return
     */
    @ApiOperation(value = " 用户删除文章")
    @ApiImplicitParam(name = "id", value = "文章id", required = true, dataType = "String", paramType = "path")
    @DeleteMapping(value = "/article/delete/{id}")
    public ResultVo userDetele(@PathVariable("id") String article_id){
        Article article = articleService.get(article_id);
        //判断该文章的作者是否是该登录用户
        User currentUser = userService.getCurrentUser();
        if(currentUser == null || StringUtils.isBlank(currentUser.getId())){
            return ResultVOUtil.error(ResultEnum.LOGIN_ERROR);
        }
        if(!article.getAuthor().getId().equals(currentUser.getId())){
            return ResultVOUtil.error(ResultEnum.ROLE_ERROR);
        }
        article.setDelFlag(Constants.IS_DEL);
        articleService.update(article);
        return ResultVOUtil.success();

    }

    /**
     * 直接MultipartFile使用上传图片 需要登录
     *
     * @return
     */
    @ApiOperation(value = "发表文章时上传图片")
    @ApiImplicitParam(name = "editormd-image-file", value = "图片", required = true, dataType = "MultipartFile")
    @PutMapping("/article/img/upload")
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
        if (currentUser == null || StringUtils.isBlank(currentUser.getUsername())) {
            throw new BusinessException(ResultEnum.LOGIN_ERROR);
        }
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
    @PutMapping("/article/img/base")
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
        String file_name;
        try {
            file_name = ImageUtil.saveImg(img,filePath);

        } catch (Exception e) {
            throw new BusinessException(ResultEnum.SAVE_IMG_ERROE);
        }
        return ResultVOUtil.success(return_path+File.separator+file_name);
    }

    /**
     * 上传文件到七牛云存储
     * @param multipartFile
     * @return
     * @throws IOException
     */
    @PutMapping("/article/img/qiniu")
    public String uploadImgQiniu(@RequestParam(value = "editormd-image-file",required = false) MultipartFile multipartFile) throws IOException {
        FileInputStream inputStream = (FileInputStream) multipartFile.getInputStream();
        User currentUser = userService.getCurrentUser();
        String path = qiniuUtil.uploadImg(inputStream, currentUser.getUsername()+"_"+Constants.getUUID());
        return path;
    }

    /**
     * 上传图片到FastDFS
     * @param multipartFile
     * @return
     * @throws IOException
     */
    @PutMapping("/article/img/fdfs")
    public String uploadImgfdfs(@RequestParam(value = "editormd-image-file") MultipartFile multipartFile) throws IOException {
        StorePath storePath= storageClient.uploadFile(multipartFile.getInputStream(), multipartFile.getSize(), "png", null);
        String path = fdfsWebServer.getWebServerUrl() + storePath.getFullPath();

        logger.info("保存路径={}",path);
        return path;
    }
    /**
     * 根据图片地址访问图片，未完成
     * @param filePath
     * @return
     * @throws FileNotFoundException
     */
    @ApiIgnore
    @GetMapping(value = "/article/img/{path}")
    public ResponseEntity<?> getImg(@PathVariable(value = "path") String filePath) throws FileNotFoundException {
        if(StringUtils.isBlank(filePath)){
            throw new BusinessException(ResultEnum.PARAM_ERROR);
        }
        String path = Base64Utils.getFromBase64(filePath);
        String s = Paths.get(location, path).toString();
        logger.info("返回地址={}",s);
        Resource resource = resourceLoader.getResource( "images:"+s);
        return ResponseEntity.ok(resource);
    }


}
