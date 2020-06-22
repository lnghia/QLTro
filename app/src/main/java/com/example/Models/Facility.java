package com.example.Models;

import com.google.gson.annotations.SerializedName;

public class Facility {
    private String _id;
    private String name;
    @SerializedName("price")
    private int unitPrice;
    private int quantity;
    private String description;

    public Facility(){}

    public Facility(String name, int unitPrice, int quantity, String description) {
        this.name = name;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.description = description;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
