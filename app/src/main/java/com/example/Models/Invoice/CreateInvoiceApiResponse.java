package com.example.Models.Invoice;

public class CreateInvoiceApiResponse {
    private boolean success;
    private InvoiceApiResponse data;

    public InvoiceApiResponse getData() {
        return data;
    }

    public void setData(InvoiceApiResponse data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    //    public CreateInvoiceApiResponse(String _id, int consumptionElectric, int consumptionWater, int waterCost, int electricCost, int waterPrice, int electricPrice, int internetPrice, int parkingPrice, int cleanPrice, int totalPrice, String roomId) {
//        this._id = _id;
//        this.consumptionElectric = consumptionElectric;
//        this.consumptionWater = consumptionWater;
//        this.waterCost = waterCost;
//        this.electricCost = electricCost;
//        this.waterPrice = waterPrice;
//        this.electricPrice = electricPrice;
//        this.internetPrice = internetPrice;
//        this.parkingPrice = parkingPrice;
//        this.cleanPrice = cleanPrice;
//        this.totalPrice = totalPrice;
//        this.roomId = roomId;
//    }
//
//    public boolean isSuccess() {
//        return success;
//    }
//
//    public void setSuccess(boolean success) {
//        this.success = success;
//    }
//
//    public String get_id() {
//        return _id;
//    }
//
//    public void set_id(String _id) {
//        this._id = _id;
//    }
//
//    public int getConsumptionElectric() {
//        return consumptionElectric;
//    }
//
//    public void setConsumptionElectric(int consumptionElectric) {
//        this.consumptionElectric = consumptionElectric;
//    }
//
//    public int getConsumptionWater() {
//        return consumptionWater;
//    }
//
//    public void setConsumptionWater(int consumptionWater) {
//        this.consumptionWater = consumptionWater;
//    }
//
//    public int getWaterCost() {
//        return waterCost;
//    }
//
//    public void setWaterCost(int waterCost) {
//        this.waterCost = waterCost;
//    }
//
//    public int getElectricCost() {
//        return electricCost;
//    }
//
//    public void setElectricCost(int electricCost) {
//        this.electricCost = electricCost;
//    }
//
//    public int getWaterPrice() {
//        return waterPrice;
//    }
//
//    public void setWaterPrice(int waterPrice) {
//        this.waterPrice = waterPrice;
//    }
//
//    public int getElectricPrice() {
//        return electricPrice;
//    }
//
//    public void setElectricPrice(int electricPrice) {
//        this.electricPrice = electricPrice;
//    }
//
//    public int getInternetPrice() {
//        return internetPrice;
//    }
//
//    public void setInternetPrice(int internetPrice) {
//        this.internetPrice = internetPrice;
//    }
//
//    public int getParkingPrice() {
//        return parkingPrice;
//    }
//
//    public void setParkingPrice(int parkingPrice) {
//        this.parkingPrice = parkingPrice;
//    }
//
//    public int getCleanPrice() {
//        return cleanPrice;
//    }
//
//    public void setCleanPrice(int cleanPrice) {
//        this.cleanPrice = cleanPrice;
//    }
//
//    public int getTotalPrice() {
//        return totalPrice;
//    }
//
//    public void setTotalPrice(int totalPrice) {
//        this.totalPrice = totalPrice;
//    }
//
//    public String getRoomId() {
//        return roomId;
//    }
//
//    public void setRoomId(String roomId) {
//        this.roomId = roomId;
//    }
}
