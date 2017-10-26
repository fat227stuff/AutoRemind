package com.mikeporet.autoremind;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class Start_Screen extends AppCompatActivity {

    public HashMap<String, ArrayList<String>> makesModels;
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
        makesModels = new HashMap<>();
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            JSONArray carList = obj.getJSONArray("Makes");

            for (int i = 0; i < carList.length(); i++) {
                JSONObject current_make = carList.getJSONObject(i);
                String make_name = current_make.getString("Name");
                JSONArray jModelList = current_make.getJSONArray("Models");
                ArrayList<String> thisMake = new ArrayList<>();
                for (int j = 0; j < jModelList.length(); j++)
                {
                    thisMake.add((String) jModelList.get(j));
                }
                makesModels.put(make_name,thisMake);
                String nothing = "donothing";
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("car_list.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public void start_click(View view){
        //Toast toast = Toast.makeText(getApplicationContext(),"Don't push my buttons", Toast.LENGTH_LONG);
        //toast.show();
        Intent intent = new Intent(this, MakesActivity.class);
        intent.putExtra("makesModels", makesModels);
        startActivity(intent);
        return;
    }
}
