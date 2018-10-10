package com.example.daniel.helloworld;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
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

        EditText substractionText1 = (EditText) findViewById(R.id.substraction1);
        String substractionNumber1 = substractionText1.getText().toString();
        Integer substraction_1 = Integer.parseInt(substractionNumber1);

        EditText substractionText2 = (EditText) findViewById(R.id.substraction2);
        String substractionNumber2 = substractionText2.getText().toString();
        Integer substraction_2 = Integer.parseInt(substractionNumber2);

        Integer substraction_result = substraction_1 - substraction_2;

        TextView substraction_result_text = findViewById(R.id.substractionResult);
        substraction_result_text.setText(substraction_result.toString());
    }

    public void calculateMultiplication(View view) {

        EditText multiplicationText1 = (EditText) findViewById(R.id.multiplication1);
        String multiplicationNumber1 = multiplicationText1.getText().toString();
        Integer multiplication_1 = Integer.parseInt(multiplicationNumber1);

        EditText multiplicationText2 = (EditText) findViewById(R.id.multiplication2);
        String multiplicationNumber2 = multiplicationText1.getText().toString();
        Integer multiplication_2 = Integer.parseInt(multiplicationNumber2);

        Integer multiplication_result = multiplication_1 * multiplication_2;

        TextView multiplication_result_text = findViewById(R.id.multiplicationResult);
        multiplication_result_text.setText(multiplication_result.toString());
    }

    public void calculateDivision(View view) {

        EditText divisionText1 = (EditText) findViewById(R.id.division1);
        String divisionNumber1 = divisionText1.getText().toString();
        Integer division_1 = Integer.parseInt(divisionNumber1);

        EditText divisionText2 = (EditText) findViewById(R.id.division2);
        String divisionNumber2 = divisionText2.getText().toString();
        Integer division_2 = Integer.parseInt(divisionNumber2);

        if (division_2 == 0) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setMessage("Nollalla ei voi jakaa!")
                    .setTitle("Huomio!");

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                }
            });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
        else {

            Float division_result = (float) division_1 / (float) division_2;

            TextView division_result_text = findViewById(R.id.divisionResult);
            division_result_text.setText(division_result.toString());
        }

    }

    public void cleanText(View view) {
        // Get all edittext and set to empty so they get default values, or to zero

        EditText additionText1 = findViewById(R.id.addition1);
        additionText1.setText("");

        EditText additionText2 = findViewById(R.id.addition2);
        additionText2.setText("");

        TextView addition_result_text = findViewById(R.id.additionResult);
        addition_result_text.setText("0");


        EditText substractionText1 = findViewById(R.id.substraction1);
        substractionText1.setText("");

        EditText substractionText2 = findViewById(R.id.substraction2);
        substractionText2.setText("");

        TextView substraction_result_text = findViewById(R.id.substractionResult);
        substraction_result_text.setText("0");


        EditText multiplicationText1 = findViewById(R.id.multiplication1);
        multiplicationText1.setText("");

        EditText multiplicationText2 = findViewById(R.id.multiplication2);
        multiplicationText2.setText("");

        TextView multiplication_result_text = findViewById(R.id.multiplicationResult);
        multiplication_result_text.setText("0");


        EditText divisionText1 = findViewById(R.id.division1);
        divisionText1.setText("");

        EditText divisionText2 = findViewById(R.id.division2);
        divisionText2.setText("");

        TextView division_result_text = findViewById(R.id.divisionResult);
        division_result_text.setText("0");
    }

    public void showLog(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

}
