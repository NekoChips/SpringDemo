package com.demo.websocket.service;

import com.demo.websocket.bean.WebSocketRoom;

import java.util.Set;

/**
 * @author NekoChips
 * @description WebSocketRoom 逻辑处理接口
 * @date 2020/5/11
 */
public interface WebSocketRoomService {

    /**
     * 查询所有房间
     * @return 所欲房间
     */
    Set<WebSocketRoom> findAll();
}
