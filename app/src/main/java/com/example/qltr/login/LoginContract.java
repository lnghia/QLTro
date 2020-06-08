package com.example.qltr.login;

import java.util.*;

public interface LoginContract {
    interface LoginView {
        void loginSuccess(String token);

        void loginFailure(String error);

        void resetTxtInput();

        void raiseConnectionAlert();

        void loading();

        void hideLoadingViews();
    }

    interface Presenter {
        void handleLogin(String userName, String password);

        boolean isOnline();

        void alreadyLoggedIn(String token);

        boolean isValidUserName(String userName);
    }
}
