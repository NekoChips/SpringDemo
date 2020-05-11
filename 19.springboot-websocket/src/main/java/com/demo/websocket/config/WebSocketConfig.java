package com.demo.websocket.config;

import com.demo.websocket.bean.WebSocketRoom;
import com.demo.websocket.handler.MyTextWebSocketHandler;
import com.demo.websocket.service.WebSocketRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author NekoChips
 * @description Websocket 配置类
 * @date 2020/3/29
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Autowired
    private MyTextWebSocketHandler myTextWebSocketHandler;

    @Autowired
    private WebSocketRoomService webSocketRoomService;
    
    /**
     * 注册 websocket 处理器
     *
     * @param registry WebSocketHandlerRegistry
     */
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        Set<WebSocketRoom> rooms = webSocketRoomService.findAll();
        List<String> roomConnects = rooms.stream().map(WebSocketRoom::getConnectPath).collect(Collectors.toList());
        registry.addHandler(myTextWebSocketHandler, roomConnects.toArray(new String[] {}))
                .withSockJS();
    }

//    /**
//     * WebSocket服务器端点
//     *
//     * @return ServerEndpointExporter
//     */
//    @Bean
//    public ServerEndpointExporter serverEndpointExporter() {
//        return new ServerEndpointExporter();
//    }
    
}
