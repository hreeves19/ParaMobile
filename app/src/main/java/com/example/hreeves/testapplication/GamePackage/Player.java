package com.example.hreeves.testapplication.GamePackage;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by dgray on 4/23/2017.
 */

public class Player extends GameObject {

    private Bitmap spriteSheet;
    private int score;
    private double dya; //Acceleration Variable
    private boolean up;
    private boolean playing;
    private Animation animation = new Animation();
    private long startTime;

    public Player(Bitmap res, int w, int h, int numFrames) {

        setX(100);
        setY(GamePanel.HEIGHT / 2);
        setDy(0);
        setHeight(h);
        setWidth(w);
        score = 0;

        Bitmap[] image = new Bitmap[numFrames];
        spriteSheet = res;

        for(int i = 0; i < image.length; i++) {

            image[i] = Bitmap.createBitmap(spriteSheet, i * getWidth(), 0, getWidth(), getHeight());

        }

        animation.setFrames(image);
        animation.setDelay(10);
        startTime = System.nanoTime();

    }

    public void setUp(boolean b) { up = b; }

    public void update() {

        long elapsed = (System.nanoTime() - startTime) / 1000000;

        if(elapsed > 100) {

            score++;
            startTime = System.nanoTime();

        }

        animation.update();

        if(up) {
            setDy((int)(dya -= 1.08));
        } else {
            setDy((int)(dya += 1.08));
        }

        if(getDy() > 14)
            setDy(14);
        if(getDy() < -14)
            setDy(-14);

        setY(getY() + getDy() * 3);
        setDy(0);

    }

    public void draw(Canvas canvas) {

        canvas.drawBitmap(animation.getImage(), getX(), getY(), null);

    }

    public int getScore() {return score;}
    public boolean getPlaying() {return playing;}
    public void setPlaying(boolean b) {playing = b;}
    public void resetDYA() {dya = 0;}
    public void resetScore() {score = 0;}



}
