package com.redblog.controller;

import com.redBlog.service.IFriendService;
import com.redblog.entity.Friend;
import com.redblog.entity.User;
import com.redblog.entity.result.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/friend")
public class FriendController {
    @Autowired
    private IFriendService friendService;

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/getFriendListByUid")
    public ResultEntity getFriendListByUid(Integer uid ) {
        List<Friend> friendList = friendService.getFriendListByUid(uid);
        for(Friend f:friendList){
            f.setFriendObj(userService.getUserById(f.getFid()));
        }
        return ResultEntity.success(friendList);
    }

    @RequestMapping("/test")
    public ResultEntity test(Integer uid){
        User user = userService.getUserById(uid);
        return ResultEntity.success(user);
    }
}
