package com.redBlog.service;
import com.qf.base.service.BaseService;
import com.redblog.entity.User;
import org.springframework.stereotype.Service;


public interface IUserService extends BaseService<User> {
    Integer register(User user);

    User getUserByUsername(String username);
}
