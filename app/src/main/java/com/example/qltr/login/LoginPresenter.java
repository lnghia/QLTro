package com.example.qltr.login;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.APIHelpers.Worker;
import com.example.Models.Account;
import com.example.Models.RegisteredAcc;
import com.example.Models.SignedInAcc;
import com.example.qltr.R;
import com.google.gson.Gson;

import java.util.regex.Pattern;

public class LoginPresenter implements LoginContract.Presenter {
    private LoginContract.LoginView loginView;
    private Worker<SignedInAcc> worker;
    private SignedInAcc usr;
    private Context mContext;

    public LoginPresenter() {
    }

    public class LoginTask extends AsyncTask<String, Void, SignedInAcc>{

        @Override
        protected SignedInAcc doInBackground(String... strings) {
            String url=strings[0];
            String usrName=strings[1];
            String pwd=strings[2];

            try{
                worker.post.setObject(new Account(usrName, pwd));

                return (SignedInAcc)worker.post.execute(url).get();
            }catch (Exception e){}

            return null;
        }

        @Override
        protected void onPostExecute(SignedInAcc acc) {
            super.onPostExecute(acc);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }

    public LoginPresenter(LoginContract.LoginView view, Context mContext) {
        setView(view);
        worker = new Worker<>(SignedInAcc.class);
        this.mContext = mContext;
    }

    public void setView(LoginContract.LoginView view) {
        loginView = view;
    }

    @Override
    public void handleLogin(String userName, String password) {
//        if (!isValidUserName(userName)) {
//            loginView.loginFailure(mContext.getResources().getString(R.string.login_err_invalid_inp));
//             return;
//        }

        try {
            worker.post.setObject(new Account(userName, password));

            usr = (SignedInAcc)worker.post.execute("https://quanlytro.herokuapp.com/users/login").get();



            Toast.makeText(mContext, usr.getToken(), Toast.LENGTH_LONG).show();

            if (usr.getSuccess()) {
                loginView.loginSuccess(usr.getToken());
            } else {
                loginView.loginFailure(mContext.getResources().getString(R.string.login_err_invalid_inp));
            }
        } catch (Exception e) {
//            e.printStackTrace();
            loginView.loginFailure("Lỗi xác thực !");
        }
    }

    @Override
    public void alreadyLoggedIn(String token) {
        loginView.loginSuccess(token);
    }

    @Override
    public boolean isValidUserName(String userName) {
        return Pattern.matches(mContext.getResources().getString(R.string.user_name_regex), userName);
    }
}
