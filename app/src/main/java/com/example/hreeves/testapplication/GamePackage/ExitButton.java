package com.example.hreeves.testapplication.GamePackage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import static com.example.hreeves.testapplication.GamePackage.GamePanel.BITMAPSCALE;

/**
 * Created by dgray on 4/23/2017.
 */

public class ExitButton extends GameObject{

    private Bitmap spriteSheet; //PNG file containing image
    private Bitmap scaledBitmap; //scaled version of the image to fit properly

    public ExitButton(Bitmap res, int w, int h) {

        spriteSheet = res;

        setX(GamePanel.WIDTH - (256));
        setY(0);

        setHeight(h);
        setWidth(w);

        scaledBitmap = scaleDown(spriteSheet, 256, false);

    }

    //Function to rescale the exit button
    public static Bitmap scaleDown(Bitmap realImage, float maxImageSize,
                                   boolean filter) {
        float ratio = Math.min(
                maxImageSize / realImage.getWidth(),
                maxImageSize / realImage.getHeight());
        int width = Math.round(ratio * realImage.getWidth());
        int height = Math.round(ratio * realImage.getHeight());

        Bitmap newBitmap = Bitmap.createScaledBitmap(realImage, width,
                height, filter);
        return newBitmap;
    }

    public void update() {}

    public void draw(Canvas canvas) {

        //Redraws exit button on canvas
        canvas.drawBitmap(scaledBitmap, getX(), getY(), null);

    }

}
