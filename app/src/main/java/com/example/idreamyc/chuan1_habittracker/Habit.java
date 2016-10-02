package com.example.idreamyc.chuan1_habittracker;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by idrea on 2016/9/28.
 */
public class Habit {
    private String habit;
    private String date;
    private String[] days_week;


    private ArrayList<Date> complete_time = new ArrayList<Date>();
    int complete_times;

    public Habit(String habit, String[] days_week, int complete_times, ArrayList<Date> complete_time){
        this.habit = habit;
        this.complete_time = complete_time;
        SimpleDateFormat sdf = new SimpleDateFormat( "yyyy/MM/dd" );
        this.date = sdf.format( new Date() );
        this.days_week = days_week;
        this.complete_times=complete_times;
    }

    public Habit(String date, String habit, String[] days_week, int complete_times, ArrayList<Date> complete_time){
        this.date = date;
        this.complete_time = complete_time;
        this.habit = habit;
        this.days_week = days_week;
        this.complete_times=complete_times;
    }

    public ArrayList<Date> getComplete_time() {
        return complete_time;
    }

    public void setComplete_time(ArrayList<Date> complete_time) {
        this.complete_time = complete_time;
    }

    public void setComplete_times(int complete_times) {
        this.complete_times = complete_times;
    }

    public int getComplete_times() {
        return complete_times;
    }

    public void setHabit(String habit) {
        this.habit = habit;
    }

    public void setDays_week(String[] days_week) {
        this.days_week = days_week;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHabit() {
        return habit;
    }

    public String[] getDays_week() {
        return days_week;
    }

    public String getDate() {
        return date;
    }

    public String toString(){
        return  habit+"\nCompleted Times: "+complete_times;
    }

    public String details(){
        String days="";
        for (int i = 0; i<7 ;i++){
            if (days_week[i] != null){
                days += days_week[i];
                days+=" ";
            }
        }
        String comp_date = "";
        for (int i =0; i < complete_time.size(); i++)
            comp_date+=complete_time.get(i).toString() +"\n";
        return   "Habit Name: " + habit + "\nAdded Date: " + date + "\nAdded days: "+ days +"\nCompleted Times: " + complete_times +
                "\nComplete Time: \n" + comp_date;
    }

    // for debugging
    public void print(String string) {
        Log.d("DDDDDDDDDDDDDDD:    ",string);
    }

    @Override
    public boolean equals(Object object2) {
        return object2 instanceof Habit && habit.equals(((Habit)object2).habit);
    }
}
