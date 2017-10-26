package com.mikeporet.autoremind;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.List;

public class CarHomeScrollingActivity extends AppCompatActivity {

    private Storage storage;
    private Car car;
    private RecyclerView recyclerView;
    private List<Task> taskList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_home_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_car);
        setSupportActionBar(toolbar);

        storage = (Storage) getIntent().getSerializableExtra("Storage");
        car = storage.getCar(0);

        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.app_bar_car);
        appBarLayout.setBackground(getDrawable(car.getImage()));
        String name = car.toString();
        setTitle(name);

        taskList = storage.getTasks(0);
        recyclerView = (RecyclerView) findViewById(R.id.rv);

        CarTasksRecyclerAdapter adapter = new CarTasksRecyclerAdapter(taskList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_car);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

//    @Override
//    public void onBackPressed() {
//        Intent intent = new Intent(Intent.ACTION_MAIN);
//        intent.addCategory(Intent.CATEGORY_HOME);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(intent);
//    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

}