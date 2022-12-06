package com.example.clockapp;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Date;
import java.util.GregorianCalendar;

public class Clock extends SurfaceView implements SurfaceHolder.Callback {

    public static final int DEGREE_PER_MINUTE = 6;
    public static final int DEGREE_PER_SECOND = 6;
    public static final int HAND_TAIL_LENGTH = 25;

    private SurfaceHolder holder;
    private SurfaceViewThread mSurfaceViewThread;
    private boolean hasSurface;


    private GregorianCalendar mCalendar;
    private float mSeconds;
    private float mMinutes;
    private float mHour;

    private boolean mAttached;

    private int clockFormat = 12;
    private int angle = 360 / clockFormat;

    private Paint markerPaint;
    private Paint secondMarkerPaint;
    private Paint textPaint;
    private Paint circlePaint;
    private Paint mHourHandPaint;
    private Paint mMinuteHandPaint;
    private Paint mSecondHandPaint;


    private int textHeight;

    public Clock(Context context) {
        super(context);
        if (!isInEditMode()) {
            init();
        }
    }

    public Clock(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode()) {
            init();
        }

    }

    public Clock(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (!isInEditMode()) {
            init();
        }
    }

    public void setClockFormat(int clockFormat) {
        this.clockFormat = clockFormat;
        angle = 360 / clockFormat;
    }

    private void init() {
        holder = getHolder();
        holder.addCallback(this);
        hasSurface = false;
        setZOrderOnTop(true);
        holder.setFormat(PixelFormat.TRANSLUCENT);

        Resources r = this.getResources();

        circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        circlePaint.setColor(r.getColor(R.color.background_color));
        circlePaint.setStrokeWidth(1);
        circlePaint.setStyle(Paint.Style.FILL_AND_STROKE);

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(r.getColor(R.color.text_color));

        textHeight = 50;
        textPaint.setTextSize(textHeight);

        markerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        markerPaint.setColor(r.getColor(R.color.marker_color));
        markerPaint.setStrokeWidth(5);
        secondMarkerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        secondMarkerPaint.setColor(r.getColor(R.color.second_marker_color));
        secondMarkerPaint.setStrokeWidth(5);

        mHourHandPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mHourHandPaint.setStrokeWidth(9);
        mHourHandPaint.setColor(r.getColor(R.color.text_color));

        mMinuteHandPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mMinuteHandPaint.setStrokeWidth(5);
        mMinuteHandPaint.setColor(r.getColor(R.color.text_color));

        mSecondHandPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mSecondHandPaint.setStrokeWidth(3);
        mSecondHandPaint.setColor(r.getColor(R.color.secondHandColor));

    }

    @Override
    protected void onMeasure(int wMeasureSpec, int hMeasureSpec) {
        int measureHeight = measure(hMeasureSpec);
        int measureWidth = measure(wMeasureSpec);

        int d = Math.min(measureHeight, measureWidth);

        setMeasuredDimension(d, d);
    }

    private int measure(int measureSpec) {
        int result = 0;

        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.UNSPECIFIED) {
            result = 200;
        } else {
            result = specSize;
        }

        return result;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        if (!mAttached) {
            mAttached = true;
            IntentFilter filter = new IntentFilter();

            filter.addAction(Intent.ACTION_TIME_TICK);
            filter.addAction(Intent.ACTION_TIME_CHANGED);
            filter.addAction(Intent.ACTION_TIMEZONE_CHANGED);

        }

        mCalendar = new GregorianCalendar();

        onTimeChanged();

        post(mClockTick);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mAttached) {
            mAttached = false;
        }
    }

    public void resume() {

        if (mSurfaceViewThread == null) {
            mSurfaceViewThread = new SurfaceViewThread();

            if (hasSurface) {
                mSurfaceViewThread.start();
            }
        }
    }

    public void pause() {

        if (mSurfaceViewThread != null) {
            mSurfaceViewThread.requestExitAndWait();
            mSurfaceViewThread = null;
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        hasSurface = true;
        if (mSurfaceViewThread != null) {
            mSurfaceViewThread.start();
        } else {
            resume();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        if (mSurfaceViewThread != null) {
            mSurfaceViewThread.onWindowResize(width, height);
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        hasSurface = false;
        pause();
    }

    class SurfaceViewThread extends Thread {
        private boolean done;

        SurfaceViewThread() {
            super();
            done = false;
        }

        @Override
        public void run() {
            SurfaceHolder surfaceHolder = holder;

            while (!done) {
                Canvas canvas = null;
                try {
                    canvas = surfaceHolder.lockCanvas();
                    if (canvas != null) {
                        int measureWidth = getMeasuredWidth();
                        int measureHeight = getMeasuredHeight();

                        int px = measureWidth / 2;
                        int py = measureHeight / 2;

                        int radius = Math.min(px, py);

                        canvas.drawCircle(px, py, radius, circlePaint);

                        canvas.save();



                        for (int i = 0; i < 60; i++) {
                            canvas.drawLine(px, py - radius, px, py - radius + 20, secondMarkerPaint);
                            canvas.rotate(6, px, py);
                        }

                        for (int i = 0; i < clockFormat; i++) {

                            canvas.drawLine(px, py - radius, px, py - radius + 30, markerPaint);

                            canvas.rotate(30, px, py);
                        }

                        for (int i = 1; i <= clockFormat; i++) {
                            int textWidth = (int) textPaint.measureText(String.valueOf(i));

                            int dx = (int) ((radius - 50) * Math.sin(Math.toRadians(i * angle)));
                            int dy = (int) ((radius - 50) * Math.cos(Math.toRadians(i * angle)));

                            int cardinalX = -textWidth / 3;

                            canvas.drawText(String.valueOf(i), px + dx + cardinalX,
                                    py - dy + textHeight / 3,
                                    textPaint);
                        }

                        int dx = (int) ((radius / 3) * Math
                                .sin(Math.toRadians(mHour * angle + (angle * mMinutes / 60))));
                        int dy = (int) ((radius / 3) * Math
                                .cos(Math.toRadians(mHour * angle + (angle * mMinutes / 60))));
                        canvas.drawLine(
                                (float) (px - HAND_TAIL_LENGTH * Math
                                        .sin(Math.toRadians(
                                                mHour * angle + (angle * mMinutes / 60)))),
                                (float) (py + HAND_TAIL_LENGTH * Math
                                        .cos(Math.toRadians(
                                                mHour * angle + (angle * mMinutes / 60)))),
                                px + dx, py - dy, mHourHandPaint);


                        dx = (int) ((radius / 1.5) * Math
                                .sin(Math.toRadians(mMinutes * DEGREE_PER_MINUTE)));
                        dy = (int) ((radius / 1.5) * Math
                                .cos(Math.toRadians(mMinutes * DEGREE_PER_MINUTE)));

                        canvas.drawLine((float) (px - HAND_TAIL_LENGTH * Math
                                        .sin(Math.toRadians(mMinutes * DEGREE_PER_MINUTE))),
                                (float) (py + HAND_TAIL_LENGTH * Math
                                        .cos(Math.toRadians(mMinutes * DEGREE_PER_MINUTE))),
                                px + dx,
                                py - dy, mMinuteHandPaint);


                        dx = (int) ((radius / 1.2) * Math
                                .sin(Math.toRadians(mSeconds * DEGREE_PER_SECOND)));
                        dy = (int) ((radius / 1.2) * Math
                                .cos(Math.toRadians(mSeconds * DEGREE_PER_SECOND)));

                        canvas.drawLine((float) (px - 2 * HAND_TAIL_LENGTH * Math
                                        .sin(Math.toRadians(mSeconds * DEGREE_PER_SECOND))),
                                (float) (py + 2 * HAND_TAIL_LENGTH * Math.cos(
                                        Math.toRadians(mSeconds * DEGREE_PER_SECOND))), px + dx,
                                py - dy,
                                mSecondHandPaint);
                    }
                }
                finally {
                    if (canvas != null) {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }
                }
            }
        }

        public void requestExitAndWait() {

            done = true;
            try {
                join();
            } catch (InterruptedException ex) {

            }
        }

        public void onWindowResize(int w, int h) {
        }
    }

    private void onTimeChanged() {
        mCalendar.setTime(new Date());

        int hour = mCalendar.getTime().getHours();
        int minute =  mCalendar.getTime().getMinutes();
        int second =  mCalendar.getTime().getSeconds();

        mSeconds = second;
        mMinutes = minute;
        mHour = hour;
    }

    private final Runnable mClockTick = new Runnable () {

        @Override
        public void run() {
            onTimeChanged();
            Clock.this.postDelayed(mClockTick, 1000);
        }
    };

}
