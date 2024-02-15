package com.redblog.entity.msg;

import lombok.Data;

@Data
public class ChatMsg extends  NettyMsg {

    private Integer fid;
    private Integer tid;
    private String content; // 内容
}
