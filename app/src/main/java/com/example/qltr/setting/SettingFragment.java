package com.example.qltr.setting;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.qltr.R;
import com.example.qltr.login.LoginActivity;
import com.example.qltr.login.LoginContract;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

public class SettingFragment extends Fragment {
    private static final String TAG = "PhongTroFragment";

    private CardView signOut;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup containter, @Nullable Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_setting, containter, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        assignViews();
        signOutOnClick();
    }

    private void assignViews(){
        signOut=getView().findViewById(R.id.sign_out);
    }

    private void signOutOnClick(){
        signOut.setOnClickListener(view -> {
            getContext().getSharedPreferences("com.example.qltr.login", Context.MODE_PRIVATE).edit().remove("token").commit();

            startActivity(new Intent(getContext(), LoginActivity.class));

            getActivity().finish();
        });
    }
}
