package com.wqh.blog.repository;

import com.wqh.blog.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author wqh
 * @Date 2017/10/20 15:12
 * @Description:
 */
@Repository
public interface CategoryReponsitory extends JpaRepository<Category,String> {

}
