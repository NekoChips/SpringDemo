package com.demo.websocket.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author NekoChips
 * @description 自定义文本处理类
 * @date 2020/3/31
 */
@Component
public class MyTextWebSocketHandler extends TextWebSocketHandler {

    private Logger logger = LoggerFactory.getLogger(MyTextWebSocketHandler.class);

    private final AtomicInteger onlineCount = new AtomicInteger(0);

    private static CopyOnWriteArraySet<WebSocketSession> sessions = new CopyOnWriteArraySet<>();

    /**
     * 连接建立后调用的方法
     *
     * @param session 客户端 session
     * @throws Exception 异常
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
        logger.info("client {} join in, now online number is : {}", session.getId(), onlineCount.incrementAndGet());
        session.sendMessage(new TextMessage("当前客户端会话 ID 为: " + session.getId()));
        groupSendMessage(session.getId(), session.getId() + " 进入聊天室");
    }

    /**
     * 连接断开后调用的方法
     *
     * @param session 客户端 session
     * @param status  关闭状态， 1000 为正常关闭
     * @throws Exception 异常
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
        logger.info("client {} was closed, now online number is : {}", session.getId(), onlineCount.decrementAndGet());

        groupSendMessage(session.getId(), session.getId() + " 已断开连接");
    }

    /**
     * 获取到客户端发送的文本消息时调用的方法
     *
     * @param session 客户端 session
     * @param message 消息内容
     * @throws Exception 异常
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String textMessage = message.getPayload();
        logger.info("message from client {}: {}", session.getId(), message);
        session.sendMessage(new TextMessage("我: " + textMessage));

        // 发送消息给其他客户端
        groupSendMessage(session.getId(), session.getId() + " 说: " + textMessage);
    }

    /**
     * 客户端连接异常时调用的方法
     *
     * @param session   客户端 session
     * @param exception 异常信息
     * @throws Exception 异常
     */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        session.close(CloseStatus.SERVER_ERROR);
        logger.error("server error, Session ID: {}, error info: {}", session.getId(), exception.getMessage());
    }

    /**
     * 向指定客户端发送消息
     *
     * @param sessionId 被指定客户端 session
     * @param message   消息内容
     */
    public void sendMessage(String sessionId, String message) {
        WebSocketSession targetSession = sessions.stream()
                .filter(session -> sessionId.equals(session.getId()))
                .findAny()
                .orElse(null);

        if (targetSession == null) {
            logger.warn("{} is offline", sessionId);
            return;
        }
        try {
            targetSession.sendMessage(new TextMessage(message));
        } catch (IOException e) {
            logger.error("client {} send message fail, error: {}", sessionId, e.getMessage());
        }
    }

    /**
     * 群发消息
     *
     * @param sourceSessionId 发出消息的 session 的 id
     * @param message       消息内容
     */
    public void groupSendMessage(String sourceSessionId, String message) {
        sessions.stream()
                .filter(WebSocketSession::isOpen)
                .filter(session -> !session.getId().equals(sourceSessionId))
                .forEach(session -> sendMessage(session.getId(), message));
    }

}
