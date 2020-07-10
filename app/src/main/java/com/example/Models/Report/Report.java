package com.example.Models.Report;

public class Report {
    private String _id;
    private String name;
    private String description;
    private String status;
    private String userId;

    public Report(String name, String description, String status, String userId) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.userId = userId;
    }

    public Report(String status) {
        this.status = status;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
