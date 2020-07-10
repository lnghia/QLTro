package com.example.Models.Room;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Room {
    private String slotStatus;
    private String _id;
    private String name;
    private int floor;
    private int price;
    private float square;
    private int capacity;
    private int amountOfVehicles;

    public Room(String slotStatus, String _id, String name, int floor, int price, float square, int capacity, int amountOfVehicles) {
        this.slotStatus = slotStatus;
        this._id = _id;
        this.name = name;
        this.floor = floor;
        this.price = price;
        this.square = square;
        this.capacity = capacity;
        this.amountOfVehicles = amountOfVehicles;
    }

    public Room(String name, int floor, int price, float square, int capacity, int amountOfVehicles) {
        this.name = name;
        this.floor = floor;
        this.price = price;
        this.square = square;
        this.capacity = capacity;
        this.amountOfVehicles = amountOfVehicles;
    }

    public Room(String name, int floor, int price, float square, int capacity) {
        this.name = name;
        this.floor = floor;
        this.price = price;
        this.square = square;
        this.capacity = capacity;
    }

    public Room(String _id) {
        this._id = _id;
    }

    public Room(int amountOfVehicles) {
        this.amountOfVehicles = amountOfVehicles;
    }

    public String getSlotStatus() {
        return slotStatus;
    }

    public void setSlotStatus(String slotStatus) {
        this.slotStatus = slotStatus;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public float getSquare() {
        return square;
    }

    public void setSquare(float square) {
        this.square = square;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getamountOfVehicles() {
        return amountOfVehicles;
    }

    public void setamountOfVehicles(int amountOfVehicles) {
        this.amountOfVehicles = amountOfVehicles;
    }
}
