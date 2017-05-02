package com.example.hreeves.testapplication.GamePackage;

import android.graphics.Bitmap;

/**
 * Created by dgray on 4/23/2017.
 */

public class Animation {

    private Bitmap[] frames;        //Number of frame steps the animation runs through (ex. 3 for the helicopter)
    private int currentFrame;       //Where the frame currently is
    private long startTime;         //Start time in nanoseconds
    private long delay;             //Time between each individual frame
    private boolean playedOnce;     //boolean for if one full animation cycle has been played

    public void setFrames(Bitmap[] frames) {

        this.frames = frames;
        currentFrame = 0;
        startTime = System.nanoTime();

    }

    public void setDelay(long d) {delay = d;}
    public void setFrame(int i) {currentFrame = i;}

    public void update() {

        long elapsed = (System.nanoTime() - startTime) / 1000000;

        //Once a certain amount of time has passed the frame will change
        if(elapsed > delay) {

            currentFrame++;
            startTime = System.nanoTime();

        }

        if(currentFrame == frames.length) {

            currentFrame = 0;
            playedOnce = true;

        }

    }

    public Bitmap getImage() {

        return frames[currentFrame];

    }

    public int getFrame() {return currentFrame;}
    public boolean playedOnce() {return playedOnce;}
}
