package com.example.hreeves.testapplication.GamePackage;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by dgray on 4/23/2017.
 */

public class Player extends GameObject {

    private Bitmap spriteSheet; //Bitmap for representing 2D PNG frame
    private int score;  //Value for storing score
    private double dya; //Acceleration Variable
    private boolean up; //boolean value for determining if player is moving up (via key press)
    private boolean playing; //boolean value representing whether or not the player is active (in this case - alive)
    private Animation animation = new Animation(); //Animation class (not the android animation class) for updating "motion" picture
    private long startTime; //long integer value storing the start time of the game in nanoseconds

    //Constructor - setting
    public Player(Bitmap res, int w, int h, int numFrames) {

        //Sets the original position of the player (helicopter) object
        setX(100);
        setY(GamePanel.HEIGHT / 2);
        setDy(0);
        setHeight(h);
        setWidth(w);
        score = 0;

        //Array containing the three separate frames animating the helicopter object
        Bitmap[] image = new Bitmap[numFrames];
        spriteSheet = res;

        for(int i = 0; i < image.length; i++) {

            //setting each frame to the helicopter
            image[i] = Bitmap.createBitmap(spriteSheet, i * getWidth(), 0, getWidth(), getHeight());

        }

        animation.setFrames(image);
        animation.setDelay(10);
        startTime = System.nanoTime();

    }

    //Setter for the boolean 'up'
    public void setUp(boolean b) { up = b; }

    //Update function that 'updates' each frame via drawing
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

        //Draws the new spriteSheet via animation which regularly updates how the frame should appear
        //According to time in nanoseconds
        canvas.drawBitmap(animation.getImage(), getX(), getY(), null);

    }

    //setter + getter section
    public int getScore() {return score;}
    public boolean getPlaying() {return playing;}
    public void setPlaying(boolean b) {playing = b;}
    public void resetDYA() {dya = 0;}
    public void resetScore() {score = 0;}



}
