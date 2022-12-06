package com.example.testapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.widget.Toast;

public class LaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        Intent i = getIntent();
        String message = i.getStringExtra("key");
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        Log.d("info", "here");
        WebView myWebView = (WebView) findViewById(R.id.myWebView);
        myWebView.loadUrl("http://www.google.com");
    }
}