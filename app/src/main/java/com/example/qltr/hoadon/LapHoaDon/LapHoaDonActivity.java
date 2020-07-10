package com.example.qltr.hoadon.LapHoaDon;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.Models.Invoice.Invoice;
import com.example.Models.Room.RoomId;
import com.example.qltr.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LapHoaDonActivity extends AppCompatActivity implements LapHoaDonContract.View {

    private Spinner roomSpinner;
    private EditText soDien;
    private EditText soNuoc;
    private EditText tienDien;
    private EditText tienNuoc;
    private EditText tienPhong;
    private EditText tienMang;
    private EditText tienGuiXe;
    private EditText tongTien;

    private ArrayList<String> roomNames;
    private HashMap<String, String> rooms;
    private ArrayAdapter<String> dataAdapter;

    private LapHoaDonPresenter presenter;
    private String token;
    private String selectedRoomId;
    private String roomName;

    //    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lap_hoa_don);

        roomNames = new ArrayList<>();
        rooms = new HashMap<>();

        assignViews();
        initPresenter();
        getToken();
        getRoomName();
        getId();

//        configRoomSpinner();
    }

    //    @RequiresApi(api = Build.VERSION_CODES.N)
//    private void configRoomSpinner() {
//        initRooms();
//        initRoomNames();
//
//        dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, roomNames);
//
//        dataAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
//
//        roomSpinner.setAdapter(dataAdapter);
//
//        roomSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                selectedRoomId = rooms.get(roomNames.get(i));
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
//    }

    private void getToken() {
        token = getIntent().getExtras().getString("token");
    }

    private void getId(){ selectedRoomId=getIntent().getExtras().getString("roomId"); }

    private void getRoomName(){
        roomName=getIntent().getExtras().getString("roomName");
    }

    private void assignViews() {
        soDien=findViewById(R.id.so_dien_edt);
        soNuoc=findViewById(R.id.so_nuoc_edt);
        tienDien=findViewById(R.id.gia_dien_edt);
        tienNuoc=findViewById(R.id.gia_nuoc_edt);
        tienPhong=findViewById(R.id.gia_phong_edt);
        tienMang=findViewById(R.id.internet_edt);
        tienGuiXe=findViewById(R.id.xe_edt);
        tongTien=findViewById(R.id.tong_edt);
    }

    private void initPresenter() {
        presenter = new LapHoaDonPresenter(this, this);
    }

    //    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initRoomNames() {
//        rooms.forEach((key, val) -> roomNames.add(val));
        roomNames = (ArrayList<String>) rooms.keySet();
    }

    private void initRooms() {
        presenter.loadRooms(token, rooms);
    }

    public void createInvoiceOnClick(View view) {
        presenter.createInvoice(token, selectedRoomId, new Invoice(Integer.parseInt(soDien.getText().toString()),
                Integer.parseInt(soNuoc.getText().toString()),
                Integer.parseInt(tienNuoc.getText().toString()),
                Integer.parseInt(tienDien.getText().toString()),
                15,
                15,
                Integer.parseInt(tienMang.getText().toString()),
                Integer.parseInt(tienGuiXe.getText().toString()),
                15,
                Integer.parseInt(tongTien.getText().toString()),
                new RoomId(selectedRoomId, roomName)));
    }

    public void cancelEditRoomOnCLick(View view) {
        finish();
    }

    @Override
    public void finishActivity() {
        finish();
    }
}
