package com.example.lab_test_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView = null;
    private dataAdaptor adaptor = null;
    private String [] currency = {"USD", "GBP", "JPY", "AUD", "CHF"};
    private String [] rates = {"0.97692635", "0.87529628", "147.03323921", "1.56026155", "0.98476361"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //recycleView Settings
        recyclerView = findViewById((R.id.recycleView));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        // make the adaptor  and set it to recylerView
        adaptor = new dataAdaptor(this, R.layout.row_layout, currency, rates);
        recyclerView.setAdapter(adaptor);
    }

    public void conversionActiviy(View v){
        // launch activity

        Intent i = new Intent(this, conversion.class);

        String currency = ((TextView)findViewById(R.id.textView1)).getText().toString();
        String currency_rate = ((TextView)findViewById(R.id.textView2)).getText().toString();
        i.putExtra("currency", currency);
        i.putExtra("currency_rate", currency_rate);
        startActivity(i);
    }
}