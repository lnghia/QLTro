package com.example.Models.Room;

public class RoomCreateApiResponse {
    private boolean success;
    private Room data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Room getData() {
        return data;
    }

    public void setData(Room data) {
        this.data = data;
    }
}
