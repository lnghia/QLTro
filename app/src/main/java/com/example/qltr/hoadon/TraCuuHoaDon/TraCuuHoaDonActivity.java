package com.example.qltr.hoadon.TraCuuHoaDon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.Models.Invoice.Invoice;
import com.example.Models.Invoice.InvoiceGetAllRoom;
import com.example.Models.Invoice.InvoiceGetByRoomId;
import com.example.Models.Invoice.InvoiceGetByStatus;
import com.example.Utils.DatePicker.DatePickerFragment;
import com.example.qltr.Adapters.PagingHoaDonAdapter;
import com.example.qltr.R;
import com.example.qltr.khachtro.SuaKhachTro.SuaKhachTroActivity;

import java.util.ArrayList;
import java.util.Calendar;

public class TraCuuHoaDonActivity extends AppCompatActivity implements TraCuuHoaDonContract.View {

    private EditText fromDateEdt;
    private EditText toDateEdt;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private PagingHoaDonAdapter adapter;
    private ArrayList<Invoice> invoices;
    private SwipeRefreshLayout swipeRefreshLayout;

    private boolean isLoading;

    private TraCuuHoaDonPresenter presenter;

    private String token;
    private int pageNum;
    private String roomId;
    private byte isPaid;
    private static final int PAGE_SIZE=10;

    private void assignViews(){
        fromDateEdt=findViewById(R.id.from_date);
        toDateEdt=findViewById(R.id.to_date);
        recyclerView=findViewById(R.id.hoadon_dashboard);
        swipeRefreshLayout=findViewById(R.id.invoice_dashboard_refresh_layout);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tra_cuu_hoa_don);

        initPresenter();

        isLoading=false;
        pageNum=1;
        isPaid=-1;

        assignViews();
        getConstants();

        invoices=new ArrayList<>();
        invoices.add(null);

        configureRecyclerView();

        assignOnRefreshListenner();
//        assignOnScrollEvent();

        initViews();

        loadInitial();
    }

    private void getToken(){
        token=getIntent().getExtras().getString("token");
    }

    private void getRoomId(){
        roomId=getIntent().getExtras().getString("roomId", "");
    }

    private void getConstants(){
//        getRoomId();
        getToken();
    }

    public void fromDateOnClick(View view) {
        initDatePickerDialog(fromDateEdt, "Từ ngày");
    }

    public void toDateOnClick(View view) {
        initDatePickerDialog(toDateEdt, "Đến ngày");
    }

    private void initDatePickerDialog(EditText editText, String tag){
        DatePickerFragment datePickerFragment=new DatePickerFragment(editText);

        datePickerFragment.show(getSupportFragmentManager(), tag);
    }

    private void initPresenter(){
        presenter=new TraCuuHoaDonPresenter(this, this);
    }

    private void loadInitial(){
        isLoading=true;

        if(isPaid!=-1){
            presenter.loadInitial(token,
                                   new InvoiceGetByStatus(fromDateEdt.getText().toString(),
                                                                toDateEdt.getText().toString(),
                                                                (isPaid==1)?true:false),
                                   invoices);
        }
        else{
                presenter.loadInitial(token,
                                       1,
                                       PAGE_SIZE,
                                       new InvoiceGetAllRoom(fromDateEdt.getText().toString(),
                                                                   toDateEdt.getText().toString()),
                                       invoices);
//            }
        }

//        swipeRefreshLayout.setRefreshing(false);
//        pageNum=1;
    }

//    private void loadNextPage(){
//        if(isPaid!=-1){
//            presenter.loadNextPage(token,
//                                   ++pageNum,
//                                   PAGE_SIZE,
//                                   new InvoiceGetByStatus(fromDateEdt.getText().toString(),
//                                                                toDateEdt.getText().toString(),
//                                                                (isPaid==1)?true:false),
//                                   invoices);
//        }
//        else{
////            if(!roomId.isEmpty()){
////                presenter.loadNextPage(token,
////                                       ++pageNum,
////                                       PAGE_SIZE,
////                                       new InvoiceGetByRoomId(fromDateEdt.getText().toString(),
////                                                                    toDateEdt.getText().toString(),
////                                                                    roomId),
////                                       invoices);
////            }
////            else{
//                presenter.loadNextPage(token,
//                                       ++pageNum,
//                                       PAGE_SIZE,
//                                       new InvoiceGetAllRoom(fromDateEdt.getText().toString(),
//                                                                   toDateEdt.getText().toString()),
//                                       invoices);
////            }
//        }
//    }

    @Override
    public void assignOnRefreshListenner() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(!isLoading){
                    isLoading=true;
                    loadInitial();
                }
            }
        });
    }

    @Override
    public void raiseNote(String note) {
        Toast.makeText(this, note, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void configureRecyclerView() {
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setHasFixedSize(false);

        adapter = new PagingHoaDonAdapter(this, invoices);
        recyclerView.setAdapter(adapter);

        assignOnScrollEvent();
    }

    @Override
    public void assignOnScrollEvent() {

    }

//    @Override
//    public void assignOnScrollEvent() {
//        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//            }
//
//            @Override
//            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//
//                if (!isLoading && recyclerView.getLayoutManager() != null &&
//                        ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition() == invoices.size() - 1) {
//                    isLoading = true;
//                    loadNextPage();
//                }
//            }
//        });
//    }

    @Override
    public void initViews() {
        Calendar c = Calendar.getInstance();

        StringBuilder sb=new StringBuilder("");

        sb.append(Integer.toString(c.get(Calendar.YEAR)));
        sb.append('-');
        sb.append(Integer.toString(c.get(Calendar.MONTH)));
        sb.append('-');
        sb.append(Integer.toString(c.get(Calendar.DATE)));

        fromDateEdt.setText(sb.toString());
        toDateEdt.setText(sb.toString());
    }

    @Override
    public void updateDataSetChanged() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setIsLoading(boolean val) {
        isLoading=val;
    }

    @Override
    public void setRefresh(boolean value) {
        swipeRefreshLayout.setRefreshing(value);
    }

    @Override
    public void setPageNum(int i) {
        pageNum=i;
    }

    public void filterPaiedInvoices(View view) {
        isPaid=1;
        loadInitial();
    }

    public void filterUnPaiedInvoices(View view) {
        isPaid=0;
        loadInitial();
    }

    public void filterAllInvoices(View view) {
        isPaid=-1;
        loadInitial();
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                presenter.confirm(token, invoices.get(item.getGroupId()).get_id(), item.getGroupId(), invoices);
                loadInitial();
                break;
        }

        return super.onContextItemSelected(item);
    }
}
