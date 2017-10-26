package com.mikeporet.autoremind;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by mikeporet on 10/11/17.
 */

class Task implements Serializable, Comparable {

    private String title;
    private int image;
    private int interval;
    private int difficulty;
    private int num_people;
    private String[] steps;
    private List<Supply> supplies;
    private String video_url;
    private Date date;


    public Task(String title, int image, int interval, int difficulty, int num_people, String[] steps, List<Supply> supplies, String video_url, Date date) {
        this.title = title;
        this.image = image;
        this.interval = interval;
        this.difficulty = difficulty;
        this.num_people = num_people;
        this.steps = steps;
        this.supplies = supplies;
        this.video_url = video_url;
        this.date = date;
    }

    public void recomputeDate(double mileage) {
        double numWeeks = interval / mileage;
        Calendar c = Calendar.getInstance();
        c.getTime();
        c.add(Calendar.DATE, (int)numWeeks * 7);
        date = c.getTime();
    }

    @Override
    public int compareTo(@NonNull Object o) {return this.date.compareTo(((Task) o).date);
    }

    public List<Supply> getSupplies() {
        return supplies;
    }

    public void setSupplies(List<Supply> supplies) {
        this.supplies = supplies;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public void setNum_people(int num_people) {
        this.num_people = num_people;
    }

    public void setSteps(String[] steps) {
        this.steps = steps;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    public int getInterval() {

        return interval;
    }


    public String dueDateToString() {
        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
        return df.format(date);
    }

    public Date getDate() {
        return date;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public int getNum_people() {
        return num_people;
    }

    public String[] getSteps() {
        return steps;
    }

    public String getVideo_url() {
        return video_url;
    }

    public String getTitle() {
        return title;
    }

    public int getImage() {
        return image;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
