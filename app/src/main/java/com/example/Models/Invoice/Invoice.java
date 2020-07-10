package com.example.Models.Invoice;

import com.example.Models.Room.RoomId;

public class Invoice {
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
    private RoomId roomId;
    private String createdAt;
    private boolean isPaid;

    public Invoice(String _id, int consumptionElectric, int consumptionWater, int waterCost, int electricCost, int waterPrice, int electricPrice, int internetPrice, int parkingPrice, int cleanPrice, int totalPrice, RoomId roomId) {
        this._id = _id;
        this.consumptionElectric = consumptionElectric;
        this.consumptionWater = consumptionWater;
        this.waterCost = waterCost;
        this.electricCost = electricCost;
        this.waterPrice = waterPrice;
        this.electricPrice = electricPrice;
        this.internetPrice = internetPrice;
        this.parkingPrice = parkingPrice;
        this.cleanPrice = cleanPrice;
        this.totalPrice = totalPrice;
        this.roomId = roomId;
    }

    public Invoice(int consumptionElectric, int consumptionWater, int waterCost, int electricCost, int waterPrice, int electricPrice, int internetPrice, int parkingPrice, int cleanPrice, int totalPrice, RoomId roomId) {
        this.consumptionElectric = consumptionElectric;
        this.consumptionWater = consumptionWater;
        this.waterCost = waterCost;
        this.electricCost = electricCost;
        this.waterPrice = waterPrice;
        this.electricPrice = electricPrice;
        this.internetPrice = internetPrice;
        this.parkingPrice = parkingPrice;
        this.cleanPrice = cleanPrice;
        this.totalPrice = totalPrice;
        this.roomId = roomId;
    }

    public Invoice(int consumptionElectric, int consumptionWater, int waterCost, int electricCost, int waterPrice, int electricPrice, int internetPrice, int parkingPrice, int cleanPrice, int totalPrice) {
        this.consumptionElectric = consumptionElectric;
        this.consumptionWater = consumptionWater;
        this.waterCost = waterCost;
        this.electricCost = electricCost;
        this.waterPrice = waterPrice;
        this.electricPrice = electricPrice;
        this.internetPrice = internetPrice;
        this.parkingPrice = parkingPrice;
        this.cleanPrice = cleanPrice;
        this.totalPrice = totalPrice;
    }



    public Invoice(int consumptionElectric, int consumptionWater, int waterCost, int electricCost, int internetPrice, int parkingPrice, int totalPrice, RoomId roomId) {
        this.consumptionElectric = consumptionElectric;
        this.consumptionWater = consumptionWater;
        this.waterCost = waterCost;
        this.electricCost = electricCost;
        this.internetPrice = internetPrice;
        this.parkingPrice = parkingPrice;
        this.totalPrice = totalPrice;
        this.roomId = roomId;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

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

    public RoomId getRoomId() {
        return roomId;
    }

    public void setRoomId(RoomId roomId) {
        this.roomId = roomId;
    }

    public static Invoice parseInvoice(CreateInvoiceApiResponse data){
        return new Invoice(data.getData().get_id(),
                           data.getData().getConsumptionElectric(),
                           data.getData().getConsumptionWater(),
                           data.getData().getWaterCost(),
                           data.getData().getElectricCost(),
                           data.getData().getWaterPrice(),
                           data.getData().getElectricPrice(),
                           data.getData().getInternetPrice(),
                           data.getData().getParkingPrice(),
                           data.getData().getCleanPrice(),
                           data.getData().getTotalPrice(),
                           new RoomId(data.getData().getRoomId(), ""));
    }
}
