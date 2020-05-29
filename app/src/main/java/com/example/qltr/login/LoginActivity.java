package com.example.qltr.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qltr.R;
import com.example.qltr.main.MainActivity;
import com.example.qltr.register.RegisterActivity;

public class LoginActivity extends AppCompatActivity implements LoginContract.LoginView {
    LoginPresenter presenter;
    String token = null;
    SharedPreferences sharedPreferences;
    EditText userNameEdt;
    EditText passwordEdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
        initSharedPref();
        initPresenter();
        getToken();

//        if(token!=null){
//            presenter.alreadyLoggedIn(token);
//        }
    }

    private void initView() {
        userNameEdt = findViewById(R.id.username_input);
        passwordEdt = findViewById(R.id.password_input);
    }

    private void initPresenter() {
        presenter = new LoginPresenter(this, this);
    }

    private void initSharedPref() {
        sharedPreferences = getSharedPreferences("com.example.qltr.login", Context.MODE_PRIVATE);
    }

    private void getToken() {
        token = sharedPreferences.getString("token", null);
    }

    public void activateSignUpActivity(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
    }

    public void loginOnClick(View view) {
        presenter.handleLogin(userNameEdt.getText().toString(), passwordEdt.getText().toString());
    }

    @Override
    public void loginSuccess(String token) {
        sharedPreferences.edit().putString("username", token).apply();

        startActivity(new Intent(this, MainActivity.class).putExtra("token", token));
    }

    @Override
    public void loginFailure(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }
}
