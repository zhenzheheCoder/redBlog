package com.redblog.netty.handler;

import com.google.gson.Gson;
import com.redblog.entity.msg.ConnMsg;
import com.redblog.entity.msg.ShutDownMsg;
import com.redblog.netty.channel.ChannelGroup;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

public class ConnHandler extends SimpleChannelInboundHandler<ConnMsg> {

    private StringRedisTemplate redisTemplate;

    public ConnHandler(StringRedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ConnMsg connMsg) throws Exception {
        System.out.println("新的连接。。要保存到Map中"+connMsg);

        // 把新到的连接添加到map中
        ChannelGroup.addChannel(connMsg.getDid(),channelHandlerContext.channel());


        // 根据用户id从Reids中查询did
        Integer uid = connMsg.getUid();
        String did = connMsg.getDid();
        String redisDid = redisTemplate.opsForValue().get(uid.toString());
        if(redisDid != null && !redisDid.equals(did)){
            ShutDownMsg shutDownMsg = new ShutDownMsg();
            TextWebSocketFrame resp = new TextWebSocketFrame(new Gson().toJson(shutDownMsg));
            channelHandlerContext.writeAndFlush(resp);
        }
    }
}
