package com.example.idreamyc.chuan1_habittracker;

import android.app.Activity;
import android.app.Application;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ApplicationTestCase;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ActivityInstrumentationTestCase2 {
    public ApplicationTest() {
        super(com.example.idreamyc.chuan1_habittracker.MainActivity.class);
    }
    public void testStart() throws Exception {
        Activity activity = getActivity();
    }
}