package com.example.qltr.khachtro.KhachTroDetail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.Models.Customer.CreateCustomerApiBody;
import com.example.Models.Customer.Customer;
import com.example.Models.Room.Room;
import com.example.Models.Room.RoomId;
import com.example.Utils.DatePicker.DatePickerFragment;
import com.example.Utils.StatusDesc;
import com.example.qltr.R;

public class DetailKhachTroActivity extends AppCompatActivity implements DetailKhachTroContract.View {
    private EditText customerName;
    private EditText customerPhone;
    private EditText customerBirthday;
    private EditText customerIdNum;
    private EditText customerAddress;
    private EditText customerDesc;

    private String token;
    private String roomId;
    private String roomName;
    private int floor;

    private DetailKhachTroContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_khach_tro);

        getToken();
        getRoomId();
        getFloor();

        assignViews();
        initPresenter();
    }

    private void getToken(){
        token=getIntent().getExtras().getString("token");
    }

    private void getRoomId(){
        roomId=getIntent().getExtras().getString("roomId");
    }

    private void getRoomName(){
        roomName=getIntent().getExtras().getString("roomName");
    }

    private void getFloor(){
        floor=getIntent().getExtras().getInt("floor");
    }

    private void initPresenter(){
        presenter=new DetailKhachTroPresenter(this, this);
    }

    private void assignViews(){
        customerAddress=findViewById(R.id.customer_desc_edt);
        customerName=findViewById(R.id.customer_name_edt);
        customerPhone=findViewById(R.id.customer_phone_edt);
        customerBirthday=findViewById(R.id.customer_birthday_edt);
        customerIdNum=findViewById(R.id.customer_citizen_id_edt);
        customerDesc=findViewById(R.id.customer_note_edt);
    }

    private void showDatePicker(){
        DialogFragment dtPicker=new DatePickerFragment(customerBirthday);

        dtPicker.show(getSupportFragmentManager(), "datePicker");
    }

    public void showDatePickerDialog(View view) {
        DialogFragment dtPicker=new DatePickerFragment(customerBirthday);

        dtPicker.show(getSupportFragmentManager(), "datePicker");

//        InputMethodManager imm=(InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
//
//        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void addCustomerOnClick(View view) {
        presenter.addNewCustomer(token,new CreateCustomerApiBody(customerName.getText().toString(),
                                                                                 customerPhone.getText().toString(),
                                                                                 customerBirthday.getText().toString(),
                                                                                 customerIdNum.getText().toString(),
                                                                                 customerAddress.getText().toString(),
                                                                                 customerDesc.getText().toString(),
                                                                                 roomId,
                                                                                 floor));
    }

    public void cancelOnAddingCustomerOnClick(View view) {
        finish();
    }

    @Override
    public void raiseMessage(String mess) {
        Toast.makeText(this, mess, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateItemInserted(int pos) {

    }

    @Override
    public void updateDataSetChanged() {

    }

    @Override
    public void resetViews() {
        customerBirthday.setText("");
        customerDesc.setText("");
        customerIdNum.setText("");
        customerAddress.setText("");
        customerName.setText("");
        customerPhone.setText("");
    }
}
