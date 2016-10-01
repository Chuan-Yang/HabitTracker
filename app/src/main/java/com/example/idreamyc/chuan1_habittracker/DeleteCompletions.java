package com.example.idreamyc.chuan1_habittracker;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

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
import java.util.ArrayList;
import java.util.Date;

public class DeleteCompletions extends Activity {
    private static final String FILENAME2 = "HabitOfDay.sav";
    private static final String FILENAME = "file.sav";
    private ArrayList<Habit> habit = new ArrayList<Habit>();
    private Habit Habit_day;
    private ArrayAdapter adapter;
    private ListView comp_dates;
    private ArrayList<Date> date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_completions);
        comp_dates =(ListView)findViewById(R.id.listView5);
        comp_dates.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> l, View v, int position, long id) {
                for (int i = 0; i < habit.size(); i++) {
                    if (Habit_day.equals(habit.get(i))) {
                        String done_message = "The Habit: " + habit.get(i).getHabit() + "'s one completion has been deleted!";
                        //update the completed times
                        habit.get(i).setComplete_times(habit.get(i).getComplete_times() - 1);
                        Habit_day.setComplete_times(Habit_day.getComplete_times() - 1);
                        //update the complete time
                        date.remove(habit.get(i).getComplete_time().get(position));
                        habit.get(i).setComplete_time(date);
                        Habit_day.setComplete_time(date);

                        ArrayAdapter adapter = new ArrayAdapter<Date>(DeleteCompletions.this,
                                R.layout.list_item_3, date);
                        comp_dates.setAdapter(adapter);
                        saveInFile();
                        saveInFile2();
                        TextView output = (TextView) findViewById(R.id.textView12);
                        output.setText(done_message);
                        /*
                        message = habit.get(i).details()+"\n";
                        output = (TextView) findViewById(R.id.textView5);
                        output.setText(message);
                        */
                    }
                }
            }
        });

        Button backButton = (Button) findViewById(R.id.button9);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void onStart(){
        super.onStart();
        loadFromFile();
        loadFromFile2();
        date = Habit_day.getComplete_time();
        ArrayAdapter adapter = new ArrayAdapter<Date>(this,
                R.layout.list_item_3, date);
        comp_dates.setAdapter(adapter);
    }

    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME, 0);

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();
            gson.toJson(habit, out);
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
    private void saveInFile2() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME2, 0);

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();
            gson.toJson(Habit_day, out);
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
            FileInputStream fis = openFileInput(FILENAME2);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            // Code from http://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            Type listType = new TypeToken<Habit>(){}.getType();

            Habit_day = gson.fromJson(in,listType);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            habit = new ArrayList<Habit>();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }
    private void loadFromFile2() {
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
