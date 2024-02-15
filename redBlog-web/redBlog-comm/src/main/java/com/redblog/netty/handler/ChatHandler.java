package com.redblog.netty.handler;

import com.google.gson.Gson;
import com.redblog.entity.msg.ChatMsg;
import com.redblog.entity.msg.ConnMsg;
import com.redblog.entity.msg.ShutDownMsg;
import com.redblog.netty.channel.ChannelGroup;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

public class ChatHandler extends SimpleChannelInboundHandler<ChatMsg> {

    private RabbitTemplate rabbitTemplate;

    public ChatHandler(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ChatMsg chatMsg) throws Exception {
        System.out.println("用户聊天的信息"+chatMsg);

        // 消息入库

        // 转发给交换机
        rabbitTemplate.convertAndSend("ws_exchange","",chatMsg);

    }
}
