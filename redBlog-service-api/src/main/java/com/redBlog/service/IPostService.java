package com.redBlog.service;
import com.qf.base.service.BaseService;
import com.redblog.entity.Post;
import com.redblog.entity.User;
import com.redblog.entity.result.ResultEntity;

import java.util.List;

public interface IPostService extends BaseService<Post>{
    Integer post(Post post);
    List<Post> cpost(Integer uid);
}
