package com.mikeporet.autoremind;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mikeporet on 10/25/17.
 */

public class Storage implements Serializable{
    List<Car> cars;
    List<List<Task>> tasks;
    List<Double> mileage;

    public Storage(List<Car> cars, List<List<Task>> tasks, List<Double> mileage) {
        this.cars = cars;
        this.tasks = tasks;
        this.mileage = mileage;
    }

    public Storage() {
        this.cars = new ArrayList<>();
        this.tasks = new ArrayList<>();
        this.mileage = new ArrayList<>();
    }

    public void addCar(Car car, List<Task> task, Double mileage){
        this.cars.add(car);
        this.tasks.add(task);
        this.mileage.add(mileage);
    }

    public void setCar(int index, Car car, List<Task> task, Double mileage){
        this.cars.set(index, car);
        this.tasks.set(index, task);
        this.mileage.set(index, mileage);
    }

    public Car getCar(int index) {
        return cars.get(index);
    }

    public List<Task> getTasks(int index) {
        return tasks.get(index);
    }

    public Double getMileage(int index) {
        return mileage.get(index);
    }

    public int size(){
        return this.cars.size();
    }
}
