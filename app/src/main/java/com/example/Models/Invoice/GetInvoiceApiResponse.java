package com.example.Models.Invoice;

import java.util.ArrayList;

public class GetInvoiceApiResponse {
    private boolean success;
    private ArrayList<Invoice> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ArrayList<Invoice> getData() {
        return data;
    }

    public void setData(ArrayList<Invoice> data) {
        this.data = data;
    }
}
