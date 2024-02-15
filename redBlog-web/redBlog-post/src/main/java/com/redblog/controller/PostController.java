package com.redblog.controller;

import com.redBlog.service.IPostService;
import com.redblog.entity.Friend;
import com.redblog.entity.Post;
import com.redblog.entity.User;
import com.redblog.entity.result.ResultEntity;
import com.redblog.service.api.IFriendService;
import com.redblog.service.api.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/post")
public class PostController {

    @Autowired
    IPostService postService;

    @Autowired
    IUserService userService;

    @Autowired
    IFriendService friendService;

    @RequestMapping(value="/newpost")
    private ResultEntity makePost(Post post, String username){
        User user = userService.findUserByUsername(username);
        postService.post(post);
        System.out.println(user);
        return new ResultEntity().success();
    }

    @RequestMapping(value="postuser")
    private ResultEntity postUser(User user){
        Integer uid = user.getId();
        List<Post> list = postService.cpost(uid);
        return ResultEntity.success(list);
    }

    @RequestMapping(value="postfriend")
    private ResultEntity postFriend(User user){
        Integer uid = user.getId();
        List<Friend> list = (List<Friend>) friendService.getFriendListByUid(uid).getData();
        List<Post> res = new ArrayList<>();
        for(Friend f:list){
            Integer fid = f.getFid();
            User fu = userService.getUserById(fid);
            List<Post> l = (List<Post>) postUser(fu).getData();
            for(Post p: l)res.add(p);
        }
        return ResultEntity.success(res);
    }



}
