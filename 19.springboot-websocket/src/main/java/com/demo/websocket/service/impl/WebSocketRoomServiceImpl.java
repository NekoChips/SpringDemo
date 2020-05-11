package com.demo.websocket.service.impl;

import com.demo.websocket.bean.WebSocketRoom;
import com.demo.websocket.service.WebSocketRoomService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * @author NekoChips
 * @description WebSocketRoom 逻辑处理接口实现类
 * @date 2020/5/11
 */
@Service
public class WebSocketRoomServiceImpl implements WebSocketRoomService {
    
    @Override
    public Set<WebSocketRoom> findAll() {
        Set<WebSocketRoom> rooms = new HashSet<>();
        rooms.add(new WebSocketRoom(1, "001", "/connect/001"));
        rooms.add(new WebSocketRoom(2, "002", "/connect/002"));
        rooms.add(new WebSocketRoom(3, "003", "/connect/003"));
        rooms.add(new WebSocketRoom(4, "004", "/connect/004"));
        rooms.add(new WebSocketRoom(5, "005", "/connect/005"));
        rooms.add(new WebSocketRoom(6, "006", "/connect/006"));
        return rooms;
    }
}
