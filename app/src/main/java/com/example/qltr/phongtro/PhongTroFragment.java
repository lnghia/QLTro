package com.example.qltr.phongtro;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.APIHelpers.RetrofitClient;
import com.example.Models.Room.Room;
import com.example.Models.Room.RoomApiResponse;
import com.example.qltr.Adapters.RoomButtonViewAdapter;
import com.example.qltr.R;
import com.example.qltr.phongtro.PhongTroContract.Presenter;
import com.example.qltr.phongtro.ThemPhTr.ThemPhongTroActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.TreeSet;
import java.util.stream.Collectors;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhongTroFragment extends Fragment implements PhongTroContract.View {
    private static final String TAG = "PhongTroFragment";
    private final int PAGE_SIZE = 10;

    private RecyclerView recyclerView;
    private Spinner spinner;
    private RoomButtonViewAdapter adapter;
    private ArrayList<Room> rooms;
    private ArrayList<Room> displayedRooms;
    private TreeSet<Integer> floors;
    private FloatingActionButton addRoomButton;
    private SwipeRefreshLayout roomPullToRefreshLayout;
    private String token;
    private boolean isLoading;
    private int pageNum;
    private GridLayoutManager layoutManager;
    private int selectedItem;

    private Button emptyRoomFilter;
    private Button availableRoomFilter;
    private Button fullRoomFilter;
    private Boolean isFiltered;

    private PhongTroPresenter presenter;

    public PhongTroFragment() {
    }

    public PhongTroFragment(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup containter, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_phongtro, containter, false);

        pageNum = 1;
        isFiltered=false;

        assignViews(view);
        initViews();

        assignViewsEvents();
        getToken();

        rooms = new ArrayList<>();
        displayedRooms=new ArrayList<>();
        displayedRooms.add(null);
//        rooms.add(null);
        loadInitial();

        assignOnRefreshListenner();
        assignOnScrollEvent();
        configureRecyclerView();

        floors = new TreeSet<>();
//        getFloors();

        presenter = new PhongTroPresenter(getContext(), this);

        availableRoomsFilterOnClick();
        emptyRoomsFilterOnClick();
        fullRoomsFilterOnClick();

        return view;
    }

    private void getFloors(){
        for(Room room : rooms){
            floors.add(room.getFloor());
        }
    }

    private void assignViews(View view) {
        recyclerView = view.findViewById(R.id.room_button_view);
        addRoomButton = view.findViewById(R.id.add_room);
        roomPullToRefreshLayout = view.findViewById(R.id.room_dashboard_refresh_layout);
        emptyRoomFilter = view.findViewById(R.id.empty_rooms_btn);
        availableRoomFilter = view.findViewById(R.id.not_full_rooms_btn);
        fullRoomFilter = view.findViewById(R.id.full_rooms_btn);
        spinner=view.findViewById(R.id.floor_spinner);
    }

    private void configSpinner(){
        ArrayAdapter<Integer> arrayAdapter=new ArrayAdapter<Integer>(getContext(), android.R.layout.simple_spinner_item, new ArrayList<>(floors));

        spinner.setAdapter(arrayAdapter);
        arrayAdapter.notifyDataSetChanged();
    }

    @Override
    public void initViews() {
        fullRoomFilter.setBackgroundResource(R.drawable.orange_rounded_button);
        availableRoomFilter.setBackgroundResource(R.drawable.yellow_rounded_button);
        emptyRoomFilter.setBackgroundResource(R.drawable.rounded_button);
    }

    private void getToken() {
        token = getActivity().getSharedPreferences("com.example.qltr.login", Context.MODE_PRIVATE).getString("token", null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        adapter = new RoomButtonViewAdapter(getContext(), displayedRooms);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
    }

    private void addRoomButtonOnClick() {
        addRoomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ThemPhongTroActivity.class);
                intent.putExtra("token", token);

                startActivity(intent);
            }
        });
    }

    public void assignViewsEvents() {
        addRoomButtonOnClick();
    }

    @Override
    public void assignOnRefreshListenner() {
        roomPullToRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (!isLoading) {
                    isLoading = true;
                    loadInitial();
                }
            }
        });
    }

    @Override
    public void raiseNote(String note) {
        Toast.makeText(getActivity(), note, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void configureRecyclerView() {
        layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setHasFixedSize(false);

        adapter = new RoomButtonViewAdapter(getContext(), displayedRooms);
        recyclerView.setAdapter(adapter);

        assignOnScrollEvent();
    }

    @Override
    public void assignOnScrollEvent() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (!isLoading && recyclerView.getLayoutManager() != null && ((GridLayoutManager) recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition() == rooms.size() - 1) {
                    isLoading = true;
                    loadNextPage(++pageNum);
                }
            }
        });
    }

    public void loadInitial() {
        isLoading = true;
//        recyclerView.removeAllViewsInLayout();
        int prevSize = rooms.size();

//        recyclerView.removeAllViewsInLayout();

        RetrofitClient.getInstance().getRoomApi().getRooms(token, 1, PAGE_SIZE).enqueue(new Callback<RoomApiResponse>() {
            @Override
            public void onResponse(Call<RoomApiResponse> call, Response<RoomApiResponse> response) {
                if (response.body() != null || response.body().isSuccess()) {
                    rooms.clear();
                    displayedRooms.clear();
                    adapter.notifyDataSetChanged();
//                    adapter.notifyItemRangeRemoved(0, prevSize);
//                    adapter.notifyItemRemoved(rooms.size());
                    for (Room room : response.body().getData()) {
                        rooms.add(room);
                        displayedRooms.add(room);
                    }
                    adapter.notifyDataSetChanged();
                }
                roomPullToRefreshLayout.setRefreshing(false);
                isLoading = false;
                pageNum = 1;
            }

            @Override
            public void onFailure(Call<RoomApiResponse> call, Throwable t) {
                raiseNote("Vui lòng kiểm tra kết nối và thử lại!");
                roomPullToRefreshLayout.setRefreshing(false);
                isLoading = false;
            }
        });
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//
//        if(rooms.isEmpty()){
//            loadInitial();
//            getFloors();
//            configSpinner();
//        }
//
//        adapter.notifyDataSetChanged();
//    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        getActivity().getMenuInflater().inflate(R.menu.facility_long_click_menu, menu);

        selectedItem = v.getId();
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                break;
            case 2:
                break;
        }

        return super.onContextItemSelected(item);
    }

    public void loadNextPage(int page) {
//        rooms.add(null);
        displayedRooms.add(null);
        adapter.notifyItemInserted(displayedRooms.size() - 1);

        RetrofitClient.getInstance().getRoomApi().getRooms(token, page, PAGE_SIZE).enqueue(new Callback<RoomApiResponse>() {
            @Override
            public void onResponse(Call<RoomApiResponse> call, Response<RoomApiResponse> response) {
                if (response.body() != null && response.body().isSuccess()) {
                    if (!response.body().getData().isEmpty()) {
//                        rooms.remove(rooms.size() - 1);
                        displayedRooms.remove(displayedRooms.size()-1);
                        adapter.notifyItemRemoved(displayedRooms.size());
                        for (Room room : response.body().getData()) {
                            rooms.add(room);
                            displayedRooms.add(room);
                        }
                        adapter.notifyDataSetChanged();
                    } else {
                        displayedRooms.remove(displayedRooms.size() - 1);
                        adapter.notifyItemRemoved(displayedRooms.size());
                    }
                } else {
                    displayedRooms.remove(displayedRooms.size() - 1);
                    adapter.notifyItemRemoved(displayedRooms.size());
                }

                isLoading = false;
            }

            @Override
            public void onFailure(Call<RoomApiResponse> call, Throwable t) {
                raiseNote("Vui lòng kiểm tra kết nối và thử lại!");
                isLoading = false;
            }
        });
    }

    private void emptyRoomsFilterOnClick(){
        emptyRoomFilter.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                presenter.filterEmptyRooms(rooms, displayedRooms, isFiltered);
                isFiltered=!isFiltered;
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void availableRoomsFilterOnClick(){
        availableRoomFilter.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                presenter.filterAvailableRooms(rooms, displayedRooms, isFiltered);
                isFiltered=!isFiltered;
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void fullRoomsFilterOnClick(){
        fullRoomFilter.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                presenter.filterFullRooms(rooms, displayedRooms, isFiltered);
                isFiltered=!isFiltered;
                adapter.notifyDataSetChanged();
            }
        });
    }
}
