package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView = null;
    private dataAdaptor adaptor = null;
    private String [] names = {"John", "Jane", "Jack", "Jeff", "Jenny"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //recycleView Settings
        recyclerView = findViewById((R.id.recycleView));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        // make the adaptor  and set it to recylerView
        adaptor = new dataAdaptor(this, R.layout.row_layout, names);
        recyclerView.setAdapter(adaptor);
    }
}