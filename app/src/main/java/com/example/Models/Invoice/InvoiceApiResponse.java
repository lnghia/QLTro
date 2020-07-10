package com.example.Models.Invoice;

public class InvoiceApiResponse {
    private String _id;
    private int consumptionElectric;
    private int consumptionWater;
    private int waterCost;
    private int electricCost;
    private int waterPrice;
    private int electricPrice;
    private int internetPrice;
    private int parkingPrice;
    private int cleanPrice;
    private int totalPrice;
    private String roomId;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public int getConsumptionElectric() {
        return consumptionElectric;
    }

    public void setConsumptionElectric(int consumptionElectric) {
        this.consumptionElectric = consumptionElectric;
    }

    public int getConsumptionWater() {
        return consumptionWater;
    }

    public void setConsumptionWater(int consumptionWater) {
        this.consumptionWater = consumptionWater;
    }

    public int getWaterCost() {
        return waterCost;
    }

    public void setWaterCost(int waterCost) {
        this.waterCost = waterCost;
    }

    public int getElectricCost() {
        return electricCost;
    }

    public void setElectricCost(int electricCost) {
        this.electricCost = electricCost;
    }

    public int getWaterPrice() {
        return waterPrice;
    }

    public void setWaterPrice(int waterPrice) {
        this.waterPrice = waterPrice;
    }

    public int getElectricPrice() {
        return electricPrice;
    }

    public void setElectricPrice(int electricPrice) {
        this.electricPrice = electricPrice;
    }

    public int getInternetPrice() {
        return internetPrice;
    }

    public void setInternetPrice(int internetPrice) {
        this.internetPrice = internetPrice;
    }

    public int getParkingPrice() {
        return parkingPrice;
    }

    public void setParkingPrice(int parkingPrice) {
        this.parkingPrice = parkingPrice;
    }

    public int getCleanPrice() {
        return cleanPrice;
    }

    public void setCleanPrice(int cleanPrice) {
        this.cleanPrice = cleanPrice;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
}
