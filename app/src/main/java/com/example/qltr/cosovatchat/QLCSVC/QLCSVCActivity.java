package com.example.qltr.cosovatchat.QLCSVC;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.APIHelpers.RetrofitClient;
import com.example.Models.Facility.Facility;
import com.example.Models.Facility.GetFacilityApiResponse;
import com.example.qltr.Adapters.PagingFacilityAdapter;
import com.example.qltr.R;
import com.example.qltr.cosovatchat.ThemCSVC.ThemCSVCActivity;

import java.util.ArrayList;

public class QLCSVCActivity extends AppCompatActivity {
    private final int PAGE_SIZE=10;

    private RecyclerView recyclerView;
    private ArrayList<Facility> facilities;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private boolean isLoading;
    private int pageCount=1;
    private String token;
    private SwipeRefreshLayout pullToRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q_l_c_s_v_c);

        isLoading=false;

        facilities=new ArrayList<>();
        facilities.add(null);

        assignViews();
        assignOnRefreshListener();
        configureFacilitiesRecyclerView();
        getToken();
        loadInitial();
    }

    private void getToken(){
        token=getSharedPreferences("com.example.qltr.login", MODE_PRIVATE).getString("token", null);
    }

    public void assignViews(){
        recyclerView=findViewById(R.id.facilities_recyclerview);
        pullToRefresh=findViewById(R.id.pull_to_refresh_layout);
    }

    private void assignOnRefreshListener(){
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(!isLoading){
                    isLoading=true;
                    loadInitial();
                }
            }
        });
    }

    public void createNewFacilityOnClick(View view){
        startActivity(new Intent(this, ThemCSVCActivity.class));
    }

    public void configureFacilitiesRecyclerView(){
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setHasFixedSize(false);

//        FacilityViewModel facilityViewModel= ViewModelProviders.of(this).get(FacilityViewModel.class);
        adapter=new PagingFacilityAdapter(this, facilities);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if(!isLoading && recyclerView.getLayoutManager()!=null && ((LinearLayoutManager)recyclerView.getLayoutManager()).findFirstCompletelyVisibleItemPosition()==0){

                }

                if(!isLoading &&
                        recyclerView.getLayoutManager()!=null &&
                        ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition()==facilities.size()-1){
                    isLoading=true;
                    loadNextPage(++pageCount);
//                    isLoading=true;
                }
            }
        });
    }

    public void loadNextPage(int pageNum){
        facilities.add(null);
        int insertedPos=facilities.size()-1;
        adapter.notifyItemInserted(facilities.size()-1);

        RetrofitClient.getInstance().getApi().getItemsInPage(token, pageNum, PAGE_SIZE).enqueue(new Callback<GetFacilityApiResponse>() {
            @Override
            public void onResponse(Call<GetFacilityApiResponse> call, Response<GetFacilityApiResponse> response) {
                if(response.body()!=null && response.body().isSuccess()){
                    if(!response.body().getData().isEmpty()){
                        facilities.remove(facilities.size()-1);
                        adapter.notifyItemRemoved(facilities.size());
                        for(Facility item : response.body().getData()){
                            facilities.add(item);
                        }
                        adapter.notifyDataSetChanged();
                    }
                    else{
                        facilities.remove(facilities.size()-1);
                        adapter.notifyItemRemoved(facilities.size());
                    }
                }
                else{
                    facilities.remove(facilities.size()-1);
                    adapter.notifyItemRemoved(facilities.size());
                }

                isLoading=false;
            }

            @Override
            public void onFailure(Call<GetFacilityApiResponse> call, Throwable t) {
                Toast.makeText(QLCSVCActivity.this, "Vui lòng kiểm tra kết nối mạng và thử lại!", Toast.LENGTH_LONG).show();
                isLoading=false;
            }
        });
    }

    private void loadInitial(){
        isLoading=true;
        RetrofitClient.getInstance().getApi().getItemsInPage(token, 1, PAGE_SIZE).enqueue(new Callback<GetFacilityApiResponse>() {
            @Override
            public void onResponse(Call<GetFacilityApiResponse> call, Response<GetFacilityApiResponse> response) {
                if(response.body()!=null && response.body().isSuccess()){
                    facilities.clear();
                    adapter.notifyItemRemoved(facilities.size());
                    for(Facility item : response.body().getData()){
                        facilities.add(item);
                    }
                    adapter.notifyDataSetChanged();
                }
                pullToRefresh.setRefreshing(false);
                isLoading=false;
            }

            @Override
            public void onFailure(Call<GetFacilityApiResponse> call, Throwable t) {
                Toast.makeText(QLCSVCActivity.this, "Vui lòng kiểm tra kết nối mạng và thử lại!", Toast.LENGTH_LONG).show();
                pullToRefresh.setRefreshing(false);
                isLoading=false;
            }
        });
    }


}
