package com.mikeporet.autoremind;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by mikeporet on 10/26/17.
 */

public class DataSaver {

    public static Storage fetchFile(AppCompatActivity app) {

        String fileName = "storage.srl";

        File directory = new File(app.getFilesDir() + File.pathSeparator + fileName);
        Storage result = null;
        try {
            FileInputStream fStream = new FileInputStream(directory);
            ObjectInputStream is = new ObjectInputStream(fStream);
            result = (Storage) is.readObject();
        } catch (Exception e) {
            Log.d("FileInputError", e.toString());
        }

        return result;
    }

    public static Storage updateFile(Car car, List<Task> tasks, Double mileage, AppCompatActivity app) {

        String fileName = "storage.srl";

        Storage storage = null;
        File directory = new File(app.getFilesDir() + File.pathSeparator + fileName);
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
}
