package com.example.qltr.phongtro;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.Models.Room;
import com.example.qltr.Adapters.CustomGridViewAdapter;
import com.example.qltr.Adapters.RoomButtonViewAdapter;
import com.example.qltr.R;
import com.example.qltr.login.LoginContract;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PhongTroFragment extends Fragment {
    private static final String TAG = "PhongTroFragment";

    private RecyclerView recyclerView;
    private RoomButtonViewAdapter adapter;
    private ArrayList<Room> rooms;
    private FloatingActionButton addRoomButton;

    public PhongTroFragment(){}

    public PhongTroFragment(ArrayList<Room> rooms){
        this.rooms=rooms;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup containter, @Nullable Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.fragment_phongtro, containter, false);

        recyclerView=view.findViewById(R.id.room_button_view);
        addRoomButton=view.findViewById(R.id.add_room);

        assignViewsEvents();

        rooms = new ArrayList<>();
        rooms.add(new Room(0, 0, 0, 30, 0));
        rooms.add(new Room(1, 0, 0, 30, 0));
        rooms.add(new Room(2, 0, 0, 30, 0));
        rooms.add(new Room(3, 0, 0, 30, 0));
        rooms.add(new Room(3, 0, 0, 30, 0));
        rooms.add(new Room(3, 0, 0, 30, 0));
        rooms.add(new Room(3, 0, 0, 30, 0));
        rooms.add(new Room(3, 0, 0, 30, 0));
        rooms.add(new Room(3, 0, 0, 30, 0));
        rooms.add(new Room(3, 0, 0, 30, 0));
        rooms.add(new Room(3, 0, 0, 30, 0));

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        adapter= new RoomButtonViewAdapter(getContext(), rooms);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
    }

    public void addRoomButtonOnClick(){
        addRoomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Them", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void assignViewsEvents(){
        addRoomButtonOnClick();
    }
}
