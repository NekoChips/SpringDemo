package com.demo.websocket.bean;

import java.io.Serializable;

/**
 * @author NekoChips
 * @description WebSocket 聊天室
 * @date 2020/5/11
 */
public class WebSocketRoom implements Serializable {

    private static final long serialVersionUID = 1564587746789703609L;

    private Integer id;

    private String room;

    private String connectPath;

    public WebSocketRoom(Integer id, String room, String connectPath) {
        this.id = id;
        this.room = room;
        this.connectPath = connectPath;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getConnectPath() {
        return connectPath;
    }

    public void setConnectPath(String connectPath) {
        this.connectPath = connectPath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        WebSocketRoom that = (WebSocketRoom) o;

        if (!id.equals(that.id)) {
            return false;
        }
        return room.equals(that.room);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + room.hashCode();
        return result;
    }
}
