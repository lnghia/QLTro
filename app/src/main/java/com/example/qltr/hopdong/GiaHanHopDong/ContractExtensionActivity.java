package com.example.qltr.hopdong.GiaHanHopDong;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.APIHelpers.RetrofitClient;
import com.example.Models.Contract.Contract;
import com.example.Models.Contract.ContractExtensionBody;
import com.example.Models.Contract.CreateContractApiResponse;
import com.example.Utils.DatePicker.DatePickerFragment;
import com.example.Utils.StatusDesc;
import com.example.qltr.R;

public class ContractExtensionActivity extends AppCompatActivity {
    private EditText datePicker;
    private String token;
    private String roomId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contract_extension);

        datePicker=findViewById(R.id.duedate_picker);

        token=getIntent().getExtras().getString("token");
        roomId=getIntent().getExtras().getString("roomId");
    }

    public void extend(View view) {
        RetrofitClient.getInstance().getContractApi().extendContract(token, roomId, new ContractExtensionBody(datePicker.getText().toString())).enqueue(new Callback<CreateContractApiResponse>() {
            @Override
            public void onResponse(Call<CreateContractApiResponse> call, Response<CreateContractApiResponse> response) {
                if(response!=null && response.body()!=null && response.body().isSuccess()){
                    Toast.makeText(ContractExtensionActivity.this, "Thao tác thành công!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(ContractExtensionActivity.this, StatusDesc.getInstance().getErrDesc(response.code()), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CreateContractApiResponse> call, Throwable t) {
                Toast.makeText(ContractExtensionActivity.this, "Vui lòng kiểm tra kết nối và thử lại!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void cancel(View view) {
        finish();
    }

    public void pickDate(View view) {
        DialogFragment dtPicker=new DatePickerFragment(datePicker);

        dtPicker.show(getSupportFragmentManager(), "datePicker");
    }
}
