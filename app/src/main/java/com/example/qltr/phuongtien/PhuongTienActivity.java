package com.example.qltr.phuongtien;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.APIHelpers.RetrofitClient;
import com.example.Models.Room.Room;
import com.example.Models.Room.RoomDeleteApiResponse;
import com.example.Models.Room.RoomVehicleNumBody;
import com.example.Utils.StatusDesc;
import com.example.qltr.R;

public class PhuongTienActivity extends AppCompatActivity {

    private String token;
    private String id;
    private int vehicleNumb;

    private EditText vehicleNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phuong_tien);

        getToken();
        getId();
        getVehicleNum();

        vehicleNum=findViewById(R.id.vehicle_num_edt);

        vehicleNum.setText(Integer.toString(vehicleNumb));
    }

    private void getToken() {
        token = getIntent().getExtras().getString("token");
    }

    private void getId(){
        id=getIntent().getExtras().getString("Id");
    }

    private void getVehicleNum(){
        vehicleNumb=getIntent().getExtras().getInt("vehicleNum");
    }

    private void edit(){
        RetrofitClient.getInstance().getRoomApi().editVehicleNum(token, id, new RoomVehicleNumBody(Integer.parseInt(vehicleNum.getText().toString()))).enqueue(new Callback<RoomDeleteApiResponse>() {
            @Override
            public void onResponse(Call<RoomDeleteApiResponse> call, Response<RoomDeleteApiResponse> response) {
                if(response!=null && response.body()!=null && response.body().isSuccess()){
                    Toast.makeText(PhuongTienActivity.this, "Cập nhật thành công!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(PhuongTienActivity.this, StatusDesc.getInstance().getErrDesc(response.code()), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RoomDeleteApiResponse> call, Throwable t) {
                Toast.makeText(PhuongTienActivity.this, "Vui lòng kiểm tra kết nối và thử lại!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void editVehicleNum(View view) {
        edit();
    }

    public void cancelEditVehicleNum(View view) {
        finish();
    }
}
