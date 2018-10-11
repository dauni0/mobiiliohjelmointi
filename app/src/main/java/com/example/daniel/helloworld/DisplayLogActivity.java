package com.example.daniel.helloworld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayLogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_log);

        //Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String stringLog = intent.getStringExtra(MainActivity.LOG_STRING);

        //Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.textView3);
        textView.setText(stringLog);
    }
}
