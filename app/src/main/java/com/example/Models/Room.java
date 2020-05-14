package com.example.Models;

public class Room {
    int id;
    int vehicleNum;
    int takenSpot;
    int area;
    int debt;

    public Room(int id, int vehicleNum, int takenSpot, int area, int debt) {
        this.id = id;
        this.vehicleNum = vehicleNum;
        this.takenSpot = takenSpot;
        this.area = area;
        this.debt = debt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVehicleNum() {
        return vehicleNum;
    }

    public void setVehicleNum(int vehicleNum) {
        this.vehicleNum = vehicleNum;
    }

    public int getTakenSpot() {
        return takenSpot;
    }

    public void setTakenSpot(int takenSpot) {
        this.takenSpot = takenSpot;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public int getDebt() {
        return debt;
    }

    public void setDebt(int debt) {
        this.debt = debt;
    }
}
