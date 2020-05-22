package com.nekochips.netty.nio;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

/**
 * @author NekoChips
 * @description NIO 服务端
 * @date 2020/5/20
 */
@Slf4j
@Component
@ConditionalOnProperty(name = "nio.enable", havingValue = "true")
public class NioServer {

    private Selector selector;

    private ServerSocketChannel serverChannel;

    private volatile boolean stop;

    @Value("${nio.port}")
    int port;

    /**
     * 初始化
     */
    public void init() {
        try {
            // 获取 ServerSocket 通道
            serverChannel = ServerSocketChannel.open();
            // 将通道设置为 非阻塞 状态，否则会抛出异常
            serverChannel.configureBlocking(false);
            // 绑定端口
            serverChannel.socket().bind(new InetSocketAddress(port), 1024);
            // 创建 selector
            this.selector = Selector.open();
            // 绑定 selector 以及注册监听事件
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);
            log.info("NioServer start success with port: {}", port);
        } catch (IOException e) {
            log.error("NioServer start failed.", e);
        }
    }
    
    public void stop() {
        this.stop = true;
    }
    
    /**
     * 监听事件
     */
    public void listen() {
        while (!stop) {
            try {
                // 当监听到的事件被触发时，方法被调用，否则一致阻塞
                selector.select(1000);
                 Iterator<SelectionKey> iterator = this.selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    iterator.remove();
                    try {
                        handleKey(key);
                    } catch (IOException e) {
                        key.cancel();
                        if (key.channel() != null) {
                            key.channel().close();
                        }
                        log.error("nio server process key error.", e);
                    }
                }
            } catch (IOException e) {
                log.error("process listen event error", e);        
            }
        }
        if (selector != null) {
            try {
                selector.close();
            } catch (IOException e) {
                log.error("close selector fail.", e);
            }
        }
    }

    private void handleKey(SelectionKey key) throws IOException {
         if (key.isAcceptable()) {
            handleAccept(key);
        } else if (key.isReadable()) {
            handleRead(key);
        }
    }

    private void handleRead(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        ByteBuffer readBuffer = ByteBuffer.allocate(1024);
        int read = channel.read(readBuffer);
        if (read > 0) {
            byte[] bytes = readBuffer.array();
            String message = new String(bytes, StandardCharsets.UTF_8);
            log.info("receive message from client: [{}]", message);
            String response = "消息 [ " + message + " ] 发送成功";
            ByteBuffer writeBuffer = ByteBuffer.allocate(response.getBytes().length);
            writeBuffer.put(response.getBytes());
            writeBuffer.flip();
            channel.write(writeBuffer);
        }
        else{
            log.info("no message to read.");
        }
        key.cancel();
        channel.close();
    }
    

    private void handleAccept(SelectionKey key) throws IOException {
        ServerSocketChannel socketChannel = (ServerSocketChannel) key.channel();
        SocketChannel accept = socketChannel.accept();
        accept.configureBlocking(false);
        log.info("a new nio client connect in.");
        accept.register(this.selector, SelectionKey.OP_READ);
    }
}
