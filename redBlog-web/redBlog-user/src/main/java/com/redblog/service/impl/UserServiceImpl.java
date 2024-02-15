package com.redblog.service.impl;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.qf.base.service.impl.BaseServiceImpl;
import com.redBlog.service.IUserService;
import com.redblog.entity.User;
import com.redblog.mapper.IUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements IUserService {

    @Autowired
    private IUserMapper userMapper;

    @Override
    public BaseMapper<User> getMapper() {
        return userMapper;
    }

    @Override
    public Integer register(User user) {
        // 1.下判断用户是否存在
        if(isExits("username",user.getUsername())){
            return  1; // 用户名已存在
        }else if(isExits("phone",user.getPhone())){
            return 2; // 手机号已被注册
        }else{
            // 3.添加用户
            userMapper.insert(user);
            return 3;
        }
    }

    private boolean isExits(String column, String value) {
        EntityWrapper wrapper = new EntityWrapper();
        wrapper.eq(column,value);
        return userMapper.selectCount(wrapper) != 0;

    }

    @Override
    public User getUserByUsername(String username) {
        User user = new User();
        user.setUsername(username);
        return userMapper.selectOne(user);
    }


}
