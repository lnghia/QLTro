package com.example.qltr.cosovatchat.ThemCSVC;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.qltr.R;

public class ThemCSVCActivity extends AppCompatActivity implements ThemCSVCContract.ThemCSVCView {
    private ThemCSVCContract.Presenter presenter;
    private EditText facilityNameEdt;
    private EditText facilitySpecificationEdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_c_s_v_c);

        initPresenter();
        initComponents();
    }

    public void initPresenter(){
        presenter=new ThemCSVCPresenter();
    }

    public void initComponents(){
        facilityNameEdt=findViewById(R.id.facility_name_edt);
        facilitySpecificationEdt=findViewById(R.id.facility_note_edt);
    }

    public void addFacilityOnClick(View view) {

    }

    public void cancelOnAddingFacilityOnClick(View view) {
    }
}
