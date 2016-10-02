package com.example.idreamyc.chuan1_habittracker;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AddHabit extends Activity {

    private static final String FILENAME = "file.sav";
    private ArrayList<Habit> habit = new ArrayList<Habit>();
    CheckBox mon,tue,wed,thu,fri,sat,sun;
    private EditText Habit_name,date_text;
    private String[] weekdays = new String[8];
    boolean[] days_week={};
    Habit new_habit;
   // DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_habit);
        Button saveButton = (Button) findViewById(R.id.save);
        Button backButton = (Button) findViewById(R.id.back);

        date_text = (EditText)findViewById(R.id.editText);
        //http://stackoverflow.com/questions/2008558/displaying-a-default-date-in-a-edittext-widget
        SimpleDateFormat sdf = new SimpleDateFormat( "yyyy/MM/dd" );
        date_text.setText(sdf.format( new Date() ));
        /*     initial idea of date part
        try {
            String date_form = date_text.getText().toString();
            date = formatter.parse(date_form);
            String date_output = new SimpleDateFormat("dd/MM/yyyy").format(date_form);
        }catch{

        }
        */

        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                setResult(RESULT_OK);
                Habit_name = (EditText)findViewById(R.id.body);
                mon = ((CheckBox)findViewById(R.id.checkBox));
                tue = ((CheckBox)findViewById(R.id.checkBox2));
                wed = ((CheckBox)findViewById(R.id.checkBox3));
                thu = ((CheckBox)findViewById(R.id.checkBox4));
                fri = ((CheckBox)findViewById(R.id.checkBox5));
                sat = ((CheckBox)findViewById(R.id.checkBox6));
                sun = ((CheckBox)findViewById(R.id.checkBox7));
                boolean[] days_week = new boolean[]{mon.isChecked(),tue.isChecked(),wed.isChecked(),thu.isChecked(),fri.isChecked(),sat.isChecked(),sun.isChecked()};
                String[] week = {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
                // Check which days in the week are checked
                int l=0;
                for (int i = 0; i < 7; i++){
                    if (days_week[i]) {
                        weekdays[l] = week[i];
                        l++;
                    }
                }
                //http://stackoverflow.com/questions/20231539/java-check-the-date-format-of-current-string-is-according-to-required-format-or
                Date date = null;
                date_text = (EditText)findViewById(R.id.editText);

                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                    date = sdf.parse(date_text.getText().toString());
                    if (!date_text.getText().toString().equals(sdf.format(date))) {
                        //new_habit.print(date_text.getText().toString());
                        date = null;
                    }

                }catch(ParseException e) {
                    e.printStackTrace();
                }
                // Check if the input date form if valid
                if (date==null)
                  new_habit = new Habit(Habit_name.getText().toString(),weekdays,0, new ArrayList<Date>());
                else
                    new_habit = new Habit(date_text.getText().toString(),Habit_name.getText().toString(),weekdays,0, new ArrayList<Date>());
                loadFromFile();
                habit.add(new_habit);
                saveInFile();
                finish();
            }
        });
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
