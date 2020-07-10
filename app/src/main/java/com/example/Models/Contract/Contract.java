package com.example.Models.Contract;

public class Contract {
    private String _id;
    private String customerId;
    private int deposit;
    private String identityCard;
    private String roomId;
    private String name;
    private String dueDate;
    private String entryDate;
    private boolean isPayAtEndMonth;

    public Contract(String identityCard, String roomId, String name, String dueDate, String entryDate) {
        this.identityCard = identityCard;
        this.roomId = roomId;
        this.name = name;
        this.dueDate = dueDate;
        this.entryDate = entryDate;
    }

    public Contract(String identityCard, String roomId, String name, String dueDate, String entryDate, int deposit) {
        this.deposit = deposit;
        this.identityCard = identityCard;
        this.roomId = roomId;
        this.name = name;
        this.dueDate = dueDate;
        this.entryDate = entryDate;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public int getDeposit() {
        return deposit;
    }

    public void setDeposit(int deposit) {
        this.deposit = deposit;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    public boolean isPayAtEndMonth() {
        return isPayAtEndMonth;
    }

    public void setPayAtEndMonth(boolean payAtEndMonth) {
        isPayAtEndMonth = payAtEndMonth;
    }
}
