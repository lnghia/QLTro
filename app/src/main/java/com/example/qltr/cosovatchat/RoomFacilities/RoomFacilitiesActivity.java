//package com.example.qltr.cosovatchat.RoomFacilities;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.RecyclerView;
//import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//
//import com.example.Models.Facility.Facility;
//import com.example.Utils.ListContainers;
//import com.example.qltr.R;
//import com.example.qltr.cosovatchat.QLCSVC.QLCSVCPresenter;
//import com.example.qltr.cosovatchat.ThemCSVC.ThemCSVCActivity;
//
//import java.util.ArrayList;
//
//public class RoomFacilitiesActivity extends AppCompatActivity {
//    private final int PAGE_SIZE=10;
//
//    private RoomFacilitiesPresenter presenter;
//
//    private RecyclerView recyclerView;
//    private ArrayList<Facility> facilities;
//    private RecyclerView.LayoutManager layoutManager;
//    private RecyclerView.Adapter adapter;
//    private boolean isLoading;
//    private int pageCount=1;
//    private String token;
//    private SwipeRefreshLayout pullToRefresh;
//    private int selectedFacility;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_q_l_c_s_v_c);
//
//        isLoading=false;
//
//        presenter=new QLCSVCPresenter(this, this);
//
//        facilities=new ArrayList<>();
//        facilities.add(null);
//
//        assignViews();
//        assignOnRefreshListener();
//        configureFacilitiesRecyclerView();
//        getToken();
//        loadInitial();
//        ListContainers.getInstance().setFacilities(facilities);
//    }
//
//    private void getToken(){
//        token=getSharedPreferences("com.example.qltr.login", MODE_PRIVATE).getString("token", null);
//    }
//
//    public void assignViews(){
//        recyclerView=findViewById(R.id.facilities_recyclerview);
//        pullToRefresh=findViewById(R.id.pull_to_refresh_layout);
//    }
//
//    private void assignOnRefreshListener(){
//        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                if(!isLoading){
//                    isLoading=true;
//                    loadInitial();
//                }
//            }
//        });
//    }
//
//    public void createNewFacilityOnClick(View view){
//        startActivity(new Intent(this, ThemCSVCActivity.class));
//    }
//
//
//}
