package com.example.geetika_luthra.mydiary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class About extends AppCompatActivity {
    private TextView about;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        about=(TextView)findViewById(R.id.text2);
        about.setText("This is a Diary Writing App.You can hide your diary entries with a secure lock");

    }
}
