package com.example.hreeves.testapplication.GamePackage;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.hreeves.testapplication.GamePackage.GameObject;

/**
 * Created by dgray on 4/23/2017.
 */

public class SmokePuff extends GameObject {

    public int r; //radius of the smoke cloud

    public SmokePuff(int x, int y) {

        r = 10;
        super.setX(x);
        super.setY(y);

    }

    public void update() {

        //Sets each smoke puff back in space to create the illusion of helicopter moving
        //accross x-axis
        setX(getX() - 10);

    }

    //Function repaints the canvas with a smoke cloud object
    public void draw(Canvas canvas) {

        Paint paint = new Paint();
        paint.setColor(Color.GRAY);
        paint.setStyle(Paint.Style.FILL);

        canvas.drawCircle(getX() - r, getY() - r, r, paint);
        canvas.drawCircle(getX() - r + 2, getY() - r - 2, r, paint);
        canvas.drawCircle(getX() - r + 4, getY() - r + 1, r, paint);

    }

}
