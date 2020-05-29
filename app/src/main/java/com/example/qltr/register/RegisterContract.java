package com.example.qltr.register;

import com.example.Models.RegisteredAcc;
import com.example.Models.SignedInAcc;

public interface RegisterContract {
    interface RegisterView{
        void registerSuccess();

        void registerFailure(String err);

        void resetViews();
    }

    interface  Presenter{
        void handleRegister(RegisteredAcc acc);

        boolean isValidUserName(String userName);
    }
}
