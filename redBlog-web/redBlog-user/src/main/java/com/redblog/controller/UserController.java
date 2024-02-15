package com.redblog.controller;

import com.redBlog.service.IUserService;
import com.redblog.entity.User;
import com.redblog.entity.msg.ShutDownMsg;
import com.redblog.entity.result.ResultEntity;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    IUserService userService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RequestMapping(value="/register")
    public ResultEntity register(User user){
        Integer num = userService.register(user);
        if (num == 1) {
            return ResultEntity.error("Username already taken");
        } else if (num == 2) {
            return ResultEntity.error("Phone number already in use");
        }

        return ResultEntity.success();
    }

    /**
     *
     * @param username
     * @param password
     * @param did device_id
     * @return
     */

    @RequestMapping(value = "/login")
    public ResultEntity login(String username, String password, String did) {
        User user = userService.getUserByUsername(username);
        if(user != null && user.getPassword().equals(password)){
            user.setPassword(null); // 密码不能写到手机里面

            // 1.先根据用户id查询设备id
            String redisDid = redisTemplate.opsForValue().get(user.getId().toString());
            if(redisDid != null && !did.equals(redisDid)){
                System.out.println("delete previous login");
                // 挤下其他的设备
                ShutDownMsg shutDownMsg = new ShutDownMsg();
                shutDownMsg.setDid(redisDid); // redis中的设备id
                rabbitTemplate.convertAndSend("ws_exchange","",shutDownMsg);
            }
            // 登录成功后要保护用户id和设备id
            redisTemplate.opsForValue().set(user.getId().toString(),did);

            return ResultEntity.success(user);
        }else{
            return ResultEntity.error("用户名或密码错误");
        }
    }
    @RequestMapping(value = "/findUserByUsername")
    public User findUserByUsername(String username){
        System.out.println("username = [" + username + "]");
        if(!StringUtils.isEmpty(username)){
            return userService.getUserByUsername(username);
        }
        return null;
    }


    @RequestMapping(value = "/getUserById")
    public User getUserById(Integer id){
        System.out.println("id = [" + id + "]");
        if(id != null){
            return userService.selectById(id);
        }
        return null;
    }

    @RequestMapping(value = "/findUserById")
    public ResultEntity findUserById(Integer id){
        User user = userService.selectById(id);
        return ResultEntity.success(user);
    }
}
