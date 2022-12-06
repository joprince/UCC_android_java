package com.example.clockapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private  Button timerBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Clock");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerBtn = findViewById(R.id.timer_btn);
        timerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        MainActivity.this,
                        Timer.class
                );
                startActivity(intent);
            }
        });
    }
}