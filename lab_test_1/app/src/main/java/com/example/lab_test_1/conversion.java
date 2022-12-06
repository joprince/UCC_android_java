package com.example.lab_test_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class conversion extends AppCompatActivity {
    private EditText screen = null;
    String operation = "";
    String screenPreviousStr = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversion);

        Intent i = getIntent();
        TextView other = findViewById(R.id.textView4);
        String other_cur = i.getStringExtra("currency");
        other.setText(other_cur);
    }

    public void onClearClick(View v){
        // reset the calculator
        screen = findViewById(R.id.euro);
        screen.setText("");
        screen = findViewById(R.id.other);
        screen.setText("");

    }

    public void onConvertClick(View v){
        String euro = findViewById(R.id.euro).toString();
//        double amount = Double.parseDouble(str_amount);

        String other = findViewById(R.id.other).toString();
        Toast.makeText(this, "hello", Toast.LENGTH_LONG).show();
    }
}