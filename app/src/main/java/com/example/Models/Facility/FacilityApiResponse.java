package com.example.Models.Facility;

import com.example.Models.Facility.Facility;

public class FacilityApiResponse {
    private Boolean success;
    private Facility data;
    private String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Facility getData() {
        return data;
    }

    public void setData(Facility data) {
        this.data = data;
    }
}
