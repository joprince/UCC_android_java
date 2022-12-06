package com.example.multiactivityapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import android.os.Bundle;



public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView = null;
    private dataAdaptor adaptor = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ArrayList<country> countries = null;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("World Traveller");

//      recycleView Settings
        recyclerView = findViewById((R.id.recycleView));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        try {
            XMLParser parser = new XMLParser();
            InputStream is=getAssets().open("countries.xml");
            countries = parser.parse(is);

        } catch (IOException e) {e.printStackTrace();}

//     make the adaptor  and set it to recylerView
        adaptor = new dataAdaptor(this, R.layout.row_layout, countries);
        recyclerView.setAdapter(adaptor);
    }

}
