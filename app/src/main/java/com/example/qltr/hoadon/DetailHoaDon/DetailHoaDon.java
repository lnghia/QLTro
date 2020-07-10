package com.example.qltr.hoadon.DetailHoaDon;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.example.APIHelpers.RetrofitClient;
import com.example.Models.Invoice.CreateInvoiceApiResponse;
import com.example.Utils.StatusDesc;
import com.example.qltr.R;

public class DetailHoaDon extends AppCompatActivity {

    private String token;
    private String id;

    private EditText soDien;
    private EditText soNuoc;
    private EditText tienDien;
    private EditText tienNuoc;
    private EditText tienPhong;
    private EditText tienMang;
    private EditText tienXe;
    private EditText tongTien;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_hoa_don);

        getToken();
        getId();

        assignViews();

        getDetail();
    }

    private void assignViews(){
        soDien=findViewById(R.id.detail_so_dien_edt);
        soNuoc=findViewById(R.id.detail_gia_nuoc_edt);
        tienDien=findViewById(R.id.detail_gia_dien_edt);
        tienNuoc=findViewById(R.id.detail_gia_nuoc_edt);
        tienPhong=findViewById(R.id.detail_gia_phong_edt);
        tienMang=findViewById(R.id.detail_internet_edt);
        tongTien=findViewById(R.id.detail_tong_edt);
    }

    private void getToken(){
        token=getIntent().getExtras().getString("token");
    }

    private void getId(){
        id=getIntent().getExtras().getString("Id");
    }

    private void getDetail(){
        RetrofitClient.getInstance().getInvoiceApi().getDetail(token, id).enqueue(new Callback<CreateInvoiceApiResponse>() {
            @Override
            public void onResponse(Call<CreateInvoiceApiResponse> call, Response<CreateInvoiceApiResponse> response) {
                if(response!=null && response.body()!=null && response.body().isSuccess()){
                    soDien.setText(Integer.toString(response.body().getData().getConsumptionElectric()));
                    soNuoc.setText(Integer.toString(response.body().getData().getConsumptionWater()));
                    tienDien.setText(Integer.toString(response.body().getData().getElectricPrice()));
                    tienNuoc.setText(Integer.toString(response.body().getData().getWaterPrice()));
                    tienPhong.setText(Integer.toString(response.body().getData().getCleanPrice()));
                    tienMang.setText(Integer.toString(response.body().getData().getInternetPrice()));
                    tongTien.setText(Integer.toString(response.body().getData().getTotalPrice()));
                }
                else{
                    Toast.makeText(DetailHoaDon.this, StatusDesc.getInstance().getErrDesc(response.code()), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CreateInvoiceApiResponse> call, Throwable t) {
                Toast.makeText(DetailHoaDon.this, "Vui lòng kiểm tra kết nối và thử lại!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
