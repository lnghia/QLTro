package com.example.Models.Customer;

public class CustomerGetDetailApiResponse {
    private boolean success;
    private DeleteCustomerApiResponse data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public DeleteCustomerApiResponse getData() {
        return data;
    }

    public void setData(DeleteCustomerApiResponse data) {
        this.data = data;
    }
}
