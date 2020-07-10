package com.example.qltr.khachtro.SuaKhachTro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.Models.Customer.Customer;
import com.example.Models.Room.RoomId;
import com.example.Utils.DatePicker.DatePickerFragment;
import com.example.Utils.ListContainers;
import com.example.qltr.R;

public class SuaKhachTroActivity extends AppCompatActivity implements SuaKhachTroContract.View {

    private String token;
    private String id;
    private int index;

    private EditText customerName;
    private EditText phoneNumber;
    private EditText birthDay;
    private EditText citizenId;
    private EditText address;
    private EditText note;

    private SuaKhachTroPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_khach_tro);

        getConstants();

        assignViews();
//        setContent();
        initPresenter();

        getDetail();
        setContent();
    }

    private void getToken(){
        token=getIntent().getExtras().getString("token");
    }

    private void getId(){
        id=getIntent().getExtras().getString("Id");
    }

    private void getIndex(){
        index=getIntent().getExtras().getInt("Index");
    }

    private void initPresenter(){
        presenter=new SuaKhachTroPresenter(this, this);
    }

    private void getDetail(){
        // api get detail by id
        presenter.getDetail(token, id, index);
    }

    private void getConstants(){
        getToken();
        getId();
        getIndex();
    }

    private void assignViews(){
        customerName=findViewById(R.id.edit_customer_name_edt);
        phoneNumber=findViewById(R.id.edit_customer_phone_edt);
        birthDay=findViewById(R.id.edit_customer_birthday_edt);
        citizenId=findViewById(R.id.edit_customer_citizen_id_edt);
        address=findViewById(R.id.edit_customer_desc_edt);
        note=findViewById(R.id.edit_customer_note_edt);
    }

    private void setContent(){
        Customer customer= ListContainers.getInstance().getCustomers().get(index);

        customerName.setText(customer.getName()==null?"":customer.getName());
        phoneNumber.setText(customer.getPhoneNumber()==null?"":customer.getPhoneNumber());
        birthDay.setText(customer.getBirthday()==null?"":customer.getBirthday());
        citizenId.setText(customer.getIdentityCard()==null?"":customer.getIdentityCard());
        address.setText(customer.getAddress()==null?"":customer.getAddress());
        note.setText(customer.getNotes()==null?"":customer.getNotes());
    }

    public void editCustomerOnClick(View view) {
        presenter.edit(token, new Customer(customerName.getText().toString(),
                                                           phoneNumber.getText().toString(),
                                                           birthDay.getText().toString(),
                                                           citizenId.getText().toString(),
                                                           address.getText().toString(),
                                                           note.getText().toString()), id, index);
    }

    public void cancelOnEdittingCustomerOnClick(View view) {
        finish();
    }

    @Override
    public void editSuccess() {
        Toast.makeText(this, "Thao tác thành công", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void editFailure(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void showDatePickerDialog(View view) {
        DatePickerFragment datePickerFragment=new DatePickerFragment(birthDay);

//        String[] tokens=birthDay.getText().toString().split("-");
//
//        datePickerFragment.setDay(Integer.parseInt(tokens[2]));
//        datePickerFragment.setMonth(Integer.parseInt(tokens[1]));
//        datePickerFragment.setYear(Integer.parseInt(tokens[0]));

        datePickerFragment.show(getSupportFragmentManager(), "datePicker");
    }
}
