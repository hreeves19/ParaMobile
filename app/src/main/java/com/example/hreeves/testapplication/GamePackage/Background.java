package com.example.hreeves.testapplication.GamePackage;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by dgray on 4/22/2017.
 */

public class Background {

    private Bitmap image;
    private int x, y, dx;

    public Background(Bitmap res) {

        image = res;
        dx = GamePanel.MOVESPEED;

    }

    public void update() {

//        System.out.println("Width of image " + image.getWidth());
//        System.out.println("Height of image " + image.getHeight());

        x += dx;

        if(x < -GamePanel.WIDTH) {
            x = 0;
        }

    }
    public void draw(Canvas canvas) {

        canvas.drawBitmap(image, x, y, null);
        if(x < 0) {
            canvas.drawBitmap(image, x + GamePanel.WIDTH, y, null);
        }
    }

    public void setVector(int dx) {
        this.dx = dx;
    }

}
