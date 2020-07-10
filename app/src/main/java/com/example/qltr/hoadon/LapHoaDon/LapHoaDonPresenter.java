package com.example.qltr.hoadon.LapHoaDon;

import android.content.Context;
import android.util.Pair;
import android.widget.Toast;

import com.example.APIHelpers.RetrofitClient;
import com.example.Models.Invoice.CreateInvoiceApiResponse;
import com.example.Models.Invoice.Invoice;
import com.example.Models.Room.Room;
import com.example.Models.Room.RoomApiResponse;
import com.example.Utils.ListContainers;
import com.example.Utils.StatusDesc;
import com.example.qltr.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LapHoaDonPresenter implements LapHoaDonContract.Presenter {
    private Context context;
    private LapHoaDonContract.View view;

    public LapHoaDonPresenter(Context context, LapHoaDonContract.View view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void getRooms(String token, HashMap<String, String> rooms) {
        RetrofitClient.getInstance().getRoomApi().getAllRooms(token).enqueue(new Callback<RoomApiResponse>() {
            @Override
            public void onResponse(Call<RoomApiResponse> call, Response<RoomApiResponse> response) {
                if (response != null && response.body() != null) {
                    if (response.body().isSuccess()) {
                        for (Room room : response.body().getData()) {
                            rooms.put(room.getName(), room.get_id());
                        }
                    } else {

                    }
                }
            }

            @Override
            public void onFailure(Call<RoomApiResponse> call, Throwable t) {
                Toast.makeText(context, "Vui lòng kiểm tra kết nối và thử lại!", Toast.LENGTH_SHORT).show();
                view.finishActivity();
            }
        });
    }

    @Override
    public void loadRooms(String token, HashMap<String, String> rooms) {
        if (ListContainers.getInstance().getRooms() == null || ListContainers.getInstance().getRooms().isEmpty()) {
            getRooms(token, rooms);
        } else {
            for (Room room : ListContainers.getInstance().getRooms()) {
                rooms.put(room.getName(), room.get_id());
            }
        }
    }

    @Override
    public void createInvoice(String token, String roomId, Invoice invoice) {
//        if(!Pattern.matches(context.getString(R.string.money_regex), Integer.toString(invoice.getWaterPrice())) ||
//                !Pattern.matches(context.getString(R.string.money_regex), Integer.toString(invoice.getConsumptionElectric())) ||
//        !Pattern.matches(context.getString(R.string.money_regex), Integer.toString(invoice.getConsumptionWater())) ||
//        !Pattern.matches(context.getString(R.string.money_regex), Integer.toString(invoice.getElectricCost())) ||
//        !Pattern.matches(context.getString(R.string.money_regex), Integer.toString(invoice.getElectricPrice())) ||
//        !Pattern.matches(context.getString(R.string.money_regex), Integer.toString(invoice.getInternetPrice())) ||
//        !Pattern.matches(context.getString(R.string.money_regex), Integer.toString(invoice.getParkingPrice())) ||
//        !Pattern.matches(context.getString(R.string.money_regex), Integer.toString(invoice.getTotalPrice()))){
//            Toast.makeText(context, "Dữ liệu nhập không hợp lệ!", Toast.LENGTH_SHORT).show();
//
//            return;
//        }

        RetrofitClient.getInstance().getInvoiceApi().createInvoice(token, invoice).enqueue(new Callback<CreateInvoiceApiResponse>() {
            @Override
            public void onResponse(Call<CreateInvoiceApiResponse> call, Response<CreateInvoiceApiResponse> response) {
                if(response!=null && response.body()!=null && response.body().isSuccess()){
                    Toast.makeText(context, "Thao tác thành công!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(context, StatusDesc.getInstance().getErrDesc(response.code()), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CreateInvoiceApiResponse> call, Throwable t) {
                Toast.makeText(context, "Vui lòng kiểm tra kết nối và thử lại!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
