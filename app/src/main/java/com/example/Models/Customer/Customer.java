package com.example.Models.Customer;

import com.example.Models.Room.Room;

public class Customer {
    private String _id;
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
    private Room roomId;

    public Customer(String name, String email, String phoneNumber, String birthday, String identityCard, String province, String district, String ward, String address, String notes, Room roomId) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.birthday = birthday;
        this.identityCard = identityCard;
        this.province = province;
        this.district = district;
        this.ward = ward;
        this.address = address;
        this.notes = notes;
        this.roomId = roomId;
    }

    public Customer(String name, String phoneNumber, String birthday, String identityCard, String address, String notes) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.birthday = birthday;
        this.identityCard = identityCard;
        this.address = address;
        this.notes = notes;
    }

    public Customer(String name, String phoneNumber, String birthday, String identityCard, String address, String notes, Room roomId) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.birthday = birthday;
        this.identityCard = identityCard;
        this.address = address;
        this.notes = notes;
        this.roomId = roomId;
    }

    public Customer(String _id, String name, String identityCard, Room roomId) {
        this._id = _id;
        this.name = name;
        this.identityCard = identityCard;
        this.roomId = roomId;
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

    public Room getRoomId() {
        return roomId;
    }

    public void setRoomId(Room roomId) {
        this.roomId = roomId;
    }
}
