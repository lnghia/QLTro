package com.example.Models.Customer;

public class AddCustomerApiResponse {
    private boolean success;
    private Customer data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Customer getData() {
        return data;
    }

    public void setData(Customer data) {
        this.data = data;
    }
}
