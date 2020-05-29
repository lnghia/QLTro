package com.example.qltr.login;

import java.util.*;

public interface LoginContract {
    interface LoginView {
        void loginSuccess(String token);

        void loginFailure(String error);
    }

    interface Presenter {
        void handleLogin(String userName, String password);

        void alreadyLoggedIn(String token);

        boolean isValidUserName(String userName);
    }
}
