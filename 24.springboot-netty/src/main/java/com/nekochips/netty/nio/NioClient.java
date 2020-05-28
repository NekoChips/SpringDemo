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
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

/**
 * @author NekoChips
 * @description NIO 客户端
 * @date 2020/5/20
 */
@Slf4j
@Component
@ConditionalOnProperty(name = "nio.enable", havingValue = "true")
public class NioClient {

    @Value("${server.address:localhost}")
    String host;

    @Value("${nio.port}")
    int port;

    private Selector selector;

    private SocketChannel clientChannel;

    private volatile boolean stop;

    /**
     * 启动客户端
     */
    public void start() {
        try {
            clientChannel = SocketChannel.open();
            clientChannel.configureBlocking(false);
            selector = Selector.open();
        } catch (IOException e) {
            log.error("nio client start fail.", e);
            System.exit(1);
        }
    }

    /**
     * 连接服务端
     */
    public void connect() {
        try {
            boolean connected = clientChannel.connect(new InetSocketAddress(host, port));
            if (connected) {
                clientChannel.register(this.selector, SelectionKey.OP_READ);
            } else {
                clientChannel.register(this.selector, SelectionKey.OP_CONNECT);
            }
        } catch (IOException e) {
            log.error("nio client connect to server fail. host: {}, port: {}", host, port, e);
        }
    }

    /**
     * 发送消息
     * @param sc SocketChannel
     */
    private void write(SocketChannel sc) {
        byte[] bytes = "a test message from client".getBytes();
        ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
        writeBuffer.put(bytes);
        writeBuffer.flip();
        try {
            sc.write(writeBuffer);
        } catch (IOException e) {
            log.error("nio client send message fail.", e);
        }
    }

    public void listen() {
        while (!stop) {
            try {
                selector.select(1000);
                Iterator<SelectionKey> selectionKeys = selector.selectedKeys().iterator();
                while (selectionKeys.hasNext()) {
                    SelectionKey key = selectionKeys.next();
                    selectionKeys.remove();
                    if (key.isValid()) {
                        SocketChannel sc = (SocketChannel) key.channel();
                        if (key.isConnectable()) {
                            if (sc.finishConnect()) {
                                sc.register(this.selector, SelectionKey.OP_READ);
                                write(sc);
                            } else {
                                System.exit(1);
                            } 
                        }
                        if (key.isReadable()) {
                            ByteBuffer readBuffer = ByteBuffer.allocate(2048);
                            int readBytes = sc.read(readBuffer);
                            if (readBytes > 0) {
                                readBuffer.flip();
                                byte[] bytes = new byte[readBuffer.remaining()];
                                readBuffer.get(bytes);
                                log.info("收到服务端消息： " + new String(bytes, StandardCharsets.UTF_8));
                                stop();
                            } else {
                                key.cancel();
                                sc.close();
                            } 
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 关闭客户端
     */
    private void stop() {
        this.stop = true;
    }

}
