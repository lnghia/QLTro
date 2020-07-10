package com.example.qltr.login;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Toast;

import com.example.APIHelpers.RetrofitClient;
import com.example.APIHelpers.Worker;
import com.example.Models.Account;
import com.example.Models.RegisteredAcc;
import com.example.Models.SignedInAcc;
import com.example.Utils.StatusDesc;
import com.example.qltr.R;
import com.google.gson.Gson;

import java.util.regex.Pattern;

import androidx.annotation.RequiresApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        worker = new Worker<>(SignedInAcc.class/*, mContext*/);
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
//        ProgressDialog progressDialog=new ProgressDialog(mContext);
//        progressDialog.setCancelable(false);
//        progressDialog.show();

        RetrofitClient.getInstance().getLoginApi().login(new Account(userName, password)).enqueue(new Callback<SignedInAcc>() {
            @Override
            public void onResponse(Call<SignedInAcc> call, Response<SignedInAcc> response) {
                if(response!=null && response.body()!=null){
                    if(response.body().getSuccess()){
                        loginView.loginSuccess(response.body().getToken());
                    }
                    else{
                        loginView.loginFailure(StatusDesc.getInstance().getErrDesc(response.code()));
                    }
                }
            }

            @Override
            public void onFailure(Call<SignedInAcc> call, Throwable t) {
                loginView.loginFailure("Vui lòng kiểm tra kết nối và thử lại !");
            }
        });

//        try {
//            worker.post.setObject(new Account(userName, password));
//
//            usr = (SignedInAcc)worker.post.execute("https://quanlytro.herokuapp.com/users/login").get();
//
////            Toast.makeText(mContext, usr.getToken(), Toast.LENGTH_LONG).show();
//
////            progressDialog.dismiss();
//            if (usr.getSuccess()) {
//                loginView.loginSuccess(usr.getToken());
//            } else {
//                loginView.loginFailure(mContext.getResources().getString(R.string.login_err_invalid_inp));
//            }
//        } catch (Exception e) {
////            e.printStackTrace();
//            loginView.loginFailure("Vui lòng kiểm tra kết nối Internet !");
//        }
    }

    @Override
    public boolean isOnline() {
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo connectionInfo = connectivityManager.getActiveNetworkInfo();

        return connectionInfo!=null &&  connectionInfo.isConnected();
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
