package com.example.qltr.phongtro.ThemPhTr;

import android.content.Context;
import android.util.Log;

import com.example.APIHelpers.RetrofitClient;
import com.example.Models.Room.Room;
import com.example.Models.Room.RoomApiResponse;
import com.example.Models.Room.RoomCreateApiResponse;
import com.example.Utils.ListContainers;
import com.example.Utils.StatusDesc;
import com.example.qltr.R;
import com.example.qltr.phongtro.PhongTroFragment;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThemPhongTroPresenter implements ThemPhongTroContract.Presenter {
    private Context context;
    private ThemPhongTroContract.View view;

    public ThemPhongTroPresenter(Context context, ThemPhongTroContract.View view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void createNewRoom(String token, Room room, int filterType) {
        if (!Pattern.matches(context.getString(R.string.floor_regex), Integer.toString(room.getFloor())) ||
                !Pattern.matches(context.getString(R.string.money_regex), Integer.toString(room.getPrice())) ||
//                !Pattern.matches(context.getString(R.string.area_regex), Float.toString(room.getSquare())) ||
                !Pattern.matches(context.getString(R.string.capacity_regex), Integer.toString(room.getCapacity()))
        ) {
            view.raiseNotification("Dữ liệu nhập không hợp lệ!");

            return;
        }

        RetrofitClient.getInstance().getRoomApi().createRoom(token, room).enqueue(new Callback<RoomCreateApiResponse>() {
            @Override
            public void onResponse(Call<RoomCreateApiResponse> call, Response<RoomCreateApiResponse> response) {
                if (response.body() != null) {
                    if (response.body().isSuccess()) {
                        view.createSuccess(StatusDesc.getInstance().getErrDesc(response.code()));
                        if (filterType == PhongTroFragment.BY_ALL || filterType == PhongTroFragment.BY_EMPTY && ListContainers.getInstance().getRooms().size() % 10 != 0) {
                            ListContainers.getInstance().getRooms().add(response.body().getData());
                        }
                    } else {
                        view.raiseNotification(StatusDesc.getInstance().getErrDesc(response.code()));
                    }
                }
            }

            @Override
            public void onFailure(Call<RoomCreateApiResponse> call, Throwable t) {
                Log.i("t", t.getMessage());
                view.raiseNotification("Vui lòng kiểm tra kết nối và thử lại!");
            }
        });
    }
}
