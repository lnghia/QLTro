package com.example.qltr.nhatro;

import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.qltr.Adapters.CustomGridViewAdapter;
import com.example.qltr.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class NhaTroFragment extends Fragment {
    private static final String TAG = "PhongTroFragment";

    private ArrayList<Pair<String, Integer>> buttonsInfo;
    private CustomGridViewAdapter gAdapter;
    private GridView buttonGridView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup containter, @Nullable Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_nhatro, containter, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        buttonGridView=view.findViewById(R.id.button_grid_view);
        initButtonView();
    }

    public void initButtonView(){
        buttonsInfo=new ArrayList<Pair<String, Integer>>(){
            {
                add(new Pair<String, Integer>("Dịch Vụ", R.drawable.miscellaneous));
                add(new Pair<String, Integer>("Tạo Hợp Đồng", R.drawable.business));
                add(new Pair<String, Integer>("Thanh Lí Hợp Đồng", R.drawable.finishcontract));
                add(new Pair<String, Integer>("Cọc Giữ Chỗ", R.drawable.payinad));
                add(new Pair<String, Integer>("Chốt điện nước", R.drawable.lightbulbs));
                add(new Pair<String, Integer>("Lập Hóa Đơn Điện Nước", R.drawable.invoice));
                add(new Pair<String, Integer>("Hóa Đơn Thu Tiền", R.drawable.invoice));
                add(new Pair<String, Integer>("Quản Lý Thu", R.drawable.wallet));
                add(new Pair<String, Integer>("Quản Lý Chi", R.drawable.pay));
            }
        };

        gAdapter=new CustomGridViewAdapter(buttonsInfo, getContext());
        buttonGridView.setAdapter(gAdapter);

        buttonGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getContext(), Integer.toString(i), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
