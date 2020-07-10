package com.example.Models.Contract;

import com.google.gson.annotations.SerializedName;

public class ContractExtensionBody {
    @SerializedName("dueDate")
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
