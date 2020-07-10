package com.example.Models.Contract;

public class ContractExtensionBody {
    private String dueDate;

    public ContractExtensionBody(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }
}
