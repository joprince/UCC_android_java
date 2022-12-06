package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Timer extends AppCompatActivity {


    private Button button2;
    private Button button3;
    private Button button4;
    private TextView timertext;
    private TextView timertext1;
    private TextView timertext2;
    private Thread t;


    int number, number1, number2, x,y=0,a,b,c;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        timertext = findViewById(R.id.timertext);
        timertext1 = findViewById(R.id.timertext1);
        timertext2 = findViewById(R.id.timertext2);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });



        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timertext.setEnabled(false);
                timertext1.setEnabled(false);
                timertext2.setEnabled(false);
                number = Integer.parseInt(timertext.getText().toString());
                number1 = Integer.parseInt(timertext1.getText().toString());
                number2 = Integer.parseInt(timertext2.getText().toString());
                x = number2*3600+number1*60+number;
                a = number;
                b = number1;
                c = number2;

                t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (x>0 && y==0) {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            x--;
                            if (a==0 & b==0) {
                                number=60;
                                number1=59;
                                number2--;
                                a=1;
                                b=1;
                            }
                            if (a==0) {
                                number=60;
                                number1--;
                                a--;
                            }
                            number--;


                            Timer.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                   timertext.setText(""+number);
                                   timertext1.setText(""+number1);
                                   timertext2.setText(""+number2);
                                   if (number1==0 && number==0){
                                        number1=59;
                                    }
                                    if (number==0){
                                        number=60;
                                    }

                                }
                            });
                            }
                        if (y==0) {
                            Timer.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(Timer.this);
                                    builder.setTitle("Timer Done");

                                    builder.setPositiveButton("OK", null);

                                    AlertDialog dialog = builder.create();
                                    dialog.show();
                                    timertext.setEnabled(true);
                                    timertext1.setEnabled(true);
                                    timertext2.setEnabled(true);
                                }
                            });
                        }
                        if (y==1) {
                            Timer.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    timertext.setEnabled(true);
                                    timertext1.setEnabled(true);
                                    timertext2.setEnabled(true);
                                    number = 0;
                                    number1 = 0;
                                    number2 = 0;
                                    timertext.setText(""+number);
                                    timertext1.setText(""+number1);
                                    timertext2.setText(""+number2);
                                    y=0;
                                }
                            });
                        }
                    }

                });
                t.start();
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                y=1;
            }
        });
    }
}