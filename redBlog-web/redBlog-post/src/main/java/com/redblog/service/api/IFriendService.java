package com.redblog.service.api;

import com.redblog.entity.result.ResultEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("FRIEND-WEB")
public interface IFriendService {
    @RequestMapping(value = "/friend/getFriendListByUid")
    public ResultEntity getFriendListByUid(@RequestParam("uid") Integer uid);
}