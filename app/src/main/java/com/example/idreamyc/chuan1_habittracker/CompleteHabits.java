package com.example.idreamyc.chuan1_habittracker;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CompleteHabits extends Activity {

    private static final String FILENAME2 = "HabitOfDay.sav"; // To save the clicked habit
    private static final String FILENAME = "file.sav";
    private ArrayList<Habit> habit = new ArrayList<Habit>();
    private ArrayList<Habit> Habits_day = new ArrayList<Habit>();
    private ArrayAdapter<Habit> adapter;
    private ListView Day_Habits;
    private int[] p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_habits);
        // http://stackoverflow.com/questions/13281197/android-how-to-create-clickable-listview
        // Click a View and enter it
        Day_Habits = (ListView) findViewById(R.id.listView2);
        Day_Habits.setOnItemClickListener (new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> l, View v, int position, long id) {
                // For debug:
                // Log.i("HelloListView", "You clicked Item: " + id + " at position:" + position);
                saveInFile(p[position]);
                // Update the information on the screen
                Habits_day.clear();
                adapter.notifyDataSetChanged();
                Intent intent = new Intent(CompleteHabits.this,complete_a_habit.class);
                startActivity(intent);
                loadFromFile();
            }
        });

        Button backButton = (Button) findViewById(R.id.button2);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }




    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        loadFromFile();
        String day_of_week = new SimpleDateFormat("EEEE").format(new Date());
        // Get the Habits in this day(today) of week
        int l=0;
        p = new int[1000001];
        for (int i = 0; i < habit.size(); i++){
            Habit a = habit.get(i);
            //a.print(day_of_week);
            for (int j = 0; j < a.getDays_week().length; j++){
                String day = a.getDays_week()[j];
               // if (day!=null)
                   //a.print(day);
                if (day!=null)
                    if (day.equals(day_of_week)){
                        Habits_day.add(a);
                        p[l] = i;
                        l++;
                    }

            }
        }
        adapter = new ArrayAdapter<Habit>(this,
                R.layout.list_item, Habits_day);
        Day_Habits.setAdapter(adapter);
    }

    private void saveInFile(int position) {
        try {
            FileOutputStream fos = openFileOutput(FILENAME2, 0);

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();
            gson.toJson(habit.get(position), out);
            out.flush();

            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }

    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            // Code from http://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            Type listType = new TypeToken<ArrayList<Habit>>(){}.getType();

            habit = gson.fromJson(in,listType);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            habit = new ArrayList<Habit>();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }

}
