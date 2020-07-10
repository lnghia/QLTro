package com.example.qltr.phongtro.ThemPhTr;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.Models.Room.Room;
import com.example.qltr.R;

public class ThemPhongTroActivity extends AppCompatActivity implements ThemPhongTroContract.View {
    private ThemPhongTroPresenter presenter;
    private EditText roomNameEdt;
    private EditText floorEdt;
    private EditText priceEdt;
    private EditText areaEdt;
    private EditText capacityEdt;
    private EditText vehicleNum;
    private String token;
    private int filteredBy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_phong_tro);

        assignViews();
        initPresenter();
        getToken();
        getFilterType();
    }

    private void assignViews() {
        roomNameEdt = findViewById(R.id.ten_phong_edt);
        floorEdt = findViewById(R.id.tang_edt);
        priceEdt = findViewById(R.id.gia_phong_edt);
        areaEdt = findViewById(R.id.dt_edt);
        capacityEdt = findViewById(R.id.suc_chua_edt);
    }

    private void getFilterType(){
        filteredBy=getIntent().getExtras().getInt("filterType");
    }

    private void getToken() {
        token = getIntent().getExtras().getString("token");
    }

    @Override
    public void initPresenter() {
        presenter = new ThemPhongTroPresenter(this, this);
    }

    @Override
    public void createSuccess(String note) {
        raiseNotification(note);
    }

    public void cancelCreateRoomOnCLick(View view) {
        finish();
    }

    public void createRoomOnCLick(View view) {
        presenter.createNewRoom(token, new Room(roomNameEdt.getText().toString(),
                                              Integer.parseInt(floorEdt.getText().toString()),
                                              Integer.parseInt(priceEdt.getText().toString()),
                                              Float.parseFloat(areaEdt.getText().toString()),
                                              Integer.parseInt(capacityEdt.getText().toString())), filteredBy);
    }

    @Override
    public void raiseNotification(String note) {
        Toast.makeText(this, note, Toast.LENGTH_SHORT).show();
    }
}
