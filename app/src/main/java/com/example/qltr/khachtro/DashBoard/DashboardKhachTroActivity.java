package com.example.qltr.khachtro.DashBoard;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.Models.Customer.Customer;
import com.example.Utils.ListContainers;
import com.example.qltr.Adapters.PagingCustomerAdapter;
import com.example.qltr.R;
import com.example.qltr.hopdong.LapHopdong.CreateContractActivity;
import com.example.qltr.khachtro.KhachTroDetail.DetailKhachTroActivity;
import com.example.qltr.khachtro.SuaKhachTro.SuaKhachTroActivity;
import com.example.qltr.khachtro.SuaKhachTro.SuaKhachTroPresenter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class DashboardKhachTroActivity extends AppCompatActivity implements DashboardKhachTroContract.View {
    private DashboardKhachTroContract.Presenter presenter;


    private String token;
    private String roomId;
    private int floor;

    private FloatingActionButton addCustomerBtn;
    private SwipeRefreshLayout customerRefreshLayout;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private LinearLayoutManager layoutManager;
    private boolean isLoading;
    private ArrayList<Customer> customers;
//    private ArrayList<Customer> displayedCustomer;

    Color color = new Color();

    private int pageNum;
    private int selectedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_khach_tro);

//        recyclerView=new RecyclerView(this);
        assignViews();

        initPresenter();
        getToken();
        getRoomId();
        getFloor();

        isLoading = false;
        pageNum = 1;
        customers = new ArrayList<>();
        customers.add(null);

        configRecyclerView();

        assignOnRefreshListener();

        addCustomerOnClick();
    }

    private void initPresenter() {
        presenter = new DashboardKhachTroPresenter(this, this);
    }

    private void getToken() {
        token = getIntent().getExtras().getString("token");
    }

    private void getFloor() {
        floor = getIntent().getExtras().getInt("floor");
    }

    private void assignViews() {
        addCustomerBtn = findViewById(R.id.add_customer_btn);
        customerRefreshLayout = findViewById(R.id.customer_dashboard_refresh_layout);
        recyclerView = findViewById(R.id.dashboard_customer);
    }

    private void initAdapter() {
        adapter = new PagingCustomerAdapter(this, customers);
    }

    private void initLayoutManager() {
        layoutManager = new LinearLayoutManager(this);
    }

    private void configRecyclerView() {
        initAdapter();
        initLayoutManager();

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(false);
        recyclerView.setAdapter(adapter);

        assignOnScrollEvent();
    }

    private void getRoomId() {
        roomId = getIntent().getExtras().getString("roomId");
    }

    private void addCustomerOnClick() {
        addCustomerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (customers == null || customers.isEmpty()) {
                    Intent intent = new Intent(DashboardKhachTroActivity.this, CreateContractActivity.class);

                    intent.putExtra("token", token);
                    intent.putExtra("roomId", roomId);

                    startActivity(intent);
                } else {
                    Intent intent = new Intent(DashboardKhachTroActivity.this, DetailKhachTroActivity.class);

                    intent.putExtra("token", token);
                    intent.putExtra("roomId", roomId);
                    intent.putExtra("floor", floor);

                    startActivity(intent);
                }
            }
        });
    }

    private void assignOnScrollEvent() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (!isLoading && recyclerView.getLayoutManager() != null && ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition() == customers.size() - 1) {
                    isLoading = true;
                    loadNextPage(++pageNum);
                }
            }
        });
    }

    private void assignOnRefreshListener() {
        customerRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (!isLoading) {
                    isLoading = true;
                    loadInitial();
                }
            }
        });
    }

    private void loadNextPage(int page) {
        presenter.loadNextPage(token, roomId, page, customers, adapter);
    }

    private void loadInitial() {
        presenter.loadInitial(token, roomId, customers, adapter);
    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        getMenuInflater().inflate(R.menu.facility_long_click_menu, menu);

        selectedItem = v.getId();
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                Intent intent=new Intent(this, SuaKhachTroActivity.class);

                intent.putExtra("token", token);
                intent.putExtra("Id", customers.get(item.getGroupId()).get_id());
                intent.putExtra("Index", item.getGroupId());

                startActivity(intent);

                break;
            case 2:
                presenter.deleteCustomer(token, customers.get(item.getGroupId()).get_id());
                loadInitial();
                break;
        }

        return super.onContextItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();

        loadInitial();
        ListContainers.getInstance().setCustomers(customers);
    }

    @Override
    public void getSuccess() {
        updateDashboard();
    }

    @Override
    public void getFailure(String mess) {
        isLoading = false;
        Toast.makeText(this, mess, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateDashboard() {
        isLoading = false;
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setIsLoading(boolean value) {
        isLoading = value;
    }

    @Override
    public void setPageNum(int value) {
        pageNum = value;
    }

    @Override
    public void setRefreshingState(boolean state) {
        customerRefreshLayout.setRefreshing(state);
    }

    @Override
    public void updateItemInserted(int pos) {
        adapter.notifyItemInserted(pos);
    }

    @Override
    public void updateItemRemoved(int pos) {
        adapter.notifyItemRemoved(pos);
    }

    @Override
    public void updateDataSetChange() {
        adapter.notifyDataSetChanged();
    }
}
