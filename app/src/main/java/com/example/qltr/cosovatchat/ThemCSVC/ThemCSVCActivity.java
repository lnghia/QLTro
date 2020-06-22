package com.example.qltr.cosovatchat.ThemCSVC;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.APIHelpers.RetrofitClient;
import com.example.Models.Facility;
import com.example.qltr.R;

public class ThemCSVCActivity extends AppCompatActivity implements ThemCSVCContract.ThemCSVCView {
    private ThemCSVCContract.Presenter presenter;
    private EditText facilityNameEdt;
    private EditText facilitySpecificationEdt;
    private EditText facilityUnitPrice;
    private EditText facilityNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_c_s_v_c);

        initPresenter();
        initComponents();
        assignViews();
    }

    public void assignViews(){
        facilityNameEdt=findViewById(R.id.facility_name_edt);
        facilitySpecificationEdt=findViewById(R.id.facility_note_edt);
        facilityUnitPrice=findViewById(R.id.facility_unit_price_edt);
        facilityNumber=findViewById(R.id.facility_number_edt);
    }


    public void initPresenter(){
        presenter=new ThemCSVCPresenter(this, this);
    }

    public void initComponents(){
        facilityNameEdt=findViewById(R.id.facility_name_edt);
        facilitySpecificationEdt=findViewById(R.id.facility_note_edt);
    }

    public void addFacilityOnClick(View view) {
        presenter.handleAddFacility(new Facility(facilityNameEdt.getText().toString(),
                                                            Integer.parseInt(facilityUnitPrice.getText().toString()),
                                                            Integer.parseInt(facilityNumber.getText().toString()),
                                                            facilitySpecificationEdt.getText().toString()));
    }

    public void cancelOnAddingFacilityOnClick(View view) {
        finish();
    }

    @Override
    public void addSuccess(Facility newFacility) {

        Toast.makeText(this, "Thêm thành công", Toast.LENGTH_LONG).show();

        facilityNumber.setText("");
        facilityUnitPrice.setText("");
        facilitySpecificationEdt.setText("");
        facilityNameEdt.setText("");
//        finish();
    }

    @Override
    public void addFail(String err) {
        Toast.makeText(this, err, Toast.LENGTH_LONG).show();
    }


}
