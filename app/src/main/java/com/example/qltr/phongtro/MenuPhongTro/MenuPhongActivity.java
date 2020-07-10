package com.example.qltr.phongtro.MenuPhongTro;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.APIHelpers.RetrofitClient;
import com.example.Models.Contract.CreateContractApiResponse;
import com.example.Utils.StatusDesc;
import com.example.qltr.Adapters.CustomGridViewAdapter;
import com.example.qltr.R;
import com.example.qltr.cosovatchat.QLCSVC.QLCSVCActivity;
import com.example.qltr.hoadon.LapHoaDon.LapHoaDonActivity;
import com.example.qltr.hopdong.GiaHanHopDong.ContractExtensionActivity;
import com.example.qltr.khachtro.DashBoard.DashboardKhachTroActivity;
import com.example.qltr.phuongtien.PhuongTienActivity;

import java.util.ArrayList;

public class MenuPhongActivity extends AppCompatActivity {

    private ArrayList<Pair<String, Integer>> buttonsInfo;
    private GridView buttonGridView;
    private CustomGridViewAdapter gAdapter;
    private Intent intent;
    private TextView roomNameTxt;
    private TextView areaTxt;
    private TextView vehicleNumTxt;
    private TextView customerNumTxt;
    private String roomId;
    private String token;
    private int floor;
    private String slotStatus;
    private float area;
    private int vehicleNum;
    private String roomName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phong);

        buttonGridView=findViewById(R.id.menu_phong_grid_view);
        assignViews();
        initButtonView();

        getConstant();
        setContent();
    }

    private void getToken(){
        token=getIntent().getExtras().getString("token");
    }

    private void getFloor(){ floor=getIntent().getExtras().getInt("floor"); }

    private void getRoomId(){
        roomId=getIntent().getExtras().getString("roomId");
    }

    private void getArea(){
        area=getIntent().getExtras().getFloat("area");
    }

    private void getVehicleNum(){
        vehicleNum=getIntent().getExtras().getInt("vehicleNum");
    }

    private void getSlotStatus(){
        slotStatus=getIntent().getExtras().getString("slotStatus");
    }

    private void getRoomName(){
        roomName=getIntent().getExtras().getString("roomName");
    }

    private void getConstant(){
        getRoomId();
        getToken();
        getFloor();
        getArea();
        getVehicleNum();
        getSlotStatus();
        getRoomName();
    }

    private void assignViews(){
        roomNameTxt=findViewById(R.id.room_name);
        areaTxt=findViewById(R.id.room_area_txt);
        vehicleNumTxt=findViewById(R.id.vehivle_number_txt);
        customerNumTxt=findViewById(R.id.customer_number_txt);
    }

    private void setContent(){
        Intent intent=getIntent();

        roomNameTxt.setText(intent.getExtras().getString("roomName"));
        areaTxt.setText(Float.toString(area));
        vehicleNumTxt.setText(Integer.toString(vehicleNum));

    }

    private void requireDeleteConfirmation(){
        AlertDialog.Builder alertDialog= new AlertDialog.Builder(this);

        alertDialog.setTitle("Thông báo!");
        alertDialog.setIcon(R.mipmap.ic_launcher);
        alertDialog.setMessage("Bạn có muốn thanh lý hợp đồng này không ?");

        alertDialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                RetrofitClient.getInstance().getContractApi().deleteContract(token, roomId).enqueue(new Callback<CreateContractApiResponse>() {
                    @Override
                    public void onResponse(Call<CreateContractApiResponse> call, Response<CreateContractApiResponse> response) {
                        if(response!=null && response.body()!=null && response.body().isSuccess()){
                            Toast.makeText(MenuPhongActivity.this, "Thao tác thành công!", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(MenuPhongActivity.this, StatusDesc.getInstance().getErrDesc(response.code()), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<CreateContractApiResponse> call, Throwable t) {
                        Toast.makeText(MenuPhongActivity.this, "Vui lòng kiểm tra kết nối và thử lại!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        alertDialog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        alertDialog.show();
    }

    public void initButtonView(){
        buttonsInfo=new ArrayList<Pair<String, Integer>>(){
            {
                add(new Pair<String, Integer>("Khách Trọ", R.drawable.miscellaneous));
                add(new Pair<String, Integer>("Lập Hóa Đơn", R.drawable.business));
                add(new Pair<String, Integer>("Phương Tiện", R.drawable.vehicle));
                add(new Pair<String, Integer>("Thanh Lý Hợp Đồng", R.drawable.finishcontract));
                add(new Pair<String, Integer>("Gia Hạn Hợp Đồng", R.drawable.signature));
                add(new Pair<String, Integer>("Cơ sở vật chất", R.drawable.sink));
            }
        };

        gAdapter=new CustomGridViewAdapter(buttonsInfo, this);
        buttonGridView.setAdapter(gAdapter);

        buttonGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(MenuPhongActivity.this, Integer.toString(i), Toast.LENGTH_SHORT).show();

                switch (i){
                    case 0:
                        intent=new Intent(MenuPhongActivity.this, DashboardKhachTroActivity.class);
                        intent.putExtra("roomId", roomId);
                        intent.putExtra("token", token);
                        intent.putExtra("slotStatus", slotStatus);
                        intent.putExtra("roomIndex", i);
                        intent.putExtra("floor", floor);

                        startActivity(intent);

                        break;
                    case 1:
                        intent=new Intent(MenuPhongActivity.this, LapHoaDonActivity.class);
                        intent.putExtra("roomId", roomId);
                        intent.putExtra("token", token);
                        intent.putExtra("roomName", roomName);

                        startActivity(intent);

                        break;
                    case 2:
                        intent=new Intent(MenuPhongActivity.this, PhuongTienActivity.class);
                        intent.putExtra("token", token);
                        intent.putExtra("Id", roomId);
                        intent.putExtra("vehicleNum", vehicleNum);

                        startActivity(intent);
                        break;
                    case 3:
                        requireDeleteConfirmation();
                        break;
                    case 4:
                        intent=new Intent(MenuPhongActivity.this, ContractExtensionActivity.class);
                        intent.putExtra("roomId", roomId);
                        intent.putExtra("token", token);

                        startActivity(intent);
                        break;
                }
            }
        });
    }
}
