package com.demo.websocket.controller;

import com.demo.websocket.service.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author NekoChips
 * @description Websocket Controller
 * @date 2020/3/29
 */
@RestController
@RequestMapping("websocket")
public class WebSocketController {

    @Autowired
    private WebSocketServer webSocketServer;

    @PostMapping("sendOne")
    public HttpEntity<?> sendOneMessage(String clientId, String message) {
        webSocketServer.sendMessage(clientId, message);
        return ResponseEntity.ok("send message complete");
    }

    @PostMapping("groupSend")
    public HttpEntity<?> groupSendMessage(String clientId, String message) {
        webSocketServer.groupSendMessage(clientId, message);
        return ResponseEntity.ok("group send message complete");
    }
}
