package com.example.Models.Invoice;

public class InvoiceGetByStatus extends InvoiceGetAllRoom {
    private Boolean isPaid;

    public InvoiceGetByStatus(String startDate, String endDate) {
        super(startDate, endDate);
    }

    public InvoiceGetByStatus(String startDate, String endDate, Boolean isPaid) {
        super(startDate, endDate);
        this.isPaid = isPaid;
    }

    public InvoiceGetByStatus(Boolean isPaid) {
        super(null, null);
        this.isPaid = isPaid;
    }

    public Boolean getPaid() {
        return isPaid;
    }

    public void setPaid(Boolean paid) {
        isPaid = paid;
    }
}
