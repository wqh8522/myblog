package com.wqh.blog.repository;

import com.wqh.blog.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author wqh
 * @Date 2017/10/19 17:11
 * @Description: 用户 Repository
 */
@Repository
public interface UserRepository extends JpaRepository<User,String> {

}
