package com.nekochips.netty.bio;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * @author NekoChips
 * @description BIO 客户端
 * @date 2020/5/19
 */
@Slf4j
@Component
@ConditionalOnProperty(prefix = "bio", name = "enable", havingValue = "true")
public class BioClient {

    @Value("${server.address}")
    String host;

    @Value("${bio.port}")
    int port;

    private Socket socket;

    /**
     * 连接服务端，并发送消息
     */
    public void start() {
        try {
            socket = new Socket(host, port);
            log.info("BioClient connect success.");

        } catch (IOException e) {
            log.error("BioClient process data failed, host:{}, port:{}", host, port, e);
        }
    }

    /**
     * 客户端发送消息
     *
     * @param message 消息内容
     */
    public void send(String message) {
        try {
            // 获取服务端发送过来的数据
            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            // 向服务端发送数据
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
            outputStream.writeUTF(message);

            String callBack = inputStream.readUTF();
            System.out.println("服务器返回信息： " + callBack);
        } catch (IOException e) {
            log.error("socket client send message fail! message: {}", message, e);
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                    log.info("socket client closed.");
                } catch (IOException e) {
                    // help gc
                    socket = null;
                    log.error("socket client close fail!", e);
                }
            }
        }
    }
}
