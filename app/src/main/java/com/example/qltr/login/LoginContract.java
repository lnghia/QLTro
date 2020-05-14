package com.example.qltr.login;

import java.util.*;

public interface LoginContract {
    interface LoginView{
        void loginSuccess();
        void loginFailure(String error);
    }

    interface Presenter{
        boolean isValid(String userName, String password);
    }
}
