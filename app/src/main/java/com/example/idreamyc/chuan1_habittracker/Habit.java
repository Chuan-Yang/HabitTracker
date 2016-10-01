package com.example.idreamyc.chuan1_habittracker;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by idrea on 2016/9/28.
 */
public class Habit {
    private String habit;
    private String date;
    private String[] days_week;
    int complete_times;

    public Habit(String habit, String[] days_week, int complete_times){
        this.habit = habit;
        SimpleDateFormat sdf = new SimpleDateFormat( "yyyy/MM/dd" );
        this.date = sdf.format( new Date() );
        this.days_week = days_week;
        this.complete_times=complete_times;
    }

    public Habit(String date, String habit, String[] days_week, int complete_times){
        this.date = date;
        this.habit = habit;
        this.days_week = days_week;
        this.complete_times=complete_times;
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
        String days="";
        for (int i = 0; i<days_week.length;i++){
            if (days_week[i] != null){
                days += days_week[i];
                days+=" ";
            }
        }
        return   "Habit Name: " + habit + "\nAdded Date: " + date + "\nAdded days: "+ days +"\nCompleted Time: " + complete_times + "\n";
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
