package com.mikeporet.autoremind;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static android.R.id.list;

public class MakesActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    public ArrayList<String> makeList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_makes);
        HashMap<String, ArrayList<String>> makesModels = (HashMap<String, ArrayList<String>>) getIntent().getSerializableExtra("makesModels");
        makeList = new ArrayList<>();
        Iterator it = makesModels.entrySet().iterator();
        while (it.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry)it.next();
            makeList.add((String) pair.getKey());
//            it.remove(); // avoids a ConcurrentModificationException
        }
        Collections.sort(makeList, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.compareToIgnoreCase(s2);
            }
        });


        recyclerView = (RecyclerView) findViewById(R.id.makerv);

        CarMakesRecyclerAdapter adapter = new CarMakesRecyclerAdapter(makeList,makesModels);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        setTitle("Choose a Make");
    }

    public ArrayList<String> getMakeList() {
        return makeList;
    }

    public void setMakeList(ArrayList<String> makeList) {
        this.makeList = makeList;
    }
}
