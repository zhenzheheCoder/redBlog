package com.redBlog.service;

import com.qf.base.service.BaseService;
import com.redblog.entity.Friend;
import org.springframework.stereotype.Service;

import java.util.List;


public interface IFriendService extends BaseService<Friend> {
    void addFriend(Integer tid, Integer fid, int i);

    List<Friend> getFriendListByUid(Integer uid);
}
