package com.example.clockassignment;

import static android.content.ContentValues.TAG;

import static java.lang.Math.abs;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.nfc.Tag;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.Arrays;
import java.util.Calendar;

public class ClockSurfaceView extends SurfaceView implements Runnable {

    private float length;
    private Thread thread;
    private boolean running = false;
    private SurfaceHolder holder;

    Calendar calendar = Calendar.getInstance();
    int sec = 0;
    int hour = calendar.get(Calendar.HOUR);
    int min = calendar.get(Calendar.MINUTE);
    int mil = 0;


    public void onResumeClock(){
        thread = new Thread(this);
        thread.start();
        running = true;

    }

    public ClockSurfaceView(Context context, float length) {
        super(context);
        this.length = length;
        this.holder = getHolder();

    }

    public  void onPauseClock(){

        running = false;
        boolean reentry = true;
        while (reentry) {
            try {
                thread.join();
                reentry = false;
            } catch (Exception e) {
            }
        }
    }
    @Override
    public void run() {
        while(running)
        {
            if(holder.getSurface().isValid()) {


                Canvas canvas = holder.lockCanvas();
                Paint paint = new Paint();
                paint.setColor(Color.LTGRAY);
                canvas.drawPaint(paint);

                paint.setColor(Color.BLACK);
                paint.setAlpha(200);
                paint.setTextSize(100);
                paint.setTextAlign(Paint.Align.CENTER);

                canvas.drawText("Analog Clock",getWidth()/2,200,paint);


                paint.setStyle(Paint.Style.FILL);
                paint.setColor(Color.parseColor("#CD5C5C"));
                paint.setAlpha(120);
                canvas.drawCircle((getWidth()/2), (getHeight()/2), length+50, paint);

                paint.setColor(Color.BLACK);
                paint.setAlpha(200);
                RegPoly text = new RegPoly(12,getWidth()/2,getHeight()/2,length-20,canvas,paint);
                text.drawText();

                paint.setColor(Color.BLACK);
                paint.setStrokeWidth(15);
                RegPoly minHand = new RegPoly(60,getWidth()/2,getHeight()/2,length-100,canvas,paint);
                RegPoly hourHand = new RegPoly(12,getWidth()/2,getHeight()/2,length-155,canvas,paint);

                Log.d(TAG,String.valueOf(min));
                Log.d(TAG,String.valueOf(hour));
                minHand.drawRadius(min+45);
                hourHand.drawRadius(hour+45);

                paint.setColor(Color.parseColor("#CD5C5C"));
                paint.setStrokeWidth(5);
                RegPoly secHand = new RegPoly(60,getWidth()/2,getHeight()/2,length-75,canvas,paint);
                secHand.drawRadius(sec+45);

                holder.unlockCanvasAndPost(canvas);
            }

            try {
                Thread.sleep(1000);
                sec++;
                if (sec%60 == 0)
                {
                    min++;
                    if( min%60 == 0 ){
                        hour++;
                    }
                }
            }catch (Exception e) {};
        }
    }
}