package com.example.qltr.login;

public class LoginPresenter implements LoginContract.Presenter {
    private LoginContract.LoginView loginView;

    public void setView(LoginContract.LoginView view) {
        loginView = view;
    }

    @Override
    public boolean isValid(String userName, String password) {
        return false;
    }
}
