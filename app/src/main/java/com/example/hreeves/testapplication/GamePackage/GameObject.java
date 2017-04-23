package com.example.hreeves.testapplication.GamePackage;

import android.graphics.Rect;

/**
 * Created by dgray on 4/22/2017.
 */

public abstract class GameObject {

    private int x;
    private int y;
    private int dy;
    private int dx;
    private int width;
    private int height;

    public Rect getRectangle() {
        return new Rect(x, y, x + width, y + height);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDy() {
        return dy;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public int getDx() {
        return dx;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}
