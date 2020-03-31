package com.demo.websocket.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author NekoChips
 * @description Websocket 服务端配置,用于前端创建 websocket 连接
 * @date 2020/3/29
 */
@ServerEndpoint("/ws")
@Component
public class WebSocketServer {

    private Logger logger = LoggerFactory.getLogger(WebSocketServer.class);

    private final AtomicInteger onlineCount = new AtomicInteger(0);

    private static CopyOnWriteArraySet<Session> sessions = new CopyOnWriteArraySet<>();

    /**
     * 对应前端 websocket.onOpen 事件，服务端处理逻辑
     *
     * @param session 客户端 session
     */
    @OnOpen
    public void onOpen(Session session) {
        sessions.add(session);
        logger.info("join a new client, now online number is : {}", onlineCount.incrementAndGet());
        sendMessage(session, "当前客户端会话 ID 为 :" + session.getId());
        groupSendMessage(session.getId(),session.getId() + " 进入聊天室");
    }

    /**
     * 对应前端 websocket.onMessage 事件，服务端处理逻辑
     *
     * @param session 客户端session
     * @param message 消息内容
     */
    @OnMessage
    public void onMessage(Session session, String message) {
        logger.info("message from client {}: {}", session.getId(), message);
        sendMessage(session,"我: " + message);
        groupSendMessage(session.getId(), session.getId() + " 说: " + message);
    }

    /**
     * 对应前端 websocket.onClose 事件，服务器端处理逻辑
     *
     * @param session 客户端 session
     */
    @OnClose
    public void onClose(Session session) {
        sessions.remove(session);
        logger.info("client {} disconnect from server, now online number is : {}", session.getId(), onlineCount.decrementAndGet());
        groupSendMessage(session.getId(), session.getId() + " 已断开连接");
    }

    /**
     * 对应前端 websocket.onError 事件，服务器端处理逻辑
     *
     * @param session   客户端session
     * @param throwable 抛出的错误
     * @throws Exception 异常
     */
    @OnError
    public void onError(Session session, Throwable throwable) throws Exception {
        session.close(new CloseReason(CloseReason.CloseCodes.PROTOCOL_ERROR, "client error"));
        logger.error("server error, Session ID: {}, error info: {}", session.getId(), throwable.getMessage());
    }

    /**
     * 服务器发送消息
     *
     * @param session 当前客户端 session
     * @param message 消息内容
     */
    private void sendMessage(Session session, String message) {
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            logger.error("{} send message fail, error: {}", session.getId(), e.getMessage());
        }
    }

    /**
     * 向指定客户端发送消息
     *
     * @param sessionId 被指定客户端 session
     * @param message   消息内容
     */
    public void sendMessage(String sessionId, String message) {
        Session targetSession = sessions.stream()
                .filter(session -> sessionId.equals(session.getId()))
                .findAny()
                .orElse(null);

        if (targetSession == null) {
            logger.warn("{} is offline", sessionId);
            return;
        }
        sendMessage(targetSession, message);
    }

    /**
     * 群发消息
     *
     * @param sourceSessionId 发出消息的 session 的 id
     * @param message 消息内容
     */
    public void groupSendMessage(String sourceSessionId,  String message) {
        sessions.stream()
                .filter(Session::isOpen)
                .filter(session -> !session.getId().equalsIgnoreCase(sourceSessionId))
                .forEach(session -> sendMessage(session, message));

    }
}
