package com.wqh.blog.repository;

import com.wqh.blog.domain.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author wqh
 * @Date 2017/10/20 14:16
 * @Description:
 */
@Repository
public interface VoteRepository extends JpaRepository<Vote,String>{
}
