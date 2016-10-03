package com.example.idreamyc.chuan1_habittracker;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by idrea on 2016/10/2.
 */
public class HabitTest extends ApplicationTest{

    public void test(){
        ArrayList<Habit> Habit = new ArrayList<Habit>();
        ArrayList<Date> date = new ArrayList<Date>();
        date.add(new Date());
        Habit habit = new Habit("aaaaaa",new String[]{"Monday"},0,date);
        Habit.add(habit);

        assertEquals(habit, Habit.get(0));

    }
}
