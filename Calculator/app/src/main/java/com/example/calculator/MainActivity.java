package com.example.calculator;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    private EditText screen = null;
    String operation = "";
    String screenPreviousStr = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        screen = findViewById(R.id.editText);
    }

    public void onDigitClick(View v){
        //add the v label to the screen content
        String label = ((Button)v).getText().toString();
        String screenStr = screen.getText().toString();
        screen.setText(screenStr+label);
    }

    public void onClearClick(View v){
        // reset the calculator
        screen.setText("");
        operation = "";
        screenPreviousStr = "";
    }

    public void onOperationClick(View v){
        // save the operation, current screen and reset the screen
        operation = ((Button)v).getText().toString();
        screenPreviousStr = screen.getText().toString();
        screen.setText("");
    }

    public void onEqualClick(View v){
        // get the values to work with

        double preValue = Double.parseDouble(screenPreviousStr);
        double curValue = Double.parseDouble(screen.getText().toString());
        double result;
        // check operation
        switch (operation){
            case "+":
                result = preValue + curValue;
                screen.setText(""+result);
                break;
            case "-":
                result = preValue - curValue;
                screen.setText(""+result);
                break;
            case "*":
                result = preValue * curValue;
                screen.setText(""+result);
                break;
            case "/":
                if (curValue == 0){
                    screen.setText("Error");
                }
                else{
                    result = preValue + curValue;
                    screen.setText(""+result);
                }
                break;

        }
    }
}