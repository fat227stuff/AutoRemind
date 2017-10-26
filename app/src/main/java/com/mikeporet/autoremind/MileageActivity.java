package com.mikeporet.autoremind;

import android.content.Intent;
import android.content.res.Resources;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MileageActivity extends AppCompatActivity {

    private EditText mileateInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mileage);
        mileateInput = (EditText)findViewById(R.id.mileageInput);
    }

    public void finishClick(View v) {
        Car car = (Car)getIntent().getSerializableExtra("Car");
        List<Task> tasks = getTasks(car, v.getResources());
        String miles = mileateInput.getText().toString();
        double mileage = 0.0;

        if (miles != "")
        {
            try {
                mileage = Double.parseDouble(mileateInput.getText().toString());
            } catch (Exception e) {
                Snackbar.make(v, "Input a valid number", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                return;
            }

            Storage storage = setUpStorage(car, tasks, mileage);
            Intent intent = new Intent(this, CarHomeScrollingActivity.class);
            intent.putExtra("Storage", storage);
            startActivity(intent);}
        else{
            Snackbar.make(v, "Input a valid number", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }

    public void skipClick(View v) {
        Car car = (Car)getIntent().getSerializableExtra("Car");
        List<Task> tasks = getTasks(car, v.getResources());
        double mileage = 290;

        Storage storage = setUpStorage(car, tasks, mileage);
        Intent intent = new Intent(this, CarHomeScrollingActivity.class);
        intent.putExtra("Storage", storage);
        startActivity(intent);
    }

    private Storage setUpStorage(Car car, List<Task> tasks, double mileage){

        Storage storage = DataSaver.updateFile(car, tasks, mileage, this);

        return storage;
    }


    private List<Task> getTasks(Car car, Resources res) {
        String[] oil_steps = res.getStringArray(R.array.oil_steps);
        String[] coolant_steps = res.getStringArray(R.array.coolant_flush_steps);
        String[] battery_steps = res.getStringArray(R.array.battery_change_steps);
        String[] air_filter_steps = res.getStringArray(R.array.air_filter_change_steps);

        ArrayList<Supply> oilChangeSupplies = new ArrayList<>();
        oilChangeSupplies.add(new Supply("Engine Oil", ""));
        oilChangeSupplies.add(new Supply("Oil Filter", ""));
        oilChangeSupplies.add(new Supply("Paper Towels", ""));
        oilChangeSupplies.add(new Supply("Funnel", ""));
        oilChangeSupplies.add(new Supply("Oil Catch Can", "You can use a large container like a Rubbermaid"));
        oilChangeSupplies.add(new Supply("Socket Set", ""));
        oilChangeSupplies.add(new Supply("Oil Filter Wrench", ""));

        ArrayList<Supply> airFilterSupplies = new ArrayList<>();
        airFilterSupplies.add(new Supply("Air Filter", ""));

        ArrayList<Supply> batterySupplies = new ArrayList<>();
        batterySupplies.add(new Supply("Battery", ""));
        batterySupplies.add(new Supply("Socket Set", ""));

        ArrayList<Supply> coolantSupplies = new ArrayList<>();
        coolantSupplies.add(new Supply("Engine Coolant", "50/50 Premixed"));
        coolantSupplies.add(new Supply("Engine Coolant Catch Can", "You can use a large container like a Rubbermaid"));
        coolantSupplies.add(new Supply("Paper Towels", ""));
        coolantSupplies.add(new Supply("Garden Hose", ""));

        List<Task> taskList = new ArrayList<>();
        int pooder = Calendar.APRIL;
        Calendar c = Calendar.getInstance();
        c.getTime();
        c.set(2017, Calendar.NOVEMBER, 12);
        taskList.add(new Task("Oil Change", R.drawable.oilchange, 5000, 2, 2, oil_steps, oilChangeSupplies, "", c.getTime()));
        c.set(2017, Calendar.OCTOBER, 31);
        taskList.add(new Task("Air Filter", R.drawable.airfilter, 8000, 1, 1, coolant_steps, airFilterSupplies, "", c.getTime()));
        c.set(2017, Calendar.DECEMBER, 28);
        taskList.add(new Task("Battery Replacement", R.drawable.batteryreplacement, 50000, 1, 1, battery_steps, batterySupplies, "", c.getTime()));
        c.set(2018, Calendar.JANUARY, 7);
        taskList.add(new Task("Coolant Flush", R.drawable.coolantflush, 10000, 3, 2, air_filter_steps, coolantSupplies, "", c.getTime()));
        return taskList;
    }
}
