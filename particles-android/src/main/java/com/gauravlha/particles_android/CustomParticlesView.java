package com.gauravlha.particles_android;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CustomParticlesView extends View {

    private int frameRate;
    private int numberOfBalls;

    private int xVelocityLowerLimit;
    private int xVelocityUpperLimit;
    private int yVelocityLowerLimit;
    private int yVelocityUpperLimit;

    private int paintAlphaLowerLimit;
    private int paintAlphaUpperLimit;

    private int ballSizeLowerLimit;
    private int ballSizeUpperLimit;

    private int lineXCoordinateDistance;
    private int lineYCoordinateDistance;

    private int circleColor;
    private int lineColor;


    private List<Integer> x;
    private List<Integer> y;
    private List<Paint> paint;
    private List<Integer> paintAlpha;
    private List<Integer> ballSize;
    private List<Integer> xVelocity;
    private List<Integer> yVelocity;
    private List<Integer> randomXCoordinate;
    private List<Integer> randomYCoordinate;
    private Context mContext;
    private Handler h;
    private Random random;
    private Runnable r = new Runnable() {
        @Override
        public void run() {
            invalidate();
        }
    };

    public CustomParticlesView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        h = new Handler();

        paint = new ArrayList<>();
        paintAlpha = new ArrayList<>();
        ballSize = new ArrayList<>();
        x = new ArrayList<>();
        y = new ArrayList<>();
        xVelocity = new ArrayList<>();
        yVelocity = new ArrayList<>();
        randomXCoordinate = new ArrayList<>();
        randomYCoordinate = new ArrayList<>();
        random = new Random();

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CustomParticlesView, 0, 0);
        try {
            frameRate = ta.getInteger(R.styleable.CustomParticlesView_frameRate, 5);
            numberOfBalls = ta.getInteger(R.styleable.CustomParticlesView_numberOfBalls, 50);
            xVelocityLowerLimit = ta.getInteger(R.styleable.CustomParticlesView_xVelocityLowerLimit, 1);
            xVelocityUpperLimit = ta.getInteger(R.styleable.CustomParticlesView_xVelocityUpperLimit, 5);
            yVelocityLowerLimit = ta.getInteger(R.styleable.CustomParticlesView_yVelocityLowerLimit, 1);
            yVelocityUpperLimit = ta.getInteger(R.styleable.CustomParticlesView_yVelocityUpperLimit, 5);
            paintAlphaLowerLimit = ta.getInteger(R.styleable.CustomParticlesView_paintAlphaLowerLimit, 100);
            paintAlphaUpperLimit = ta.getInteger(R.styleable.CustomParticlesView_paintAlphaUpperLimit, 255);
            ballSizeLowerLimit = ta.getInteger(R.styleable.CustomParticlesView_ballSizeLowerLimit, 5);
            ballSizeUpperLimit = ta.getInteger(R.styleable.CustomParticlesView_ballSizeUpperLimit, 10);
            lineXCoordinateDistance = ta.getInteger(R.styleable.CustomParticlesView_lineXCoordinateDistance, 100);
            lineYCoordinateDistance = ta.getInteger(R.styleable.CustomParticlesView_lineYCoordinateDistance, 20);
            circleColor = ta.getColor(R.styleable.CustomParticlesView_circleColorName, getResources().getColor(android.R.color.black));
            lineColor = ta.getColor(R.styleable.CustomParticlesView_lineColorName, getResources().getColor(android.R.color.holo_green_dark));

        } finally {
            ta.recycle();
        }

        for (int i = 0; i < numberOfBalls; i++) {
            x.add(-1);
            y.add(-1);
            xVelocity.add(random.nextInt(xVelocityUpperLimit - xVelocityLowerLimit) + xVelocityLowerLimit);
            yVelocity.add(random.nextInt(yVelocityUpperLimit - yVelocityLowerLimit) + yVelocityLowerLimit);
            paint.add(new Paint());
            paintAlpha.add(random.nextInt(paintAlphaUpperLimit - paintAlphaLowerLimit) + paintAlphaLowerLimit);
            ballSize.add(random.nextInt(ballSizeUpperLimit - ballSizeLowerLimit) + ballSizeLowerLimit);
            randomXCoordinate.add(random.nextBoolean() ? 1: -1);
            randomYCoordinate.add(random.nextBoolean() ? 1: -1);
        }


    }

    protected void onDraw(Canvas c) {

        for (int i = 0; i < numberOfBalls; i++) {
            if (x.get(i) < 0 && y.get(i) < 0) {
                x.set(i, random.nextInt(this.getWidth()));
                y.set(i, random.nextInt(this.getHeight()));
            } else {
                x.set(i, x.get(i) + (xVelocity.get(i)) * randomXCoordinate.get(i));
                y.set(i, y.get(i) + (yVelocity.get(i)) * randomYCoordinate.get(i));
                if ((x.get(i) > this.getWidth() - ballSize.get(i)) || (x.get(i) < 0)) {
                    xVelocity.set(i, xVelocity.get(i) * -1);
                }
                if ((y.get(i) > this.getHeight() - ballSize.get(i)) || (y.get(i) < 0)) {
                    yVelocity.set(i, yVelocity.get(i) * -1);
                }

            }

            paint.get(i).setStyle(Paint.Style.FILL);
            paint.get(i).setColor(Color.WHITE);
            paint.get(i).setAlpha(paintAlpha.get(i));
            c.drawPaint(paint.get(i));


        }

        for (int i = 0; i < numberOfBalls; i++) {
            paint.get(i).setColor(circleColor);
            c.drawCircle(x.get(i), y.get(i), ballSize.get(i), paint.get(i));
            for (int j = 0; j < numberOfBalls; j++) {
                int valueX = Math.abs(x.get(i) - x.get(j));
                int valueY = Math.abs(y.get(i) - y.get(j));
                if (valueX < lineXCoordinateDistance || (valueY < lineYCoordinateDistance && valueX < lineXCoordinateDistance)) {
                    paint.get(i).setColor(lineColor);
                    c.drawLine(x.get(i), y.get(i), x.get(j), y.get(j), paint.get(i));
                }
            }

        }


        h.postDelayed(r, frameRate);
    }
}