package com.example.clockassignment;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;

public class RegPoly {
    // ivars
    private int n;
    private float x0, y0, r;
    private float x[], y[];
    char text[];

    private Canvas canvas; private Paint paint;

    // constructor
    public RegPoly(int n, float x0, float y0, float r, Canvas canvas, Paint paint) {
        this.n = n;
        this.x0 = x0; this.y0 = y0;
        this.r = r;

        this.canvas = canvas;
        this.paint = paint;

        // calculate x[] and y[]
        this.x = new float[this.n];this.y = new float[this.n];

        for(int i=0;i<n;i++){
            this.x[i] = (float) (x0 + r * Math.cos(2*Math.PI*i/n));
            this.y[i] = (float) (y0 + r * Math.sin(2*Math.PI*i/n));
        }
    }

    // getters
    public float getX(int i){return this.x[i%this.n];}
    public float getY(int i){return this.y[i%this.n];}

    // draw-ers
    public void drawRadius(int i){
        this.canvas.drawLine(this.x0, this.y0, this.getX(i), this.getY(i),this.paint);
    }

    public void drawNodes(){
        for(int i=0;i<this.n;i++){
            this.canvas.drawCircle(this.getX(i), this.getY(i), 10, this.paint);
        }
    }
    public void drawText(){
        paint.setTextSize(44);
        for(int i=1;i<=this.n;i++){
            this.canvas.drawText(String.valueOf(i),this.getX(i+45), this.getY(i+45), paint);
        }
    }

    public void drawArc(int i){
        paint.setColor(Color.YELLOW);
        paint.setDither(true);                    // set the dither to true
        paint.setStyle(Paint.Style.STROKE);       // set to STOKE
        paint.setStrokeJoin(Paint.Join.ROUND);    // set the join to round you want
        paint.setStrokeCap(Paint.Cap.ROUND);      // set the paint cap to round too
        paint.setPathEffect(new CornerPathEffect(50) );   // set the path effect when they join.
        paint.setAntiAlias(true);
        this.canvas.drawArc(new RectF(this.getX(i+45) - r , this.getY(i+45)+r , this.getX(i+45)+ r,this.getY(i+45) - r),
                x0,y0,false,paint);

    }



}