package com.example.qltr.login;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
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
    Button loginBtn;
    TextView registerTxt;
    ConstraintLayout loginLayout;
//    ProgressBar verificationProgress;
//    TextView verificationTxt;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
        initSharedPref();
        initPresenter();
        getToken();

        if(token!=null){
            presenter.alreadyLoggedIn(token);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (!presenter.isOnline()) {
            raiseConnectionAlert();
        }
    }

    private void initView() {
        userNameEdt = findViewById(R.id.username_input);
        passwordEdt = findViewById(R.id.password_input);
        loginBtn = findViewById(R.id.login_btn);
        registerTxt = findViewById(R.id.register_txt);
//        verificationProgress = findViewById(R.id.verification_progress);
//        verificationTxt = findViewById(R.id.verification_txt);
        progressDialog=new ProgressDialog(this);
        progressDialog.setCancelable(false);
        loginLayout=findViewById(R.id.login_layout);
//        hideLoadingViews();
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
        loading();
//        progressDialog.show();
//
//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                // YOur Request code here?
//
//            }
//        }, 2000);

//        loading();
//        ((ViewGroup)findViewById(R.id.login_layout)).invalidate();
//        loginLayout.invalidate();

        presenter.handleLogin(userNameEdt.getText().toString(), passwordEdt.getText().toString());
//        loading();
    }

    @Override
    public void loginSuccess(String token) {
        progressDialog.dismiss();

        sharedPreferences.edit().putString("token", token).apply();

        startActivity(new Intent(this, MainActivity.class).putExtra("token", token));

        finish();
    }

    @Override
    public void loginFailure(String error) {
        progressDialog.dismiss();

        Toast toast=Toast.makeText(this, error, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 200);
        TextView v=(TextView) toast.getView().findViewById(android.R.id.message);
        v.setTextColor(Color.RED);
        toast.show();

        resetTxtInput();
    }

    @Override
    public void resetTxtInput() {
        userNameEdt.setText("");
        passwordEdt.setText("");
    }

    @Override
    public void raiseConnectionAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Không thể kết nối internet !")
                .setMessage("Xin vui lòng kết nối wifi hoặc 3g/4g rồi khởi động lại ứng dụng")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });

        AlertDialog alert = builder.create();

        alert.show();
    }

    @Override
    public void loading() {
        View view = getCurrentFocus();

        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
//
//        verificationProgress.setVisibility(View.VISIBLE);
//        verificationTxt.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingViews() {
//        verificationProgress.setVisibility(View.INVISIBLE);
//        verificationTxt.setVisibility(View.INVISIBLE);
//        verificationTxt.setVisibility(View.INVISIBLE);
//        verificationProgress.setVisibility(View.INVISIBLE);
    }
}
