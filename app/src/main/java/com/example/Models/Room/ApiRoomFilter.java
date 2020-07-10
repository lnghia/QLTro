package com.example.Models.Room;

public class ApiRoomFilter {
    private String roomId;

    public ApiRoomFilter(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
}
