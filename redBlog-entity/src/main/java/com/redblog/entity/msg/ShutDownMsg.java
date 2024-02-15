package com.redblog.entity.msg;

import lombok.Data;

@Data
public class ShutDownMsg extends NettyMsg {

    {
        setType(6);
    }
}
