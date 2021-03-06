package com.mikeporet.autoremind;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import org.w3c.dom.Text;

import android.net.Uri;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class TaskInstructionsActivity extends AppCompatActivity {

    private RecyclerView suppliesReView;
    private RecyclerView stepsReView;
    private TextView taskTitle;
    private TextView taskDate;
    private Car car;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_instructions);
        final Task current_task =(Task) getIntent().getSerializableExtra("Task");
        car = (Car) getIntent().getSerializableExtra("Car");
        taskTitle = (TextView) findViewById(R.id.task_page_title);
        taskTitle.setText(current_task.getTitle());
        setTitle("");
        taskDate = (TextView) findViewById(R.id.task_page_date);
        String date_text = "Due on " + current_task.dueDateToString();
        taskDate.setText(date_text);

        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.instruction_bar);
        appBarLayout.setBackground(getDrawable(current_task.getImage()));

        //Get Supplies
        suppliesReView = (RecyclerView) findViewById(R.id.suppliesReView);

        List<Supply> supplies = current_task.getSupplies();

        TaskSuppliesRecyclerAdapter adapter = new TaskSuppliesRecyclerAdapter(supplies, car);
        suppliesReView.setAdapter(adapter);
        suppliesReView.setLayoutManager(new LinearLayoutManager(this));

        //Set up Task Steps
        stepsReView = (RecyclerView) findViewById(R.id.stepsReView);

        String[] steps = current_task.getSteps();
        TaskStepsRecyclerAdapter stepAdapter = new TaskStepsRecyclerAdapter(steps);
        stepsReView.setAdapter(stepAdapter);
        stepsReView.setLayoutManager(new LinearLayoutManager(this));

        //Keep doing the regular stuff
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Done!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Car car = (Car)getIntent().getSerializableExtra("Car");
                ArrayList<Task> tasks = (ArrayList<Task>) getIntent().getSerializableExtra("Tasks");
                int result = -1;
                for (int i = 0; i < tasks.size(); i++) {
                    if (tasks.get(i).getTitle().equals(current_task.getTitle())) {
                        result = i;
                    }
                }
                double mileage =  getIntent().getDoubleExtra("Mileage",0.0);
                tasks.get(result).recomputeDate(mileage);
                DataSaver.updateFile(car, tasks, mileage, TaskInstructionsActivity.this);
                finish();
            }
        });

    }
}
