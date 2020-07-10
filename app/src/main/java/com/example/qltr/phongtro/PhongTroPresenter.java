package com.example.qltr.phongtro;

import android.content.Context;
import android.os.Build;

import com.example.APIHelpers.RetrofitClient;
import com.example.Models.Room.Room;
import com.example.Models.Room.RoomApiResponse;
import com.example.Models.Room.RoomDeleteApiResponse;
import com.example.Models.Room.RoomTypeBody;
import com.example.Utils.ListContainers;
import com.example.Utils.StatusDesc;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhongTroPresenter implements PhongTroContract.Presenter {
    private Context context;
    private PhongTroContract.View view;
    private final int pageSize = 10;

    public PhongTroPresenter(Context context, PhongTroContract.View view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void loadInitial(String token, ArrayList<Room> rooms, int pageNum, int limit, RoomTypeBody filterType) {
        RetrofitClient.getInstance().getRoomApi().getRooms(token, pageNum, limit, filterType).enqueue(new Callback<RoomApiResponse>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<RoomApiResponse> call, Response<RoomApiResponse> response) {
                if(!rooms.isEmpty()){
                    rooms.clear();
                    view.updateDataSetChanged();
                }

                if(response!=null && response.body()!=null){
                    if(response.body().isSuccess()){
//                        response.body().getData().stream().forEach(rooms::add);
                        for(Room room : response.body().getData()){
                            rooms.add(room);
                        }
                        view.updateDataSetChanged();
                    }
                    else {
                        view.raiseNote(StatusDesc.getInstance().getErrDesc(response.code()));
                    }
                }

                view.setRefresh(false);
                view.setIsLoading(false);
            }

            @Override
            public void onFailure(Call<RoomApiResponse> call, Throwable t) {
                if(!rooms.isEmpty()){
                    rooms.clear();
                    view.updateDataSetChanged();
                }
                view.raiseNote("Vui lòng kiểm tra kết nối và thử lại!");
                view.setRefresh(false);
                view.setIsLoading(false);
            }
        });
    }

    @Override
    public void loadNextPage(String token, ArrayList<Room> rooms, int pageNum, int limit, RoomTypeBody filterType) {
        rooms.add(null);
        view.updateDataSetChanged();

        RetrofitClient.getInstance().getRoomApi().getRooms(token, pageNum, limit, filterType).enqueue(new Callback<RoomApiResponse>() {
//            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<RoomApiResponse> call, Response<RoomApiResponse> response) {
                rooms.remove(rooms.size()-1);
                view.updateDataSetChanged();

                if(response!=null && response.body()!=null){
                    if(response.body().isSuccess()){
//                        response.body().getData().stream().forEach(rooms::add);
                        for(Room room : response.body().getData()){
                            rooms.add(room);
                        }
                        view.updateDataSetChanged();
                    }
                    else{
                        view.raiseNote(StatusDesc.getInstance().getErrDesc(response.code()));
                    }
                }

                view.setIsLoading(false);
            }

            @Override
            public void onFailure(Call<RoomApiResponse> call, Throwable t) {
                rooms.remove(rooms.size()-1);
                view.updateDataSetChanged();
                view.raiseNote("Vui lòng kiểm tra kết nối và thử lại!");
                view.setIsLoading(false);
            }
        });
    }

    @Override
    public void loadInitial(String token, ArrayList<Room> rooms, int pageNum, int limit) {
        RetrofitClient.getInstance().getRoomApi().getAllRooms(token, pageNum, limit).enqueue(new Callback<RoomApiResponse>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<RoomApiResponse> call, Response<RoomApiResponse> response) {
                if(!rooms.isEmpty()){
                    rooms.clear();
                    view.updateDataSetChanged();
                }

                if(response!=null && response.body()!=null){
                    if(response.body().isSuccess()){
//                        response.body().getData().stream().forEach(rooms::add);
                        for(Room room : response.body().getData()){
                            rooms.add(room);
                        }
                        view.updateDataSetChanged();
                    }
                    else{
                        view.raiseNote(StatusDesc.getInstance().getErrDesc(response.code()));
                    }
                }

                view.setRefresh(false);
                view.setIsLoading(false);
            }

            @Override
            public void onFailure(Call<RoomApiResponse> call, Throwable t) {
                if(!rooms.isEmpty()){
                    rooms.clear();
                    view.updateDataSetChanged();
                }
                view.raiseNote("Vui lòng kiểm tra kết nối và thử lại!");
                view.setRefresh(false);
                view.setIsLoading(false);
            }
        });
    }

    @Override
    public void loadNextPage(String token, ArrayList<Room> rooms, int pageNum, int limit) {
        rooms.add(null);
        view.updateDataSetChanged();

        RetrofitClient.getInstance().getRoomApi().getAllRooms(token, pageNum, limit).enqueue(new Callback<RoomApiResponse>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<RoomApiResponse> call, Response<RoomApiResponse> response) {
                rooms.remove(rooms.size()-1);
                view.updateDataSetChanged();

                if(response!=null && response.body()!=null){
                    if(response.body().isSuccess()){
//                        response.body().getData().stream().forEach(rooms::add);
                        for(Room room : response.body().getData()){
                            rooms.add(room);
                        }
                        view.updateDataSetChanged();
                    }
                    else{
                        view.raiseNote(StatusDesc.getInstance().getErrDesc(response.code()));
                    }
                }

                view.setIsLoading(false);
            }

            @Override
            public void onFailure(Call<RoomApiResponse> call, Throwable t) {
                rooms.remove(rooms.size()-1);
                view.updateDataSetChanged();
                view.raiseNote("Vui lòng kiểm tra kết nối và thử lại!");
                view.setIsLoading(false);
            }
        });
    }

    @Override
    public void deleteRoom(String token, ArrayList<Room> rooms, int index) {
        RetrofitClient.getInstance().getRoomApi().deleteRoom(token, rooms.get(index).get_id()).enqueue(new Callback<RoomDeleteApiResponse>() {
//            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<RoomDeleteApiResponse> call, Response<RoomDeleteApiResponse> response) {
                if(response!=null && response.body()!=null){
                    if(response.body().isSuccess()){
                        rooms.remove(index);
//                        rooms.removeIf(r -> r.get_id().equals(rooms.get(index).get_id()));
                        view.updateDataSetChanged();
                        view.raiseNote("Thao tác thành công!");
                    }
                    else {
                        view.raiseNote(StatusDesc.getInstance().getErrDesc(response.code()));
                    }
                }
            }

            @Override
            public void onFailure(Call<RoomDeleteApiResponse> call, Throwable t) {
                view.raiseNote("Vui lòng kiểm tra kết nối và thử lại!");
            }
        });
    }

    @Override
    public void editRoom(String token, ArrayList<Room> rooms, Room room, int index) {
        RetrofitClient.getInstance().getRoomApi().editRoom(token,rooms.get(index).get_id(), room).enqueue(new Callback<RoomDeleteApiResponse>() {
            @Override
            public void onResponse(Call<RoomDeleteApiResponse> call, Response<RoomDeleteApiResponse> response) {

            }

            @Override
            public void onFailure(Call<RoomDeleteApiResponse> call, Throwable t) {

            }
        });
    }


////    @RequiresApi(api = Build.VERSION_CODES.N)
//    @Override
//    public void filterEmptyRooms(ArrayList<Room> rooms, ArrayList<Room> rooms, Boolean isFiltered) {
//        rooms.clear();
//
//        if(!isFiltered){
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                rooms.stream().filter(x -> x.getSlotStatus().equals("empty")).collect(Collectors.toList()).forEach(rooms::add);
//            }
//        }
//        else{
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                rooms.stream().collect(Collectors.toList()).forEach(rooms::add);
//            }
//        }
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.N)
//    @Override
//    public void filterAvailableRooms(ArrayList<Room> rooms, ArrayList<Room> rooms, Boolean isFiltered) {
//        rooms.clear();
//
//        if(!isFiltered){
//            rooms.stream().filter(x -> x.getSlotStatus().equals("available")).collect(Collectors.toList()).forEach(rooms::add);
//        }
//        else{
//            rooms.stream().collect(Collectors.toList()).forEach(rooms::add);
//        }
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.N)
//    @Override
//    public void filterFullRooms(ArrayList<Room> rooms, ArrayList<Room> rooms, Boolean isFiltered) {
//        rooms.clear();
//
//        if(!isFiltered){
//            rooms.stream().filter(x -> x.getSlotStatus().equals("full")).collect(Collectors.toList()).forEach(rooms::add);
//        }
//        else{
//            rooms.stream().collect(Collectors.toList()).forEach(rooms::add);
//        }
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.N)
//    @Override
//    public void filterAllRooms(ArrayList<Room> rooms, ArrayList<Room> rooms) {
//        rooms.clear();
//
//        rooms.stream().collect(Collectors.toList()).forEach(rooms::add);
//    }
}
