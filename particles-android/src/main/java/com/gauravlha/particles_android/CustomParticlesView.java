package com.gauravlha.particles_android;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

import com.gauravlha.particles_android.model.ParticlesElements;
import com.gauravlha.particles_android.model.ParticlesProperties;

import java.util.ArrayList;
import java.util.Random;

public class CustomParticlesView extends View {

    private ParticlesProperties particlesProperties;
    private ParticlesElements particlesElements;

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
        particlesProperties = new ParticlesProperties();
        particlesElements = new ParticlesElements();
        mContext = context;
        h = new Handler();


        particlesElements.setPaint(new ArrayList<Paint>());
        particlesElements.setPaintAlpha(new ArrayList<Integer>());
        particlesElements.setBallSize(new ArrayList<Integer>());
        particlesElements.setX(new ArrayList<Integer>());
        particlesElements.setY(new ArrayList<Integer>());
        particlesElements.setxVelocity(new ArrayList<Integer>());
        particlesElements.setyVelocity(new ArrayList<Integer>());
        particlesElements.setRandomXCoordinate(new ArrayList<Integer>());
        particlesElements.setRandomYCoordinate(new ArrayList<Integer>());
        random = new Random();

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CustomParticlesView, 0, 0);
        try {
            particlesProperties.setFrameRate(ta.getInteger(R.styleable.CustomParticlesView_frameRate, 5));
            particlesProperties.setNumberOfBalls(ta.getInteger(R.styleable.CustomParticlesView_numberOfBalls, 50));
            particlesProperties.setxVelocityLowerLimit(ta.getInteger(R.styleable.CustomParticlesView_xVelocityLowerLimit, 1));
            particlesProperties.setxVelocityUpperLimit(ta.getInteger(R.styleable.CustomParticlesView_xVelocityUpperLimit, 5));
            particlesProperties.setyVelocityLowerLimit(ta.getInteger(R.styleable.CustomParticlesView_yVelocityLowerLimit, 1));
            particlesProperties.setyVelocityUpperLimit(ta.getInteger(R.styleable.CustomParticlesView_yVelocityUpperLimit, 5));
            particlesProperties.setPaintAlphaLowerLimit(ta.getInteger(R.styleable.CustomParticlesView_paintAlphaLowerLimit, 100));
            particlesProperties.setPaintAlphaUpperLimit(ta.getInteger(R.styleable.CustomParticlesView_paintAlphaUpperLimit, 255));
            particlesProperties.setBallSizeLowerLimit(ta.getInteger(R.styleable.CustomParticlesView_ballSizeLowerLimit, 5));
            particlesProperties.setBallSizeUpperLimit(ta.getInteger(R.styleable.CustomParticlesView_ballSizeUpperLimit, 10));
            particlesProperties.setLineXCoordinateDistance(ta.getInteger(R.styleable.CustomParticlesView_lineXCoordinateDistance, 100));
            particlesProperties.setLineYCoordinateDistance(ta.getInteger(R.styleable.CustomParticlesView_lineYCoordinateDistance, 20));
            particlesProperties.setCircleColor(ta.getColor(R.styleable.CustomParticlesView_circleColorName, getResources().getColor(android.R.color.black)));
            particlesProperties.setLineColor(ta.getColor(R.styleable.CustomParticlesView_lineColorName, getResources().getColor(android.R.color.holo_green_dark)));

        } finally {
            ta.recycle();
        }

        for (int i = 0; i < particlesProperties.getNumberOfBalls(); i++) {
            particlesElements.getX().add(-1);
            particlesElements.getY().add(-1);
            particlesElements.getxVelocity().add(random.nextInt(particlesProperties.getxVelocityUpperLimit() - particlesProperties.getxVelocityLowerLimit()) + particlesProperties.getxVelocityLowerLimit());
            particlesElements.getyVelocity().add(random.nextInt(particlesProperties.getyVelocityUpperLimit() - particlesProperties.getyVelocityLowerLimit()) + particlesProperties.getyVelocityLowerLimit());
            particlesElements.getPaint().add(new Paint());
            particlesElements.getPaintAlpha().add(random.nextInt(particlesProperties.getPaintAlphaUpperLimit() - particlesProperties.getPaintAlphaLowerLimit()) + particlesProperties.getPaintAlphaLowerLimit());
            particlesElements.getBallSize().add(random.nextInt(particlesProperties.getBallSizeUpperLimit() - particlesProperties.getBallSizeLowerLimit()) + particlesProperties.getBallSizeLowerLimit());
            particlesElements.getRandomXCoordinate().add(random.nextBoolean() ? 1 : -1);
            particlesElements.getRandomYCoordinate().add(random.nextBoolean() ? 1 : -1);
        }


    }

    protected void onDraw(Canvas c) {

        for (int i = 0; i < particlesProperties.getNumberOfBalls(); i++) {
            if (particlesElements.getX().get(i) < 0 && particlesElements.getY().get(i) < 0) {
                particlesElements.getX().set(i, random.nextInt(this.getWidth()));
                particlesElements.getY().set(i, random.nextInt(this.getHeight()));
            } else {
                particlesElements.getX().set(i, particlesElements.getX().get(i) + (particlesElements.getxVelocity().get(i)) * particlesElements.getRandomXCoordinate().get(i));
                particlesElements.getY().set(i, particlesElements.getY().get(i) + (particlesElements.getyVelocity().get(i)) * particlesElements.getRandomYCoordinate().get(i));
                if ((particlesElements.getX().get(i) > this.getWidth() - particlesElements.getBallSize().get(i)) || (particlesElements.getX().get(i) < 0)) {
                    particlesElements.getxVelocity().set(i, particlesElements.getxVelocity().get(i) * -1);
                }
                if ((particlesElements.getY().get(i) > this.getHeight() - particlesElements.getBallSize().get(i)) || (particlesElements.getY().get(i) < 0)) {
                    particlesElements.getyVelocity().set(i, particlesElements.getyVelocity().get(i) * -1);
                }

            }

            particlesElements.getPaint().get(i).setStyle(Paint.Style.FILL);
            particlesElements.getPaint().get(i).setColor(Color.WHITE);
            particlesElements.getPaint().get(i).setAlpha(particlesElements.getPaintAlpha().get(i));
            c.drawPaint(particlesElements.getPaint().get(i));


        }

        for (int i = 0; i < particlesProperties.getNumberOfBalls(); i++) {
            particlesElements.getPaint().get(i).setColor(particlesProperties.getCircleColor());
            c.drawCircle(particlesElements.getX().get(i), particlesElements.getY().get(i), particlesElements.getBallSize().get(i), particlesElements.getPaint().get(i));
            for (int j = 0; j < particlesProperties.getNumberOfBalls(); j++) {
                int valueX = Math.abs(particlesElements.getX().get(i) - particlesElements.getX().get(j));
                int valueY = Math.abs(particlesElements.getY().get(i) - particlesElements.getY().get(j));
                if (valueX < particlesProperties.getLineXCoordinateDistance() || (valueY < particlesProperties.getLineYCoordinateDistance() && valueX < particlesProperties.getLineXCoordinateDistance())) {
                    particlesElements.getPaint().get(i).setColor(particlesProperties.getLineColor());
                    c.drawLine(particlesElements.getX().get(i), particlesElements.getY().get(i), particlesElements.getX().get(j), particlesElements.getY().get(j), particlesElements.getPaint().get(i));
                }
            }

        }

        h.postDelayed(r, particlesProperties.getFrameRate());
    }
}