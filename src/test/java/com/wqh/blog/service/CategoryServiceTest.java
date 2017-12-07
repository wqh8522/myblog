package com.wqh.blog.service;

import com.wqh.blog.domain.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author wanqh
 * @date 2017/12/04 18:54
 * @description: 分类测试类
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CategoryServiceTest {

    @Autowired
    private CategoryService categoryService;
    @Test
    public void save() throws Exception {
        Category category = new Category();
        category.setName("服务器");
        category.setDescription("qqqqq");
        category.setSort("3");
        categoryService.save(category);
    }

    @Test
    public void update() throws Exception {
        Category category = new Category();
        category.setId("87138a2edaee11e7b3d200ff7aba65fc");
//        category.setName("php");
        category.setSort("2");
        categoryService.update(category);
    }

    @Test
    public void get() throws Exception {
        Category category = new Category();
        category.setId("87138a2edaee11e7b3d200ff7aba65fc");
        categoryService.get("87138a2edaee11e7b3d200ff7aba65fc");
    }

    @Test
    public void findList() throws Exception {
        List<Category> list = categoryService.findList(null);
        for (Category category : list){
            System.out.println(category.getName());
        }
    }

}