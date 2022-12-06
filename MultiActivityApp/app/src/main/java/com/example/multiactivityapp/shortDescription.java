package com.example.multiactivityapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class shortDescription extends AppCompatActivity {

    private TextView name;
    private TextView captial;
    private TextView description;
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_short_description);
        country country = (country) getIntent().getExtras().getSerializable("country");

        setTitle("World Traveller");
        getSupportActionBar().setSubtitle("Quick glance");

        name = findViewById(R.id.textViewName1);
        name.setText(country.getName());

        captial = findViewById(R.id.textViewCapital1);
        captial.setText(country.getCapital());

        description = findViewById(R.id.textViewDesc1);
        description.setText(country.getDescription());

        img = findViewById(R.id.imageView1);
        img.setImageBitmap(ImageViaAssets(country.getImg()));

    }


    public void launchReadMore(View v){

        // launch activity
        country country = (country) getIntent().getExtras().getSerializable("country");
        Intent i = new Intent(this, ReadMore.class);
        i.putExtra("country", country);
        startActivity(i);
    }

    public Bitmap ImageViaAssets(String fileName){
        AssetManager assetmanager = getAssets();
        InputStream is = null;
        try{

            is = assetmanager.open(fileName);
        }catch(IOException e){
            e.printStackTrace();
        }
        Bitmap bitmap = BitmapFactory.decodeStream(is);
        return bitmap;
    }
}