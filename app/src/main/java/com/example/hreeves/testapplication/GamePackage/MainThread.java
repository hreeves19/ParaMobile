package com.example.hreeves.testapplication.GamePackage;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

import com.example.hreeves.testapplication.GamePackage.GamePanel;

/**
 * Created by dgray on 4/22/2017.
 */

public class MainThread extends Thread {

    private int FPS = 30;
    private double averageFPS;
    private SurfaceHolder surfaceHolder;
    private GamePanel gamePanel;
    private boolean running;
    public static Canvas canvas;

    public MainThread(SurfaceHolder surfaceHolder, GamePanel gamePanel) {

        super();

        this.surfaceHolder = surfaceHolder;
        this.gamePanel = gamePanel;

    }

    @Override
    public void run() {

        long startTime;
        long timeMillis;
        long waitTime;
        long totalTime = 0;
        long targetTime = 1000/FPS;
        int frameCount = 0;

        while(running) {

            startTime = System.nanoTime();
            canvas = null;

            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    this.gamePanel.update();
                    this.gamePanel.draw(canvas);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if(canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            timeMillis = (System.nanoTime() - startTime) / 1000000;
            waitTime = targetTime - timeMillis;

            try{
                this.sleep(2);
            } catch (Exception e) {
                e.printStackTrace();
            }

            totalTime += System.nanoTime() - startTime;
            frameCount++;

            //frameCount == FPS

            if(frameCount == FPS) {
                averageFPS = 1000 / (totalTime/frameCount) / 1000000;
                frameCount = 0;
                totalTime = 0;
                System.out.println(averageFPS);
            }

        }

    }

    public void setRunning(boolean b) {
        running = b;
    }

}
