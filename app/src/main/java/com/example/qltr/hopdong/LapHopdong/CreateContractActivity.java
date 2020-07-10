package com.example.qltr.hopdong.LapHopdong;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.Models.Contract.Contract;
import com.example.Utils.DatePicker.DatePickerFragment;
import com.example.qltr.R;
import com.example.qltr.khachtro.DashBoard.DashboardKhachTroActivity;
import com.example.qltr.phongtro.MenuPhongTro.MenuPhongActivity;

import java.util.regex.Pattern;

public class CreateContractActivity extends AppCompatActivity implements CreateContractContract.View {

    private CreateContractPresenter presenter;

    private String token;
    private String roomId;
    private int roomIndex;

    private EditText customerName;
    private EditText customerCitizenId;
    private EditText dueDate;
    private EditText entryDate;
    private EditText deposit;
//    private SwitchCompat isPayAtEndMonth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contract);

        initPresenter();

        getConstants();
        assignViews();
    }

    private void getToken(){
        token=getIntent().getExtras().getString("token");
    }

    private void getRoomId(){
        roomId=getIntent().getExtras().getString("roomId");
    }

    private void getRoomIndex(){ roomIndex=getIntent().getExtras().getInt("roomIndex"); }

    private void getConstants(){
        getToken();
        getRoomId();
        getRoomIndex();
    }

    private void initPresenter(){
        presenter=new CreateContractPresenter(this, this);
    }

    private void assignViews(){
        customerName=findViewById(R.id.contract_customer_name_edt);
        customerCitizenId=findViewById(R.id.contract_citizen_id_edt);
        dueDate=findViewById(R.id.contract_dueDate_edt);
        entryDate=findViewById(R.id.contract_entryDate_edt);
        deposit=findViewById(R.id.contract_deposit_edt);
//        isPayAtEndMonth=findViewById(R.id.contract_pay_at_edt);
    }

    public void createInvoiceOnClick(View view) {
        if(customerCitizenId.getText().toString().isEmpty() || customerName.getText().toString().isEmpty() || dueDate.getText().toString().isEmpty() || entryDate.getText().toString().isEmpty()){
            Toast.makeText(this, "Dữ liệu nhập không đầy đủ!", Toast.LENGTH_SHORT).show();

            return;
        }

        if(!Pattern.matches(getString(R.string.citizen_id_regex), customerCitizenId.getText().toString()) ||
            !Pattern.matches(getString(R.string.name_regex), customerName.getText().toString()) ){
            boolean t1=Pattern.matches(getString(R.string.citizen_id_regex), customerCitizenId.getText().toString());
            boolean t2=Pattern.matches(getString(R.string.name_regex), customerName.getText().toString());

            Toast.makeText(this, "Dữ liệu nhập không hợp lệ!", Toast.LENGTH_SHORT).show();

            return;
        }

        presenter.createContract(token, roomIndex, new Contract(customerCitizenId.getText().toString(),
                                                                     roomId,
                                                                     customerName.getText().toString(),
                                                                     dueDate.getText().toString(),
                                                                     entryDate.getText().toString(),
                                                                     Integer.parseInt(deposit.getText().toString())));
    }

    public void cancelEditRoomOnCLick(View view) {
        finish();
    }

    private void initDueDatePicker(){
        DialogFragment dtPicker=new DatePickerFragment(dueDate);

        dtPicker.show(getSupportFragmentManager(), "datePicker");
    }

    private void initEntryDatePicker(){
        DialogFragment dtPicker=new DatePickerFragment(entryDate);

        dtPicker.show(getSupportFragmentManager(), "datePicker");
    }

    public void dueDatePickerOnClick(View view) {
        initDueDatePicker();
    }

    public void entryDatePickerOnClick(View view) {
        initEntryDatePicker();
    }

    @Override
    public void createSuccess() {
        Toast.makeText(this, "Thao tác thành công!", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void createFailure(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
