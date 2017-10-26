package com.mikeporet.autoremind;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.NumberPicker;
import android.widget.Spinner;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;

public class YearActivity extends AppCompatActivity {

    private NumberPicker mc;
    private NumberPicker dy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_year);

        mc = (NumberPicker)findViewById(R.id.numberPickerMC);
        dy = (NumberPicker)findViewById(R.id.numberPickerDY);

        dy.setFormatter(new NumberPicker.Formatter() {
            @Override
            public String format(int i) {
                return String.format("%02d", i);
            }
        });

        mc.setMinValue(19);
        mc.setMaxValue(20);
        dy.setMinValue(00);
        dy.setMaxValue(99);

    }

    public void finishClick(View v) {
        String make = getIntent().getStringExtra("Make");
        String model = getIntent().getStringExtra("Model");
        int year = mc.getValue() * 100 + dy.getValue();
        int carImage = getCarImage(make, model, year, v.getContext());
        Car car = new Car(make, model, year, carImage);

        Intent intent = new Intent(this, MileageActivity.class);
        intent.putExtra("Car", car);
        startActivity(intent);
    }

    private int getCarImage(String make, String model, int year, Context context) {
        String packageName = context.getPackageName();
        int result = context.getResources().getIdentifier(make.toLowerCase(), "drawable", packageName);
        return result;
    }
}
