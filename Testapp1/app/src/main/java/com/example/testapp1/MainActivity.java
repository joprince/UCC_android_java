package com.example.testapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("fail", "Inside on create");
        Button b1 = findViewById(R.id.button3);
        b1.setText("Pick me");
        Button b2 = findViewById(R.id.button4);
        b2.setText("Pick me");

        // this is how to change title without changing the app name
        // only for main activity
        setTitle("Home");

        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.spinner, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

    }

    public void disable(View v){
        int id = v.getId();
        v.setEnabled(false);
        Log.d("Success", "Button: "+ id + " disabled");

        Button btn_obj = (Button) v;
        btn_obj.setText("Disabled");
        Button b1 = findViewById(R.id.button3);
        Button b2 = findViewById(R.id.button4);

        if (btn_obj == b1){
            b2.setEnabled(true);
            b2.setText("I'm active");
        }
        else{
            b1.setEnabled(true);
            b1.setText("I'm active");
        }
    }

    public void handleText(View v){
        EditText t = findViewById(R.id.source);
        String input = t.getText().toString();

        ((TextView)findViewById(R.id.output)).setText(input);
        Log.d("info", input);
        Toast.makeText(this, input, Toast.LENGTH_LONG).show();
    }

    public void launchActivity(View v){
        // launch activity

        Intent i = new Intent(this, LaunchActivity.class);

        String message = ((EditText)findViewById(R.id.source)).getText().toString();
        i.putExtra("key", message);
        startActivity(i);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String choice = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(getApplicationContext(), choice, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}