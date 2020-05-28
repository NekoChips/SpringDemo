package com.nekochips.netty.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author NekoChips
 * @description Netty 时间客户端
 * @date 2020/5/21
 */
@Slf4j
@Component
public class TimeClient {

    private EventLoopGroup group;

    /**
     * 连接服务端
     * @param host 服务端地址
     * @param port 服务端端口
     */
    public void connect(String host, int port) {
        group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .remoteAddress(host, port)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new TimeClientHandler());
                    }
                });
        try {
            ChannelFuture future = bootstrap.connect().sync();
            if (future.isSuccess()) {
                log.info("time client connect in client server success.");
                // 等待 服务端 链路关闭
                future.channel().closeFuture().sync();
            }
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
        } finally {
            stop();
        }
    }
    
    public void stop() {
        if (group != null) {
            try {
                group.shutdownGracefully().sync();
            } catch (InterruptedException e) {
                log.error(e.getMessage(), e);
            }
        }
    }
}
