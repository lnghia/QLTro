package com.example.qltr.phongtro;

import android.content.Context;
import android.os.Build;

import com.example.APIHelpers.RetrofitClient;
import com.example.Models.Room.Room;
import com.example.Models.Room.RoomApiResponse;
import com.example.Utils.ListContainers;

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
    public void loadInitial(String token, ArrayList<Room> rooms, RecyclerView.Adapter adapter) {

    }

    @Override
    public void loadNextPage(String token, ArrayList<Room> rooms, int pageNum, RecyclerView.Adapter adapter, boolean isLoading) {

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void filterEmptyRooms(ArrayList<Room> rooms, ArrayList<Room> displayedRooms, Boolean isFiltered) {
        displayedRooms.clear();

        if(!isFiltered){
            rooms.stream().filter(x -> x.getSlotStatus().equals("empty")).collect(Collectors.toList()).forEach(displayedRooms::add);
        }
        else{
            rooms.stream().collect(Collectors.toList()).forEach(displayedRooms::add);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void filterAvailableRooms(ArrayList<Room> rooms, ArrayList<Room> displayedRooms, Boolean isFiltered) {
        displayedRooms.clear();

        if(!isFiltered){
            rooms.stream().filter(x -> x.getSlotStatus().equals("available")).collect(Collectors.toList()).forEach(displayedRooms::add);
        }
        else{
            rooms.stream().collect(Collectors.toList()).forEach(displayedRooms::add);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void filterFullRooms(ArrayList<Room> rooms, ArrayList<Room> displayedRooms, Boolean isFiltered) {
        displayedRooms.clear();

        if(!isFiltered){
            rooms.stream().filter(x -> x.getSlotStatus().equals("full")).collect(Collectors.toList()).forEach(displayedRooms::add);
        }
        else{
            rooms.stream().collect(Collectors.toList()).forEach(displayedRooms::add);
        }
    }

}
