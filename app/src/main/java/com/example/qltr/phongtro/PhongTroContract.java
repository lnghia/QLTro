package com.example.qltr.phongtro;

import android.widget.Adapter;

import com.example.Models.Room.Room;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public interface PhongTroContract {
    interface View{
        void assignOnRefreshListenner();
        void raiseNote(String note);
        void configureRecyclerView();
        void assignOnScrollEvent();
        void initViews();
    }

    interface Presenter{
        void loadInitial(final String token, ArrayList<Room> rooms, RecyclerView.Adapter adapter);
        void loadNextPage(final String token, ArrayList<Room> rooms, int pageNum, RecyclerView.Adapter adapter, boolean isLoading);
        void filterEmptyRooms(ArrayList<Room> rooms, ArrayList<Room> displayedRooms, Boolean isFiltered);
        void filterAvailableRooms(ArrayList<Room> rooms, ArrayList<Room> displayedRooms, Boolean isFiltered);
        void filterFullRooms(ArrayList<Room> rooms, ArrayList<Room> displayedRooms, Boolean isFiltered);
    }
}
