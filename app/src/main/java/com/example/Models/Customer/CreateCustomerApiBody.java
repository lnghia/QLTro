package com.example.Models.Customer;

import com.example.Models.Room.Room;

public class CreateCustomerApiBody {
    private String name;
    private String email;
    private String phoneNumber;
    private String birthday;
    private String identityCard;
    private String province;
    private String district;
    private String ward;
    private String address;
    private String notes;
    private String roomId;
    private int floor;

    public CreateCustomerApiBody(String name, String email, String phoneNumber, String birthday, String identityCard, String address, String notes, String roomId) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.birthday = birthday;
        this.identityCard = identityCard;
        this.address = address;
        this.notes = notes;
        this.roomId = roomId;
    }

    public CreateCustomerApiBody(String name, String phoneNumber, String birthday, String identityCard, String address, String notes, String roomId, int floor) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.birthday = birthday;
        this.identityCard = identityCard;
        this.address = address;
        this.notes = notes;
        this.roomId = roomId;
        this.floor = floor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
}
