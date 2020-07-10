package com.example.qltr.phongtro.SuaPhongTro;

import android.content.Context;
import android.widget.Toast;

import com.example.APIHelpers.RetrofitClient;
import com.example.Models.Room.Room;
import com.example.Models.Room.RoomDeleteApiResponse;
import com.example.Utils.ListContainers;
import com.example.Utils.StatusDesc;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SuaPhongTroPresenter implements SuaPhongTroContract.Presenter {
    private Context context;
    private SuaPhongTroContract.View view;

    public SuaPhongTroPresenter(Context context, SuaPhongTroContract.View view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void editRoom(String token, int id, Room room) {
        RetrofitClient.getInstance().getRoomApi().editRoom(token, ListContainers.getInstance().getRooms().get(id).get_id(), room).enqueue(new Callback<RoomDeleteApiResponse>() {
            @Override
            public void onResponse(Call<RoomDeleteApiResponse> call, Response<RoomDeleteApiResponse> response) {
                if(response!=null && response.body()!=null){
                    if (response.body().isSuccess()){
                        ListContainers.getInstance().getRooms().set(id, room);
                        view.editSuccess();
                    }
                    else{
                        view.editFailure(StatusDesc.getInstance().getErrDesc(response.code()));
                    }
                }
            }

            @Override
            public void onFailure(Call<RoomDeleteApiResponse> call, Throwable t) {
                view.editFailure("Vui lòng kiểm tra kết nối và thử lại!");
            }
        });
    }
}
