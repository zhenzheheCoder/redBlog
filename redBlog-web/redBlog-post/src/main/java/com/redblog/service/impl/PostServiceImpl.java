package com.redblog.service.impl;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.qf.base.service.impl.BaseServiceImpl;
import com.redBlog.service.IPostService;
import com.redblog.entity.Post;
import com.redblog.entity.User;
import com.redblog.entity.result.ResultEntity;
import com.redblog.mapper.IPostMapper;
import com.redblog.service.api.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl extends BaseServiceImpl<Post> implements IPostService{


    @Autowired
    private IPostMapper postMapper;


    @Override
    public BaseMapper<Post> getMapper() {
        return postMapper;
    }


    @Override
    public Integer post(Post post) {
        System.out.println(post.getId());
        postMapper.insert(post);
        return 3;
    }

    @Override
    public List<Post> cpost(Integer uid) {
        EntityWrapper wrapper = new EntityWrapper();
        wrapper.eq("uid",uid);
        return postMapper.selectList(wrapper);
    }


}
