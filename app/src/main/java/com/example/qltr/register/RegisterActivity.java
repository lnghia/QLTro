package com.example.qltr.register;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.Models.RegisteredAcc;
import com.example.Models.SignedInAcc;
import com.example.qltr.R;

public class RegisterActivity extends AppCompatActivity implements RegisterContract.RegisterView {
    EditText rgUsrName;
    EditText rgPwd;
    EditText rgConfirmPwd;
    SwitchCompat isAdmin;
    RegisterPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        assignViews();
        initPresenter();
    }

    public void assignViews(){
        rgUsrName=findViewById(R.id.reg_username_input);
        rgPwd=findViewById(R.id.reg_password_input);
        rgConfirmPwd=findViewById(R.id.reg_password_confirm);
        isAdmin=findViewById(R.id.is_admin_sw);
    }

    public void cancelOnClick(View view){
        finish();
    }

    public void initPresenter(){
        presenter=new RegisterPresenter(this, this);
    }

    public void registerOnClick(View view){
        presenter.handleRegister( new RegisteredAcc(
                rgUsrName.getText().toString(),
                rgPwd.getText().toString(),
                rgConfirmPwd.getText().toString(),
                isAdmin.isChecked()
                ));
    }

    @Override
    public void registerSuccess() {
        Toast.makeText(this, "Đăng kí thành công !", Toast.LENGTH_LONG).show();

        finish();
    }

    @Override
    public void registerFailure(String err) {
        Toast.makeText(this, err, Toast.LENGTH_LONG).show();

        resetViews();
    }

    @Override
    public void resetViews() {
        rgUsrName.setText("");
        rgPwd.setText("");
        rgConfirmPwd.setText("");
    }
}
