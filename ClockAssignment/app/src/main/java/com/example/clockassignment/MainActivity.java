package com.example.clockassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    ClockSurfaceView clock ;
    SurfaceView sv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clock = new ClockSurfaceView(this,300);

        //to get width and height of a screen
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        LinearLayout lin = (LinearLayout) findViewById(R.id.id_linear);
        lin.addView(clock);


    }

    @Override
    public void onResume() {
        super.onResume();
        clock.onResumeClock();

    }

    @Override
    public void onPause() {
        super.onPause();
        clock.onPauseClock();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.it_home) {

            startActivity(new Intent(this, Timer.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}