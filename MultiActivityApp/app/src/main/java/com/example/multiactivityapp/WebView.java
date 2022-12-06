package com.example.multiactivityapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class WebView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        setTitle("World Traveller");
        getSupportActionBar().setSubtitle("News");
        android.webkit.WebView myWebView = (android.webkit.WebView) findViewById(R.id.myWebView);
        country country = (country) getIntent().getExtras().getSerializable("country");
        myWebView.loadUrl(country.getLink());
    }
}