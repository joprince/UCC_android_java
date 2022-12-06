package com.example.multiaction;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DetailsActivity extends AppCompatActivity {

    private TextView nameTV = null;
    private Person data = null;
    private Button webinfoBT = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();
        data = (Person)intent.getExtras().getSerializable(
                "data"
        );

        nameTV = findViewById(R.id.textView3);
        nameTV.setText(data.getName());

        webinfoBT = findViewById(R.id.webinfoBT);
        webinfoBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // create and load intent
                gotoUrl(data.getUrl());
            }
        });
    }

    private void gotoUrl(String url) {
        try {
            Uri uri = Uri.parse(url);
            startActivity(new Intent(Intent.ACTION_VIEW, uri));
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "Browser not found", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
}