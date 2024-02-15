package com.redblog.listenner;

import com.google.gson.Gson;
import com.redblog.entity.msg.ChatMsg;
import com.redblog.entity.msg.ShutDownMsg;
import com.redblog.netty.channel.ChannelGroup;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@RabbitListener(queues = "ws_queue_${netty.port}")
public class WsQueueListener {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @RabbitHandler
    public void wsMsg(ShutDownMsg shutDownMsg){

        // 查看当前设备号是否在本机
        String did = shutDownMsg.getDid();
        Channel channel = ChannelGroup.getChannel(did);
        System.out.println("监听器获得数据。。"+shutDownMsg);
        if(channel != null){
            System.out.println("监听器发送消息给客户端完成。。。");

            TextWebSocketFrame resp = new TextWebSocketFrame(new Gson().toJson(shutDownMsg));
            channel.writeAndFlush(resp);
        }
    }

    @RabbitHandler
    public void wsChat(ChatMsg chatMsg){

        System.out.println("监听器中收到数据："+chatMsg);
        // 根据tid查询did
        String did = redisTemplate.opsForValue().get(chatMsg.getTid().toString());

        // 根据did查询channel
        Channel channel = ChannelGroup.getChannel(did);
        if(channel != null){
            TextWebSocketFrame resp = new TextWebSocketFrame(new Gson().toJson(chatMsg));
            channel.writeAndFlush(resp);
            System.out.println("监听器消息发送成功。。。。");
        }
    }
}