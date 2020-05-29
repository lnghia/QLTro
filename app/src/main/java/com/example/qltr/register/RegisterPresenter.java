package com.example.qltr.register;

import android.content.Context;

import com.example.APIHelpers.Worker;
import com.example.Models.Account;
import com.example.Models.RegisteredAcc;
import com.example.Models.ResponseStatus;
import com.example.qltr.R;

import java.util.regex.Pattern;

public class RegisterPresenter implements RegisterContract.Presenter {
    private Worker<ResponseStatus> wk;
    private RegisterContract.RegisterView registerView;
    private Context mContext;
    private ResponseStatus status;

    public RegisterPresenter(){
        wk=new Worker<>(ResponseStatus.class);
    }

    public RegisterPresenter(RegisterContract.RegisterView view, Context context){
        registerView=view;
        mContext=context;
        wk=new Worker<>(ResponseStatus.class);
    }

    @Override
    public void handleRegister(RegisteredAcc acc) {
//        if(!isValidUserName(acc.getUsername())){
//            registerView.registerFailure("Tên đăng nhập không hợp lệ !");
//
//            return;
//        }

        if(!acc.getPassword().equals(acc.getPasswordConfirm())){
            registerView.registerFailure("Mật khẩu xác nhận và mật khẩu đăng kí không trùng khớp !");

            return;
        }

        try {
            wk.post.setObject(acc);
            status=(ResponseStatus) wk.post.execute("https://quanlytro.herokuapp.com/users/register").get();

            if(status==null || !status.getSuccess()){
                registerView.registerFailure("Không thể đăng kí ! Vui lòng thực hiện lại sau giây lát");
            }
            else{
                registerView.registerSuccess();
            }
        }catch (Exception e){
            registerView.registerFailure("Không thể thực hiện đăng kí lúc này !");
        }

    }

    @Override
    public boolean isValidUserName(String userName) {
        return Pattern.matches(mContext.getResources().getString(R.string.user_name_regex), userName);
    }
}
