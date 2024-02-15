package com.redBlog.service;

import com.qf.base.service.BaseService;
import com.redblog.entity.FriendApply;

import java.util.List;

public interface IFriendApplyService extends BaseService<FriendApply> {
    List<FriendApply> getMyFriendApplyList(Integer uid);
}
