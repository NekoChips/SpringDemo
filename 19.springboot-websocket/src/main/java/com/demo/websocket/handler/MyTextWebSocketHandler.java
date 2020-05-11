package com.demo.websocket.handler;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author NekoChips
 * @description 自定义文本处理类
 * @date 2020/3/31
 */
@Component
public class MyTextWebSocketHandler extends TextWebSocketHandler {

    private final Logger logger = LoggerFactory.getLogger(MyTextWebSocketHandler.class);

    private static final ConcurrentHashMap<String, AtomicInteger> ROOM_ONLINE_COUNTS = new ConcurrentHashMap<>();

    private static final ConcurrentHashMap<String, Set<WebSocketSession>> ROOM_SESSIONS = new ConcurrentHashMap<>();

    /**
     * 连接建立后调用的方法
     *
     * @param session 客户端 session
     * @throws Exception 异常
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String room = getRoom(session);
        
        Set<WebSocketSession> webSocketSessions = getSessions(room);
        webSocketSessions.add(session);
        
        AtomicInteger onlineCount = getOnlineCount(room);

        logger.info("client {} join in room {}, now room online number is : {}", session.getId(), room, 
                onlineCount.incrementAndGet());
        session.sendMessage(new TextMessage("当前客户端会话 ID 为: " + session.getId()));
        groupSendMessage(room, session.getId(), session.getId() + " 进入聊天室 " + room);
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
        String room = getRoom(session);
        Set<WebSocketSession> webSocketSessions = getSessions(room);
        webSocketSessions.remove(session);
        AtomicInteger onlineCount = getOnlineCount(room);
        logger.info("client {} was closed, now room {} online number is : {}", session.getId(), room, 
                onlineCount.decrementAndGet());

        groupSendMessage(room, session.getId(), session.getId() + " 已断开连接");
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
        String room = getRoom(session);
        String textMessage = message.getPayload();
        logger.info("message from client {}: {}", session.getId(), message);
        session.sendMessage(new TextMessage("我: " + textMessage));

        // 发送消息给其他客户端
        groupSendMessage(room, session.getId(), session.getId() + " 说: " + textMessage);
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
     * @param room 房间号
     * @param sessionId 被指定客户端 session
     * @param message   消息内容
     */
    public void sendMessage(String room, String sessionId, String message) {
        Set<WebSocketSession> sessions = getSessions(room);
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
     * @param room 房间号
     * @param sourceSessionId 发出消息的 session 的 id
     * @param message       消息内容
     */
    public void groupSendMessage(String room, String sourceSessionId, String message) {
        Set<WebSocketSession> sessions = getSessions(room);
        sessions.stream()
                .filter(WebSocketSession::isOpen)
                .filter(session -> !session.getId().equals(sourceSessionId))
                .forEach(session -> sendMessage(room, session.getId(), message));
    }

    private String getRoom(WebSocketSession session) {
        String[] strings = StringUtils.split(session.getUri().getPath(), "/");
        return strings[1];
    }

    private Set<WebSocketSession> getSessions(String room) {
        Set<WebSocketSession> webSocketSessions = ROOM_SESSIONS.get(room);
        if (webSocketSessions == null) {
            webSocketSessions = new CopyOnWriteArraySet<>();
            ROOM_SESSIONS.put(room, webSocketSessions);
        }
        return webSocketSessions;
    }

    private AtomicInteger getOnlineCount(String room) {
        AtomicInteger onlineCount = ROOM_ONLINE_COUNTS.get(room);
        if (onlineCount == null) {
            onlineCount = new AtomicInteger(0);
            ROOM_ONLINE_COUNTS.put(room, onlineCount);
        }
        return onlineCount;
    }
}
