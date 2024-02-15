package com.redblog.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.redblog.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_friend_apply")
public class FriendApply{
        @TableId(type = IdType.AUTO)
        private Integer id;

        private Integer fid;

        private Integer tid;

        private String msg;

        @TableField(value = "create_time")
        private Date createTime;

        private Integer status;

        @TableField(exist = false)
        private User friend;
}
