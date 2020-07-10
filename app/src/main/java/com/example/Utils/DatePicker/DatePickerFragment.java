package com.example.Utils.DatePicker;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.qltr.khachtro.KhachTroDetail.DetailKhachTroActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    private int dd;
    private int mm;
    private int yyyy;
    private EditText edt;

    private int year=0;
    private int month=0;
    private int day=0;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public DatePickerFragment(EditText edt) {
        this.edt = edt;
    }

    public DatePickerFragment(DetailKhachTroActivity detailKhachTroActivity, DatePickerDialog.OnDateSetListener onDateSetListener) {
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();

        if(year==0){
            year=c.get(Calendar.YEAR);
            month=c.get(Calendar.MONTH);
            day=c.get(Calendar.DATE);
        }

        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        Calendar cal=Calendar.getInstance();

        cal.set(i, i1, i2);

        SimpleDateFormat tmp=new SimpleDateFormat("yyyy-MM-dd");

        edt.setText(tmp.format(cal.getTime()));
    }

    public int getDd() {
        return dd;
    }

    public void setDd(int dd) {
        this.dd = dd;
    }

    public int getMm() {
        return mm;
    }

    public void setMm(int mm) {
        this.mm = mm;
    }

    public int getYyyy() {
        return yyyy;
    }

    public void setYyyy(int yyyy) {
        this.yyyy = yyyy;
    }

    public EditText getEdt() {
        return edt;
    }

    public void setEdt(EditText edt) {
        this.edt = edt;
    }
}
