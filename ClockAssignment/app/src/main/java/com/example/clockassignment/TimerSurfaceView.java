package com.example.clockassignment;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Calendar;

public class TimerSurfaceView extends SurfaceView implements Runnable {

    private float length;
    private Thread thread;
    private boolean running = false;
    private SurfaceHolder holder;

    Calendar calendar = Calendar.getInstance();
    int sec = 0;
    int hour = calendar.get(Calendar.HOUR);
    int min = calendar.get(Calendar.MINUTE);
    int targetSecs ;


    public void onResumeClock(){
        thread = new Thread(this);
        thread.start();
        running = true;

    }

    public TimerSurfaceView(Context context, float length, int targetsecs) {
        super(context);
        this.length = length;
        this.holder = getHolder();
        this.targetSecs = targetsecs;

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

                canvas.drawText("Analog Timer",getWidth()/2,300,paint);

                paint.setStyle(Paint.Style.FILL);
                paint.setColor(Color.parseColor("#CD5C5C"));
                paint.setAlpha(120);
                canvas.drawCircle((getWidth()/2), (getHeight()/2), length+50, paint);



                paint.setColor(Color.BLACK);
                paint.setAlpha(200);
                RegPoly text = new RegPoly(12,getWidth()/2,getHeight()/2,length-20,canvas,paint);
                text.drawText();

                paint.setColor(Color.parseColor("#CD5C5C"));
                paint.setStrokeWidth(5);
                RegPoly secHand = new RegPoly(60,getWidth()/2,getHeight()/2,length-75,canvas,paint);
                secHand.drawRadius(sec+45);

                holder.unlockCanvasAndPost(canvas);
            }

            try {
                Thread.sleep(1000);
                sec++;
                if (sec == (targetSecs+1))
                {
                    running = false;
                }
            }catch (Exception e) {};
        }

    }
}