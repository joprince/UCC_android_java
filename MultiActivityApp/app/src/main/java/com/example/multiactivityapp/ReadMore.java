package com.example.multiactivityapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class ReadMore extends AppCompatActivity {

    private TextView name;
    private TextView captial;
    private TextView description;
    private TextView region;
    private TextView currencySymbol;
    private TextView flag;
    private TextView location;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_more);

        country country = (country) getIntent().getExtras().getSerializable("country");

        setTitle("World Traveller");
        getSupportActionBar().setSubtitle(country.getName());


        name = findViewById(R.id.textViewName2);
        name.setText(country.getName());

        captial = findViewById(R.id.textViewCapital2);
        captial.setText(country.getCapital());

        description = findViewById(R.id.textViewDesc2);
        description.setText(country.getDescription());

        region = findViewById(R.id.textViewRegion);
        region.setText(country.getRegion());

        flag = findViewById((R.id.textViewFlag2));
        flag.setText(country.getEmoji());

        currencySymbol = findViewById(R.id.textViewCurrency2);
        currencySymbol.setText(country.getCurrencySymbol());

        location = findViewById(R.id.textViewCoordinates2);
        location.setText(country.getLatitude()+", "+country.getLongitude());

    }
    public void launchNews (View v){
        country country = (country) getIntent().getExtras().getSerializable("country");
        String url = country.getLink();
        Intent i = new Intent(this, WebView.class);
        i.putExtra("country", country);
        startActivity(i);
    }

    public Bitmap ImageViaAssets(String fileName){

        AssetManager assetmanager = getAssets();
        InputStream is = null;
        try {

            is = assetmanager.open(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Bitmap bitmap = BitmapFactory.decodeStream(is);
        return bitmap;
    }

}
