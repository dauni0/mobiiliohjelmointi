package com.example.daniel.helloworld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.daniel.helloworld.MESSAGE";
    public static final String LOG_STRING="com.example.daniel.helloworld.LOG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** Called when the user taps the Send Button */
    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    /** Calculate two numbers */
    public void calculateAddition(View view) {
        // Get two numbers
        // Add them together
        // Show result in textview

        EditText additionText1 = (EditText) findViewById(R.id.addition1);
        String additionNumber1 = additionText1.getText().toString();
        Integer addition_1 = Integer.parseInt(additionNumber1);

        EditText additionText2 = (EditText) findViewById(R.id.addition2);
        String additionNumber2 = additionText2.getText().toString();
        Integer addition_2 = Integer.parseInt(additionNumber2);

        Integer addition_result = addition_1 + addition_2;

        TextView addition_result_text = findViewById(R.id.additionResult);
        addition_result_text.setText(addition_result.toString());

        //Add result to log string

    }

    public void calculateSubstraction(View view) {

    }

    public void calculateMultiplication(View view) {

    }

    public void calculateDivision(View view) {

    }

    public void showLog(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}
