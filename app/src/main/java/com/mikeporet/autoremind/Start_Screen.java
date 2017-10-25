package com.mikeporet.autoremind;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class Start_Screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start__screen);

        String fileName = "car.srl";

        File directory = new File(getFilesDir() + File.pathSeparator + fileName);
        Car car = null;
        try {
            FileInputStream fStream = new FileInputStream(directory);
            ObjectInputStream is = new ObjectInputStream(fStream);
            car = (Car) is.readObject();
        } catch (Exception e) {
            Log.d("FileInputError", e.toString());
        }
        if(car != null) {
            Log.d("CarRetrieved", car.toString());
            Intent intent = new Intent(this, CarHomeScrollingActivity.class);
            intent.putExtra("Car", car);
            startActivity(intent);
        } else {
            Log.d("CarIsNull", "Car is Null");
        }
    }

    public void start_click(View view){
        //Toast toast = Toast.makeText(getApplicationContext(),"Don't push my buttons", Toast.LENGTH_LONG);
        //toast.show();
        Intent intent = new Intent(this, MakesActivity.class);
        startActivity(intent);
        return;
    }
}
