package com.example.clockapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.time.Duration;

public class Timer extends AppCompatActivity {

    private Chronometer chronometer;
    private  long startTime = 0;
    private boolean isPlaying = false;
    private ToggleButton toggleButton;
    private Button resetBtn;
    private Button clockBtn;
    private EditText hours;
    private EditText minutes;
    private EditText seconds;
    private TextView txtHours;
    private TextView txtMinutes;
    private TextView txtSeconds;
    private MediaPlayer beep;
    private boolean flag = false;
    private long startedRealtime;
    private long PauseOffSet;


    private void setVisibility(boolean b){
        if(b){
            hours.setVisibility(View.INVISIBLE);
            txtHours.setVisibility((View.INVISIBLE));
            minutes.setVisibility(View.INVISIBLE);
            txtMinutes.setVisibility(View.INVISIBLE);
            seconds.setVisibility(View.INVISIBLE);
            txtSeconds.setVisibility(View.INVISIBLE);
            chronometer.setVisibility(View.VISIBLE);
        }
        else {
            hours.setVisibility(View.VISIBLE);
            txtHours.setVisibility((View.VISIBLE));
            minutes.setVisibility(View.VISIBLE);
            txtMinutes.setVisibility(View.VISIBLE);
            seconds.setVisibility(View.VISIBLE);
            txtSeconds.setVisibility(View.VISIBLE);
            chronometer.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        setTitle("Timer");
        chronometer = findViewById(R.id.chronometer);
        chronometer.setCountDown(true);
        chronometer.setVisibility(View.INVISIBLE);
        chronometer.setTextColor(Color.BLACK);

        toggleButton = findViewById(R.id.Toggle);
        resetBtn = findViewById(R.id.reset_btn);
        clockBtn = findViewById(R.id.clock_btn);
        hours = findViewById(R.id.hours);
        minutes = findViewById(R.id.minutes);
        seconds = findViewById(R.id.seconds);
        txtHours = findViewById(R.id.txt_hours);
        txtMinutes = findViewById(R.id.txt_minutes);
        txtSeconds = findViewById(R.id.txt_seconds);
        toggleButton.setText(null);
        toggleButton.setTextOn(null);
        toggleButton.setTextOff(null);

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b){
            if(startTime == 0){
                startTime += Integer.parseInt(seconds.getText().toString())*1000;
                startTime += Integer.parseInt(minutes.getText().toString())*60*1000;
                startTime += Integer.parseInt(hours.getText().toString())*3600*1000;
                setVisibility(true);
                startedRealtime = SystemClock.elapsedRealtime();
                chronometer.setBase(startedRealtime+startTime);
                chronometer.start();
            }
            else if(b){
                chronometer.setBase(SystemClock.elapsedRealtime() - PauseOffSet);
                chronometer.start();
                isPlaying = true;
            }else{
                chronometer.stop();
                PauseOffSet =SystemClock.elapsedRealtime() - chronometer.getBase();
                startedRealtime = chronometer.getBase();
                if(startTime<PauseOffSet)
                {
                    setVisibility(false);
                }
                isPlaying = false;
            }
        }
        });

        resetBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                chronometer.stop();
                setVisibility(false);
                isPlaying = true;
                toggleButton.setChecked(false);
                startTime = 0;
            }
        });
        clockBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        Timer.this,
                        MainActivity.class
                );
                startActivity(intent);
            }
        });

        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                if ((chronometer.getText().toString().equals("00:00")))
                {
                    if(flag==false){
                        flag=true;
                    }
                    else {
                        flag=false;
                        toggleButton.setChecked(false);
                        chronometer.stop();
                        Toast.makeText(getApplicationContext(), "Time's up!!", Toast.LENGTH_LONG).show();
                        beep = MediaPlayer.create(getApplicationContext(), R.raw.beep);
                        beep.start();
                        startTime = 0;
                        setVisibility(false);
                    }
                }
            }
        });
    }
}