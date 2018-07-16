package com.gauravlha.particles_android.model;

import android.graphics.Paint;

import java.util.List;

public class ParticlesElements {

    private List<Integer> x;
    private List<Integer> y;
    private List<Paint> paint;
    private List<Integer> paintAlpha;
    private List<Integer> ballSize;
    private List<Integer> xVelocity;
    private List<Integer> yVelocity;
    private List<Integer> randomXCoordinate;
    private List<Integer> randomYCoordinate;

    public List<Integer> getX() {
        return x;
    }

    public void setX(List<Integer> x) {
        this.x = x;
    }

    public List<Integer> getY() {
        return y;
    }

    public void setY(List<Integer> y) {
        this.y = y;
    }

    public List<Paint> getPaint() {
        return paint;
    }

    public void setPaint(List<Paint> paint) {
        this.paint = paint;
    }

    public List<Integer> getPaintAlpha() {
        return paintAlpha;
    }

    public void setPaintAlpha(List<Integer> paintAlpha) {
        this.paintAlpha = paintAlpha;
    }

    public List<Integer> getBallSize() {
        return ballSize;
    }

    public void setBallSize(List<Integer> ballSize) {
        this.ballSize = ballSize;
    }

    public List<Integer> getxVelocity() {
        return xVelocity;
    }

    public void setxVelocity(List<Integer> xVelocity) {
        this.xVelocity = xVelocity;
    }

    public List<Integer> getyVelocity() {
        return yVelocity;
    }

    public void setyVelocity(List<Integer> yVelocity) {
        this.yVelocity = yVelocity;
    }

    public List<Integer> getRandomXCoordinate() {
        return randomXCoordinate;
    }

    public void setRandomXCoordinate(List<Integer> randomXCoordinate) {
        this.randomXCoordinate = randomXCoordinate;
    }

    public List<Integer> getRandomYCoordinate() {
        return randomYCoordinate;
    }

    public void setRandomYCoordinate(List<Integer> randomYCoordinate) {
        this.randomYCoordinate = randomYCoordinate;
    }
}
