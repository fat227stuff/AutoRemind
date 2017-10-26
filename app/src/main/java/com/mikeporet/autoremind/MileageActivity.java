package com.mikeporet.autoremind;

import android.content.Intent;
import android.content.res.Resources;
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
        double mileage = Double.parseDouble(mileateInput.getText().toString());

        Storage storage = setUpStorage(car, tasks, mileage);
        Intent intent = new Intent(this, CarHomeScrollingActivity.class);
        intent.putExtra("Storage", storage);
        startActivity(intent);
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

        String fileName = "storage.srl";
        File directory = new File(getFilesDir() + File.pathSeparator + fileName);

        Storage storage = null;

        //Get Storage file so we can append new information
        FileInputStream fIStream = null;
        ObjectInputStream oIStream = null;

        try {
            fIStream = new FileInputStream(directory);
            oIStream = new ObjectInputStream(fIStream);
            storage = (Storage) oIStream.readObject();
        } catch (Exception e) {
            Log.d("FileInputError", e.toString());
        } finally {
            if(fIStream != null) {
                try {
                    fIStream.close();
                } catch (Exception e) {
                    Log.d("FileStream Close Error", e.toString());
                }
            }
            if(oIStream != null) {
                try {
                    oIStream.close();
                } catch (Exception e) {
                    Log.d("ObjStream Close Error", e.toString());
                }
            }
        }
        if(storage != null) {
            if (storage.size() < 1){
                storage.addCar(car, tasks, mileage);
            } else {
                storage.setCar(0, car, tasks, mileage);
            }
        } else {
            Log.d("StorageIsNull", "making a new storage");
            storage = new Storage();
            storage.addCar(car, tasks, mileage);
        }

        //Save car to internal storage
        FileOutputStream fStream = null;
        ObjectOutputStream oStream = null;

        try {
            fStream = new FileOutputStream(directory);
            oStream = new ObjectOutputStream(fStream);
            oStream.writeObject(storage);
            Log.d("WriteSuccess", "WriteSuccess");
        } catch (Exception e) {
            Log.d("FileStreamError", e.toString());
        } finally {
            if(fStream != null) {
                try {
                    fStream.close();
                } catch (Exception e) {
                    Log.d("FileStream Close Error", e.toString());
                }
            }
            if(oStream != null) {
                try {
                    oStream.close();
                } catch (Exception e) {
                    Log.d("ObjStream Close Error", e.toString());
                }
            }
        }

        return storage;
    }


    private List<Task> getTasks(Car car, Resources res) {
        String[] oil_steps = res.getStringArray(R.array.oil_steps);
        String[] coolant_steps = res.getStringArray(R.array.coolant_flush_steps);
        String[] battery_steps = res.getStringArray(R.array.battery_change_steps);
        String[] air_filter_steps = res.getStringArray(R.array.air_filter_change_steps);

        ArrayList<Supply> list = new ArrayList<>();
        list.add(new Supply("Oil Pan", R.drawable.oil_bottle, "This is a bottle of oil"));
        list.add(new Supply("Oil Filter", R.drawable.oil_bottle, "This is a bottle of oil"));
        list.add(new Supply("Oil", R.drawable.oil_bottle, "This is a bottle of oil"));

        List<Task> taskList = new ArrayList<>();
        int pooder = Calendar.APRIL;
        Calendar c = Calendar.getInstance();
        c.getTime();
        c.set(2017, Calendar.NOVEMBER, 12);
        taskList.add(new Task("Oil Change", R.drawable.oilchange, 5000, 1, 1, oil_steps, list, "", c.getTime()));
        c.set(2017, Calendar.OCTOBER, 31);
        taskList.add(new Task("Air Filter", R.drawable.airfilter, 8000, 2, 3, coolant_steps, list, "", c.getTime()));
        c.set(2017, Calendar.DECEMBER, 28);
        taskList.add(new Task("Battery Replacement", R.drawable.batteryreplacement, 50000, 3, 2, battery_steps, list, "", c.getTime()));
        c.set(2018, Calendar.JANUARY, 7);
        taskList.add(new Task("Coolant Flush", R.drawable.coolantflush, 10000, 1, 2, air_filter_steps, list, "", c.getTime()));
        return taskList;
    }
}
