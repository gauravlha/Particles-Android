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

public class CustomSnowParticlesView extends View {

    private Integer frameRate = 5;

    private ParticlesProperties particlesProperties;
    private ParticlesElements particlesElements;

    private Handler h;
    private Random random;
    private Runnable r = new Runnable() {
        @Override
        public void run() {
            invalidate();
        }
    };

    public CustomSnowParticlesView(Context context, AttributeSet attrs) {
        super(context, attrs);
        particlesElements = new ParticlesElements();
        particlesProperties = new ParticlesProperties();

        h = new Handler();
        random = new Random();

        particlesElements.setX(new ArrayList<Integer>());
        particlesElements.setY(new ArrayList<Integer>());
        particlesElements.setPaint(new ArrayList<Paint>());
        particlesElements.setyVelocity(new ArrayList<Integer>());
        particlesElements.setPaintAlpha(new ArrayList<Integer>());
        particlesElements.setBallSize(new ArrayList<Integer>());

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CustomSnowParticlesView, 0, 0);
        try {
            particlesProperties.setFrameRate(ta.getInteger(R.styleable.CustomSnowParticlesView_frameRate, 5));
            particlesProperties.setNumberOfBalls(ta.getInteger(R.styleable.CustomSnowParticlesView_numberOfBalls, 50));
            particlesProperties.setyVelocityLowerLimit(ta.getInteger(R.styleable.CustomSnowParticlesView_yVelocityLowerLimit, 1));
            particlesProperties.setyVelocityUpperLimit(ta.getInteger(R.styleable.CustomSnowParticlesView_yVelocityUpperLimit, 5));
            particlesProperties.setPaintAlphaLowerLimit(ta.getInteger(R.styleable.CustomSnowParticlesView_paintAlphaLowerLimit, 100));
            particlesProperties.setPaintAlphaUpperLimit(ta.getInteger(R.styleable.CustomSnowParticlesView_paintAlphaUpperLimit, 255));
            particlesProperties.setBallSizeLowerLimit(ta.getInteger(R.styleable.CustomSnowParticlesView_ballSizeLowerLimit, 5));
            particlesProperties.setBallSizeUpperLimit(ta.getInteger(R.styleable.CustomSnowParticlesView_ballSizeUpperLimit, 10));
            particlesProperties.setCircleColor(ta.getColor(R.styleable.CustomSnowParticlesView_circleColorName, getResources().getColor(android.R.color.black)));

        } finally {
            ta.recycle();
        }

        for (int i = 0; i < particlesProperties.getNumberOfBalls(); i++) {
            particlesElements.getX().add(-1);
            particlesElements.getY().add(-1);
            particlesElements.getyVelocity().add(random.nextInt(particlesProperties.getyVelocityUpperLimit() - particlesProperties.getyVelocityLowerLimit()) + particlesProperties.getyVelocityLowerLimit());
            particlesElements.getPaint().add(new Paint());
            particlesElements.getPaintAlpha().add(random.nextInt(particlesProperties.getPaintAlphaUpperLimit() - particlesProperties.getPaintAlphaLowerLimit()) + particlesProperties.getPaintAlphaLowerLimit());
            particlesElements.getBallSize().add(random.nextInt(particlesProperties.getBallSizeUpperLimit() - particlesProperties.getBallSizeLowerLimit()) + particlesProperties.getBallSizeLowerLimit());

        }
    }

    protected void onDraw(Canvas c) {

        for (int i = 0; i < particlesProperties.getNumberOfBalls(); i++) {
            if (particlesElements.getX().get(i) < 0 && particlesElements.getY().get(i) < 0) {
                particlesElements.getX().set(i, random.nextInt(this.getWidth()));
                particlesElements.getY().set(i, random.nextInt(this.getHeight()));
            } else {
                particlesElements.getY().set(i, particlesElements.getY().get(i) + (particlesElements.getyVelocity().get(i)));
                if ((particlesElements.getY().get(i) > this.getHeight() - 10) || (particlesElements.getY().get(i) < 0)) {
                    particlesElements.getY().set(i, 0);
                    particlesElements.getX().set(i, random.nextInt(this.getWidth()));
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

        }

        h.postDelayed(r, frameRate);

    }
}
