package com.example.qltr.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.qltr.R;
import com.example.qltr.main.MainActivity;

public class LoginActivity extends AppCompatActivity implements LoginContract.LoginView{
    LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void activateSignUpActivity(View view){

    }

    public void loginOnClick(View view){

    }

    @Override
    public void loginSuccess() {
        Intent intent = new Intent(this, MainActivity.class);
        //intent.putExtra();
        startActivity(intent);
    }

    @Override
    public void loginFailure(String error) {

    }
}
