package com.example.hreeves.testapplication.GamePackage;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.example.hreeves.testapplication.GamePackage.Animation;
import com.example.hreeves.testapplication.GamePackage.GameObject;

import java.util.Random;

/**
 * Created by dgray on 4/23/2017.
 */

public class Missile extends GameObject {

    private int score;
    private int speed;

    private Random rand = new Random();

    private Animation animation = new Animation();
    private Bitmap spriteSheet;



    public Missile(Bitmap res, int x, int y, int w, int h, int s, int numFrames) {

        super.setX(x);
        super.setY(y);

        setWidth(w);
        setHeight(h);

        score = s;
        speed = 7 + rand.nextInt() * score / 30;

        //cap missile speed
        if(speed > 40)
            speed = 40;

        Bitmap[] image = new Bitmap[numFrames];

        spriteSheet = res;

        for(int i = 0; i < image.length; i++) {
            image[i] = Bitmap.createBitmap(spriteSheet, 0, i * getHeight(), getWidth(), getHeight());
        }

        animation.setFrames(image);
        animation.setDelay(100 - speed);

    }

    public void update() {

        setX(getX() - speed);
        animation.update();

    }

    public void draw(Canvas canvas) {

        try {
            canvas.drawBitmap(animation.getImage(), getX(), getY(), null);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /*
    @Override
    public int getWidth() {
        //offset slightly for more realistic collision detection
        return getWidth() - 10;
    }
    */

}
