package com.wqh.blog.service.impl;

import com.wqh.blog.domain.Vote;
import com.wqh.blog.repository.VoteRepository;
import com.wqh.blog.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 * @Author wqh
 * @Date 2017/10/20 14:16
 * @Description:
 */
@Service
public class VoteServiceImpl extends BaseServiceImpl<Vote> implements VoteService{
    @Autowired
    private VoteRepository voteRepository;


    @Override
    public JpaRepository<Vote, String> getJpaRepository() {
        return this.voteRepository;
    }
}
