package com.redblog.netty.server;


import com.redblog.netty.handler.ChatHandler;
import com.redblog.netty.handler.ConnHandler;
import com.redblog.netty.handler.HeardHandler;
import com.redblog.netty.handler.WebSocketInChannelHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class StartWebSocketServer implements CommandLineRunner {

    @Value("${netty.port}")
    private Integer port;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;


    /**
     * SpringBoot初始化成功后调用
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {
        EventLoopGroup master = new NioEventLoopGroup();
        EventLoopGroup salve = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(master,salve);
            bootstrap.channel(NioServerSocketChannel.class);
            bootstrap.childHandler(new ChannelInitializer() {
                @Override
                protected void initChannel(Channel channel) throws Exception {
                    ChannelPipeline pipeline = channel.pipeline();


                    pipeline.addLast(new HttpServerCodec()); // HttpRequset
                    pipeline.addLast(new HttpObjectAggregator(1024*10)); // FullHttpReqeust

                    // 添加WebSocket解编码
                    pipeline.addLast(new WebSocketServerProtocolHandler("/"));

                    // 客户端10s不发送信息自动断开
                    // pipeline.addLast(new ReadTimeoutHandler(10, TimeUnit.SECONDS));

                    // 添加处理客户端的请求的处理器
                    pipeline.addLast(new WebSocketInChannelHandler());

                    // 添加处理新连接的handler
                    pipeline.addLast(new ConnHandler(redisTemplate));

                    pipeline.addLast(new HeardHandler());

                    pipeline.addLast(new ChatHandler(rabbitTemplate));

                }
            });
            ChannelFuture channelFuture = bootstrap.bind(port);
            channelFuture.sync();
            System.out.println("服务端启动成功");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
//            master.shutdownGracefully();
//            salve.shutdownGracefully();
        }
    }
}
