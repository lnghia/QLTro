package com.example.Models.Customer;

import java.util.ArrayList;

public class CustomerApiResponse {
    private boolean success;
    private ArrayList<Customer> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ArrayList<Customer> getData() {
        return data;
    }

    public void setData(ArrayList<Customer> data) {
        this.data = data;
    }
}
