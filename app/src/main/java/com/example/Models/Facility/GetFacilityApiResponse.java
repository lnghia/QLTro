package com.example.Models.Facility;

import java.util.List;

public class GetFacilityApiResponse {
    private boolean success;
    private List<Facility> data;
    private String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<Facility> getData() {
        return data;
    }

    public void setData(List<Facility> data) {
        this.data = data;
    }
}
