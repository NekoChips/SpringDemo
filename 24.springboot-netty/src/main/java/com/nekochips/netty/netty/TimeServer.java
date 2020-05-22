package com.nekochips.netty.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author NekoChips
 * @description Netty 时间服务器
 * @date 2020/5/21
 */
@Slf4j
@Component
public class TimeServer {

    /**
     * boss 用于处理连接工作
     */
    private EventLoopGroup boss ;
    /**
     * worker 用于处理数据
      */ 
    private EventLoopGroup worker;

    /**
     * 服务端启动
     * @param port 端口号
     */
    public void start(int port) {
        boss = new NioEventLoopGroup();
        worker = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(boss, worker)
                // 指定 Channel
                .channel(NioServerSocketChannel.class)
                // 指定端口
                .localAddress(port)
                // 服务端可连接队列数，对应 TCP/IP 协议 listen 函数中的 backlog 参数
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new TimeServerHandler());                        
                    }
                });
        try {
            ChannelFuture future = bootstrap.bind().sync();
            if (future.isSuccess()) {
                log.info("time server start.");
                // 等待客户端链路关闭
                future.channel().closeFuture().sync();
            }
        } catch (InterruptedException e) {
           log.warn(e.getMessage(), e);
        }
    }

    /**
     * 关闭服务端
     */
    public void stop() {
        try {
            if (boss != null) {
                boss.shutdownGracefully().sync();
            }
            if (worker != null) {
                worker.shutdownGracefully().sync();
            }
            log.info("time server closed.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
