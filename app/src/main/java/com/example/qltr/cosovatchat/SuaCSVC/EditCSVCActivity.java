package com.example.qltr.cosovatchat.SuaCSVC;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.Models.Facility.Facility;
import com.example.Utils.ListContainers;
import com.example.qltr.R;

public class EditCSVCActivity extends AppCompatActivity implements EditCSVCContract.EditCSVCView {
    private String token;
    private EditCSVCContract.EditCSVCPresenter presenter;
    private int id;
    private int index;
    private EditText facilityNameEdt;
    private EditText facilityPriceEdt;
    private EditText facilityQuantityEdt;
    private EditText facilityDescEdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_c_s_v_c);

        token=getIntent().getExtras().getString("token");
        id=getIntent().getIntExtra("id", -1);
        presenter=new EditCSVCPresenter(this, this);

        assignViews();
        setContent();
    }

    private void assignViews(){
        facilityDescEdt=findViewById(R.id.facility_note_edt_edit);
        facilityNameEdt=findViewById(R.id.facility_name_edt_edit);
        facilityPriceEdt=findViewById(R.id.facility_unit_price_edt_edit);
        facilityQuantityEdt=findViewById(R.id.facility_number_edt_edit);
    }

    private void setContent(){
        Facility temp=ListContainers.getInstance().getFacilities().get(id);

        facilityQuantityEdt.setText(Integer.toString(temp.getQuantity()));
        facilityPriceEdt.setText(Integer.toString(temp.getUnitPrice()));
        facilityNameEdt.setText(temp.getName());
        facilityDescEdt.setText(temp.getDescription());
    }

    @Override
    public void editSuccess(String note) {
        Toast.makeText(this, note, Toast.LENGTH_SHORT).show();

        finish();
    }

    @Override
    public void editFailure(String err) {
        Toast.makeText(this, err, Toast.LENGTH_SHORT).show();
    }

    public void editFacilityOnClick(View view) {
        presenter.edit(token, id, new Facility(facilityNameEdt.getText().toString(),
                                                                    Integer.parseInt(facilityPriceEdt.getText().toString()),
                                                                    Integer.parseInt(facilityQuantityEdt.getText().toString()),
                                                                    facilityDescEdt.getText().toString()));
    }

    public void cancelEditFacOnClick(View view) {
        finish();
    }
}
