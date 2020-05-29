package com.example.Models;

public class RegisteredAcc {
    private String username;
    private String password;
    private String confirmPassword;
    private boolean isAdmin;

    public RegisteredAcc(){}

    public RegisteredAcc(String username, String password){
        this.username=username;
        this.password=password;
    }

    public RegisteredAcc(String username, String password, String confirmPassword, boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.isAdmin = isAdmin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return confirmPassword;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.confirmPassword = passwordConfirm;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
