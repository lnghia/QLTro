package com.example.Models.Invoice;

public class InvoiceGetByRoomId extends InvoiceGetAllRoom {
//    private String startDate;
//    private String endDate;
    private String roomId;

    public InvoiceGetByRoomId(String startDate, String endDate, String roomId) {
        super(startDate, endDate);
//        this.startDate = startDate;
//        this.endDate = endDate;
        this.roomId = roomId;
    }

//    public String getStartDate() {
//        return startDate;
//    }

//    public void setStartDate(String startDate) {
//        this.startDate = startDate;
//    }

//    public String getEndDate() {
//        return endDate;
//    }

//    public void setEndDate(String endDate) {
//        this.endDate = endDate;
//    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
}
