package com.example.qltr.phongtro.SuaPhongTro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Models.Room.Room;
import com.example.Utils.ListContainers;
import com.example.qltr.R;

public class SuaPhongTroActivity extends AppCompatActivity implements SuaPhongTroContract.View {

    private SuaPhongTroContract.Presenter presenter;

    private String token;
    private int index;

    private Button editRoomBtn;
    private Button cancelBtn;
    private EditText roomNameEdt;
    private EditText roomFloorEdt;
    private EditText roomPriceEdt;
    private EditText roomAreaEdt;
    private EditText roomCapacityEdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_phong_tro);

        getInstances();
        initPresenter();
        assignViews();

        setContent();
    }

    private void getInstances() {
        token = getIntent().getExtras().getString("token");
        index = getIntent().getExtras().getInt("index");
    }

    private void assignViews() {
        roomAreaEdt = findViewById(R.id.edit_dt_edt);
        roomNameEdt = findViewById(R.id.edit_ten_phong_edt);
        roomCapacityEdt = findViewById(R.id.edit_suc_chua_edt);
        roomFloorEdt = findViewById(R.id.edit_tang_edt);
        roomPriceEdt = findViewById(R.id.edit_gia_phong_edt);
    }

    private void setContent() {
        Room room = ListContainers.getInstance().getRooms().get(index);

        roomNameEdt.setText(room.getName());
        roomPriceEdt.setText(Integer.toString(room.getPrice()));
        roomCapacityEdt.setText(Integer.toString(room.getCapacity()));
        roomAreaEdt.setText(Float.toString(room.getSquare()));
        roomFloorEdt.setText("");
    }

    private void initPresenter() {
        presenter = new SuaPhongTroPresenter(this, this);
    }

    @Override
    public void editSuccess() {
        Toast.makeText(this, "Thao tác thành công!", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void editFailure(String mess) {
        Toast.makeText(this, mess, Toast.LENGTH_SHORT).show();
    }

    private void editRoomOnClick() {
        editRoomBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    public void editRoomOnClick(View view) {
        ListContainers.getInstance().getRooms().get(index).setName(roomNameEdt.getText().toString());
        ListContainers.getInstance().getRooms().get(index).setCapacity(Integer.parseInt(roomCapacityEdt.getText().toString()));
        ListContainers.getInstance().getRooms().get(index).setFloor(Integer.parseInt(roomFloorEdt.getText().toString()));
        ListContainers.getInstance().getRooms().get(index).setPrice(Integer.parseInt(roomPriceEdt.getText().toString()));
        ListContainers.getInstance().getRooms().get(index).setSquare(Float.parseFloat(roomAreaEdt.getText().toString()));

        presenter.editRoom(token, index, ListContainers.getInstance().getRooms().get(index));
    }

    public void cancelEditRoomOnCLick(View view) {
        finish();
    }
}
