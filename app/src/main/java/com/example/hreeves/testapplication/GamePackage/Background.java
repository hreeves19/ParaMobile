package com.example.hreeves.testapplication.GamePackage;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by dgray on 4/22/2017.
 */

public class Background {

    private Bitmap image; //Bitmap containing the PNG image displaying the background
    private int x, y, dx; // x and y positions with the direction of x 'dx' vector determining movement of the frame

    public Background(Bitmap res) {

        image = res;                //sets image
        dx = GamePanel.MOVESPEED;   //sets rate of change of scrolling background

    }

    public void update() {

//        System.out.println("Width of image " + image.getWidth());
//        System.out.println("Height of image " + image.getHeight());

        x += dx;

        if(x < -GamePanel.WIDTH) {
            x = 0;
        }

    }

    //Redraws the canvas to create illusion of movement
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
