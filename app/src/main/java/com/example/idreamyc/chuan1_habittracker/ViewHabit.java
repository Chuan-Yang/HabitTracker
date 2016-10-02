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
   // private static final String FILENAME3 = "CompletedHabits.sav";
   // private static final String FILENAME4 = "ToDoHabits.sav";
    private ArrayList<Habit> habit = new ArrayList<Habit>();
    private ArrayList<Habit> completed_habit = new ArrayList<Habit>();
    private ArrayList<Habit> todo_habit = new ArrayList<Habit>();
    private ArrayAdapter<Habit> adapter;
    private ListView oldHabits1, oldHabits2;; // use to show the completed habits, todo habits
    int[] index1,index2; //use to record the index for completed and todo habtis

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_habit);

        Button backButton = (Button) findViewById(R.id.button3);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        oldHabits1 = (ListView) findViewById(R.id.listView);
        oldHabits1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> l, View v, int position, long id) {
                // For debug:
                // Log.i("HelloListView", "You clicked Item: " + id + " at position:" + position);
                saveInFile(index1[position]);
                habit.clear();
                completed_habit.clear();
                todo_habit.clear();
                adapter.notifyDataSetChanged();
                Intent intent = new Intent(ViewHabit.this, DeleteHabit_Comp.class);
                startActivity(intent);
                loadFromFile();
            }
        });

        oldHabits2 = (ListView) findViewById(R.id.listView3);
        oldHabits2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> l, View v, int position, long id) {
                // For debug:
                // Log.i("HelloListView", "You clicked Item: " + id + " at position:" + position);
                saveInFile(index2[position]);
                habit.clear();
                todo_habit.clear();
                completed_habit.clear();
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
        int l1=0,l2=0; // length of index1 and index2
        index1 = new int[100001];
        index2 = new int[100001];
        for(int i =0;i<habit.size();i++){
            if (habit.get(i).getComplete_times() == 0) {
                todo_habit.add(habit.get(i));
                index2[l2] = i;
                l2++;
            }
            else {
                completed_habit.add(habit.get(i));
                index1[l1] = i;
                l1++;
            }
        }

        adapter = new ArrayAdapter<Habit>(this,
                R.layout.list_item, completed_habit);
        oldHabits1.setAdapter(adapter);

        adapter = new ArrayAdapter<Habit>(this,
                R.layout.list_item, todo_habit);
        oldHabits2.setAdapter(adapter);
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
        habit.clear();
        todo_habit.clear();
        completed_habit.clear();
        adapter.notifyDataSetChanged();
        Intent intent = new Intent(this, AddHabit.class);
        startActivity(intent);
    }
}
