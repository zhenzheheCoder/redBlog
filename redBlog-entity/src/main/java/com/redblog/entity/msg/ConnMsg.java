package com.redblog.entity.msg;

import lombok.Data;

/**
 * 连接对象
 */
@Data
public class ConnMsg extends  NettyMsg{

    private Integer uid;
}
