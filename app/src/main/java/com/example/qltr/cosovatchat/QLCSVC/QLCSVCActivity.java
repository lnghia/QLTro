package com.example.qltr.cosovatchat.QLCSVC;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.qltr.R;
import com.example.qltr.cosovatchat.ThemCSVC.ThemCSVCActivity;

public class QLCSVCActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q_l_c_s_v_c);
    }

    public void createNewFacilityOnClick(View view){
        startActivity(new Intent(this, ThemCSVCActivity.class));
    }
}
