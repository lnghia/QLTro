package com.example.Models;

public class SignedInAcc {
    private  String accessToken;
    private  boolean success;

    public SignedInAcc(){}

    public SignedInAcc(String token, boolean isSuccess) {
        this.accessToken = token;
        this.success = isSuccess;
    }

    public String getToken() {
        return accessToken;
    }

    public void setToken(String token) {
        this.accessToken = token;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
