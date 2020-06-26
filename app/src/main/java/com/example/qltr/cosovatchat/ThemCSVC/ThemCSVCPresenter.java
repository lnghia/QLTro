package com.example.qltr.cosovatchat.ThemCSVC;

import android.content.Context;
import android.util.Log;

import com.example.APIHelpers.RetrofitClient;
import com.example.Models.Facility.Facility;
import com.example.Models.Facility.FacilityApiResponse;
import com.example.qltr.R;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThemCSVCPresenter implements ThemCSVCContract.Presenter {
    private Context context;
    private ThemCSVCContract.ThemCSVCView view;
    private String token;

    public ThemCSVCPresenter(Context context, ThemCSVCContract.ThemCSVCView view){
        this.context=context;
        this.view=view;
        token=context.getSharedPreferences("com.example.qltr.login", Context.MODE_PRIVATE).getString("token", null);
    }

    @Override
    public void handleAddFacility(Facility newFacility) {
        if (!validateUnitPriceInput(Integer.toString(newFacility.getUnitPrice())) || !validateQuantityInput(Integer.toString(newFacility.getQuantity()))){
            view.addFail("Thông tin nhập không hợp lệ !");

            return;
        }
        Log.i("token:", token);
        RetrofitClient.getInstance().getFacilityApi().createNewFacility(token, newFacility).enqueue(new Callback<FacilityApiResponse>() {
            @Override
            public void onResponse(Call<FacilityApiResponse> call, Response<FacilityApiResponse> response) {
//                Log.i("body", new Gson().toJson(newFacility));
//                Log.i("response", new Gson().toJson(response.body()));

                if(response.body()!=null){
                    if(response.body().getSuccess()){
                        view.addSuccess(newFacility);
                    }
                    else{
                        view.addFail("Đã tồn tại trong hệ thống!");
                    }
                }
                else{
                    view.addFail("Đã tồn tại trong hệ thống!");
                }
            }

            @Override
            public void onFailure(Call<FacilityApiResponse> call, Throwable t) {
//                Log.i("t", t.getMessage());
                view.addFail("Lỗi thực thi, vui lòng kiểm tra kết nối mạng !");
            }
        });
    }

    @Override
    public Boolean validateUnitPriceInput(String inp) {
        return Pattern.matches(context.getResources().getString(R.string.facility_unit_price_regex), inp);
    }

    @Override
    public Boolean validateQuantityInput(String inp) {
        return Pattern.matches(context.getResources().getString(R.string.facility_quantity_regex), inp);
    }
}
