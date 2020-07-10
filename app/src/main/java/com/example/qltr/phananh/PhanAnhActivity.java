package com.example.qltr.phananh;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;

import com.example.Models.Report.Report;
import com.example.Utils.ListContainers;
import com.example.qltr.Adapters.PagingPhanAnhAdapter;
import com.example.qltr.R;
import com.example.qltr.phongtro.SuaPhongTro.SuaPhongTroActivity;

import java.util.ArrayList;

public class PhanAnhActivity extends AppCompatActivity implements PhanAnhContract.View {

    private String token;
    private int page;
    private static final int PAGE_SIZE=10;
    private boolean isLoading;
    private int filterBy;

    public static final int BY_DONE=0;
    public static final int BY_PROCESSING=1;
    public static final int BY_ALL=2;

    private ArrayList<Report> reports;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private SwipeRefreshLayout refreshLayout;

    private PhanAnhPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phan_anh);

        presenter=new PhanAnhPresenter(this, this);

        page=1;
        filterBy=BY_ALL;

        assignViews();
        getToken();

        reports=new ArrayList<>();
        reports.add(null);
        adapter=new PagingPhanAnhAdapter(this, reports);
        loadInitial();

//        ListContainers.getInstance().set(rooms);

        assignOnRefreshListenner();
//        assignOnScrollEvent();
        configureRecyclerView();


    }

    public void configureRecyclerView() {
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setHasFixedSize(false);

        adapter = new PagingPhanAnhAdapter(this, reports);
        recyclerView.setAdapter(adapter);

        assignOnScrollEvent();
    }

    public void assignOnRefreshListenner() {
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (!isLoading) {
                    isLoading = true;
                    loadInitial();
                }
            }
        });

//        isLoading=false;
    }

    public void assignOnScrollEvent() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

//                Log.i("isLoading", Boolean.toString(isLoading));
//                Log.i("pos", Integer.toString(((GridLayoutManager) recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition()));

                if (!isLoading && recyclerView.getLayoutManager() != null && ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition() == reports.size() - 1) {
                    isLoading = true;
                    loadNextPage();
                }

//                isLoading=false;
            }
        });
    }

    private void loadInitial() {
        isLoading=true;

        Report type;

        if(filterBy==BY_ALL){
            presenter.loadInitial(token, 1, PAGE_SIZE, reports);
        }
        else{
            type=getFilterType();
            presenter.loadInitial(token, 1, PAGE_SIZE, type,reports);
        }

        page=1;
    }

    public void loadNextPage(){
//        isLoading=false;

        Report type;

        if(filterBy==BY_ALL){
            presenter.loadNext(token, ++page, PAGE_SIZE, reports);
        }
        else{
            type=getFilterType();
            presenter.loadNext(token, ++page, PAGE_SIZE, type, reports);
        }
    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        getMenuInflater().inflate(R.menu.facility_long_click_menu, menu);

//        selectedItem = v.getId();
    }

    public Report getFilterType(){
        switch (filterBy){
            case BY_DONE:
                return new Report("done");
            case BY_PROCESSING:
                return new Report("processing");
        }

        return null;
    }

    private void initPresenter(){
        presenter=new PhanAnhPresenter(this, this);
    }

    private void getToken(){
        token=getSharedPreferences("com.example.qltr.login", MODE_PRIVATE).getString("token", null);
    }

    private void assignViews(){
        recyclerView=findViewById(R.id.phananh_dashboard);
        refreshLayout=findViewById(R.id.report_dashboard_refresh_layout);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                presenter.confirm(token, reports.get(item.getGroupId()).get_id(), item.getGroupId(), new Report("done"), reports);
                break;
            case 2:
                presenter.delete(token, reports.get(item.getGroupId()).get_id(), item.getGroupId(), reports);
                break;
        }

        return super.onContextItemSelected(item);
    }


    public void filterProcessing(View view) {
        filterBy=BY_PROCESSING;
        loadInitial();
    }

    public void filterDone(View view) {
        filterBy=BY_DONE;
        loadInitial();
    }

    public void filterAll(View view) {
        filterBy=BY_ALL;
        loadInitial();
    }

    @Override
    public void getSuccess() {

    }

    @Override
    public void getFailure(String mess) {

    }

    @Override
    public void updateDashboard() {

    }

    @Override
    public void setIsLoading(boolean value) {
        isLoading=value;
    }

    @Override
    public void setPageNum(int value) {
        page=value;
    }

    @Override
    public void setRefreshingState(boolean state) {
        refreshLayout.setRefreshing(state);
    }

    @Override
    public void updateItemInserted(int pos) {

    }

    @Override
    public void updateItemRemoved(int pos) {

    }

    @Override
    public void updateDataSetChange() {
        adapter.notifyDataSetChanged();
    }
}
