package com.example.qltr.phongtro;

import android.widget.Adapter;

import com.example.Models.Room.Room;
import com.example.Models.Room.RoomTypeBody;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public interface PhongTroContract {
    interface View{
        void assignOnRefreshListenner();
        void raiseNote(String note);
        void configureRecyclerView();
        void assignOnScrollEvent();
        void initViews();
        void updateDataSetChanged();
        void setIsLoading(boolean val);
        void setRefresh(boolean value);
    }

    interface Presenter{
        void loadInitial(final String token, ArrayList<Room> rooms, int pageNum, int limit, RoomTypeBody filterType);
        void loadNextPage(final String token, ArrayList<Room> rooms, int pageNum, int limit, RoomTypeBody filterType);
        void loadInitial(final String token, ArrayList<Room> rooms, int pageNum, int limit);
        void loadNextPage(final String token, ArrayList<Room> rooms, int pageNum, int limit);
        void deleteRoom(final String token, ArrayList<Room> rooms, int index);
        void editRoom(final String token, ArrayList<Room> rooms, Room room, int index);
//        void filterEmptyRooms(ArrayList<Room> rooms, ArrayList<Room> displayedRooms, Boolean isFiltered);
//        void filterAvailableRooms(ArrayList<Room> rooms, ArrayList<Room> displayedRooms, Boolean isFiltered);
//        void filterFullRooms(ArrayList<Room> rooms, ArrayList<Room> displayedRooms, Boolean isFiltered);
//        void filterAllRooms(ArrayList<Room> rooms, ArrayList<Room> displayedRooms);
//        void getAll(String token, ArrayList<Room> rooms, int page, int limit);
    }
}
