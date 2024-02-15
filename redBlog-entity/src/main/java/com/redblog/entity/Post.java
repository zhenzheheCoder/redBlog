package com.redblog.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_post")
public class Post {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String content;

    private Integer uid;

    @TableField(exist = false)
    private User userObj;
}
