package com.example.Models.Report;

import java.util.ArrayList;

public class ReportGetApiResponse {
    private boolean success;
    private ArrayList<Report> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ArrayList<Report> getData() {
        return data;
    }

    public void setData(ArrayList<Report> data) {
        this.data = data;
    }
}
