package com.example.Models.Report;

public class ReportCreateApiResponse {
    private boolean success;
    private Report data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Report getData() {
        return data;
    }

    public void setData(Report data) {
        this.data = data;
    }
}
