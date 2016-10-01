package com.example.idreamyc.chuan1_habittracker;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class DeleteHabit_Comp extends Activity {
    private static final String FILENAME2 = "HabitOfDay.sav";
    private static final String FILENAME = "file.sav";
    private ArrayList<Habit> habit = new ArrayList<Habit>();
    private Habit Habit_day;
    private String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_habit__comp);
        Button backButton = (Button) findViewById(R.id.button5);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        Button deleteButton = (Button) findViewById(R.id.button7);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                for (int i =0; i < habit.size();i++) {
                    if (Habit_day.equals(habit.get(i))) {
                        String done_message="The Habit: "+habit.get(i).getHabit()+" has been deleted!";
                        habit.remove(i);
                        saveInFile();
                        TextView output = (TextView) findViewById(R.id.textView6);
                        output.setText(done_message);
                    }
                }
            }
        });

        Button delete_compButton = (Button) findViewById(R.id.button6);
        delete_compButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                for (int i =0; i < habit.size();i++) {
                    if (Habit_day.equals(habit.get(i))) {
                        String done_message="The Habit: "+habit.get(i).getHabit()+"'s completions have been deleted!";
                        habit.get(i).setComplete_times(0);
                        saveInFile();
                        TextView output = (TextView) findViewById(R.id.textView6);
                        output.setText(done_message);

                        message = habit.get(i).toString()+"\n";
                        output = (TextView) findViewById(R.id.textView5);
                        output.setText(message);
                    }
                }
            }
        });

        loadFromFile();
        loadFromFile2();

        message = Habit_day.toString()+"\n";
        TextView output = (TextView) findViewById(R.id.textView5);
        output.setText(message);
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
}
