package com.example.Models.Contract;

public class CreateContractApiResponse {
    private boolean success;
    private Contract data;

    public Contract getData() {
        return data;
    }

    public void setData(Contract data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
