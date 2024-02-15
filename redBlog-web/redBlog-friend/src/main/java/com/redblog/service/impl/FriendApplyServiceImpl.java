package com.redblog.service.impl;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.qf.base.service.impl.BaseServiceImpl;
import com.redBlog.service.IFriendApplyService;
import com.redblog.entity.FriendApply;
import com.redblog.mapper.IFriendApplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendApplyServiceImpl extends BaseServiceImpl<FriendApply> implements IFriendApplyService {
    @Autowired
    private IFriendApplyMapper mapper;

    @Override
    public BaseMapper<FriendApply> getMapper() {
        return mapper;
    }

    @Override
    public List<FriendApply> getMyFriendApplyList(Integer uid) {
        EntityWrapper wrapper = new EntityWrapper();
        wrapper.eq("tid",uid);
        return mapper.selectList(wrapper);
    }
}
