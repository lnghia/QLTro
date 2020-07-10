package com.example.qltr.nhatro;

import android.content.Context;
import android.content.Intent;
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
import com.example.qltr.cosovatchat.QLCSVC.QLCSVCActivity;
import com.example.qltr.hoadon.TraCuuHoaDon.TraCuuHoaDonActivity;
import com.example.qltr.phananh.PhanAnhActivity;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class NhaTroFragment extends Fragment {
    private static final String TAG = "PhongTroFragment";

    private ArrayList<Pair<String, Integer>> buttonsInfo;
    private CustomGridViewAdapter gAdapter;
    private GridView buttonGridView;

    private String token;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup containter, @Nullable Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_nhatro, containter, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        buttonGridView=view.findViewById(R.id.button_grid_view);

        initButtonView();
        getToken();
    }

    private void getToken(){
        token = getActivity().getSharedPreferences("com.example.qltr.login", Context.MODE_PRIVATE).getString("token", null);
//        token=getActivity().getIntent().getExtras().getString("token");
    }

    public void initButtonView(){
        buttonsInfo=new ArrayList<Pair<String, Integer>>(){
            {
                add(new Pair<String, Integer>("Cơ sở vật chất", R.drawable.miscellaneous));
                add(new Pair<String, Integer>("Tra cứu hóa đơn", R.drawable.invoice));
                add(new Pair<String, Integer>("Xem phản ánh", R.drawable.review));
            }
        };

        gAdapter=new CustomGridViewAdapter(buttonsInfo, getContext());
        buttonGridView.setAdapter(gAdapter);

        buttonGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(getContext(), Integer.toString(i), Toast.LENGTH_SHORT).show();

                switch (i){
                    case 0:
                        startActivity(new Intent(getActivity(), QLCSVCActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(getContext(), TraCuuHoaDonActivity.class).putExtra("token", token));
                        break;
                    case 2:
                        startActivity(new Intent(getContext(), PhanAnhActivity.class).putExtra("token", token));
                        break;
                }
            }
        });
    }
}
