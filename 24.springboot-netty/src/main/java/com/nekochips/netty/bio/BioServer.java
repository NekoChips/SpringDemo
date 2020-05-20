package com.nekochips.netty.bio;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author NekoChips
 * @description BIO 服务端
 * @date 2020/5/19
 */
@Slf4j
@Component
@ConditionalOnProperty(prefix = "bio", name = "enable", havingValue = "true")
public class BioServer {

    @Value("${bio.port}")
    int port;

    /**
     * 创建并启动 socket 服务
     */
    @SuppressWarnings("InfiniteLoopStatement")
    public void start() {
        try {
            ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2, 5, 0L, TimeUnit.MILLISECONDS,
                    new ArrayBlockingQueue<>(5), (ThreadFactory) Thread::new);
            ServerSocket serverSocket = new ServerSocket(port);
            log.info("socket server start with port: {}", port);
            while (true) {
                // 此处 线程阻塞
                final Socket socket = serverSocket.accept();
                threadPool.execute(() -> {
                    log.info("new socket client connect!");
                    handler(socket);
                });
            }
        } catch (IOException e) {
            log.error("BioServer start socket server failed, port:{}", port, e);
        }
    }

    /**
     * 处理客户端的数据
     *
     * @param socket socket 连接
     */
    private void handler(Socket socket) {
        try {
            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
            while (inputStream.available() > 0) {
                String message = inputStream.readUTF();
                if (!message.isEmpty()) {
                    log.info("receive message [{}] from client", message);
                    outputStream.writeUTF(String.format("消息 [%s] 发送成功", message));
                } else {
                    log.info("no more message to read.");
                    break;
                }
            }
        } catch (IOException e) {
            log.error("handler socket error! ", e);
        } 
    }
}
