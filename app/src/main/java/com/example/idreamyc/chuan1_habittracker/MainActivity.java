package com.example.idreamyc.chuan1_habittracker;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends Activity {
    public final static String EXTRA_MESSAGE = "com.idreamyc.chuan1_habittracker.MESSAGE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    // Add New Habit
    public void add_new_habit(View view){
        Intent intent = new Intent(this, AddHabit.class);
        startActivity(intent);
    }
    // View All Habits
    public void view_all_habits(View view){
        Intent intent = new Intent(this, ViewHabit.class);
        startActivity(intent);
    }
    //Complete a Habit
    public void CompleteHabits(View view){
        Intent intent = new Intent(this, CompleteHabits.class);
        startActivity(intent);
    }
    //End the app
    public void exit(View view){
        finish();
    }
}
