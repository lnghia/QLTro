package com.example.Models.Room;

import java.util.ArrayList;

public class RoomApiResponse {
    private boolean success;
    private ArrayList<Room> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ArrayList<Room> getData() {
        return data;
    }

    public void setData(ArrayList<Room> data) {
        this.data = data;
    }
}
