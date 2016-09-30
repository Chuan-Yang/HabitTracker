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

public class ViewHabit extends Activity {
    private static final String FILENAME = "file.sav";
    private static final String FILENAME2 = "HabitOfDay.sav";
    private ArrayList<Habit> habit = new ArrayList<Habit>();
    private ArrayAdapter<Habit> adapter;
    private ListView oldHabits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_habit);

        oldHabits = (ListView) findViewById(R.id.listView);
        Button backButton = (Button) findViewById(R.id.button3);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        oldHabits = (ListView) findViewById(R.id.listView);
        oldHabits.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> l, View v, int position, long id) {
                // For debug:
                // Log.i("HelloListView", "You clicked Item: " + id + " at position:" + position);
                saveInFile(position);
                habit.clear();
                adapter.notifyDataSetChanged();
                Intent intent = new Intent(ViewHabit.this, DeleteHabit_Comp.class);
                startActivity(intent);
                loadFromFile();
            }
        });
    }

    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        loadFromFile();
        adapter = new ArrayAdapter<Habit>(this,
                R.layout.list_item, habit);
        oldHabits.setAdapter(adapter);
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

    public void add_new_habit(View view){
        Intent intent = new Intent(this, AddHabit.class);
        startActivity(intent);
    }
}
