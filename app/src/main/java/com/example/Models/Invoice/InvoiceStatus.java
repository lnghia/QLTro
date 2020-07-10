package com.example.Models.Invoice;

public class InvoiceStatus {
    private boolean isPaid;

    public InvoiceStatus(boolean isPaid) {
        this.isPaid = isPaid;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }
}
