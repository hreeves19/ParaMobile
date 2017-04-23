package com.example.hreeves.testapplication.GamePackage;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.hreeves.testapplication.R;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by dgray on 4/22/2017.
 */

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {

    public static final float BITMAPSCALE = 3.5f;

    public static final int WIDTH = (int)(856 * BITMAPSCALE);
    public static final int HEIGHT = (int)(480 * BITMAPSCALE);

    public static final int MOVESPEED = -10;

    private long smokeStartTime;
    private long missileStartTime;

    private MainThread thread;
    private Background bg;
    private Player player;
    private ExitButton exitButton;
    private ArrayList<SmokePuff> smoke;
    private ArrayList<Missile> missiles;

    private Random rand = new Random();

    public GamePanel(Context context) {

        super(context);

        //Add callback to the surfaceholder to intercept events
        getHolder().addCallback(this);

        thread = new MainThread(getHolder(), this);

        //Make gamePanel focusable so it can handle events
        setFocusable(true);

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {}

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

        boolean retry = true;

        int counter = 0;

        while(retry && counter < 1000) {

            counter++;
            try {

                thread.setRunning(false);
                thread.join();
                retry = false;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        bg = new Background(BitmapFactory.decodeResource(getResources(), R.drawable.grassbg1));
        player = new Player(BitmapFactory.decodeResource(getResources(), R.drawable.helicopter), (int)(65 * BITMAPSCALE), (int)(25 * BITMAPSCALE), 3);
        exitButton = new ExitButton(BitmapFactory.decodeResource(getResources(), R.drawable.exiticon), (int)(5 * BITMAPSCALE), (int)(5 * BITMAPSCALE));
        smoke = new ArrayList<>();
        missiles = new ArrayList<>();


        smokeStartTime = System.nanoTime();
        missileStartTime = System.nanoTime();

        bg.setVector(MOVESPEED);

        thread.setRunning(true);
        thread.start();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

//        event.getX()

        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            if(!player.getPlaying()) {
                player.setPlaying(true);
            } else {
                player.setUp(true);
            }
            return true;
        }
        if(event.getAction() == MotionEvent.ACTION_UP) {
            player.setUp(false);
            return true;
        }

        return super.onTouchEvent(event);

    }

    public void update() {

        if(player.getPlaying()) {
            bg.update();
            player.update();
            exitButton.update();

            //add missiles on timer
            long missileElapsed = (System.nanoTime() - missileStartTime) / 1000000;

            if(missileElapsed > (2000 - player.getScore() / 4)) {

                //first missile always in middle
                if(missiles.size() == 0) {

                    missiles.add(new Missile(BitmapFactory.decodeResource(getResources(), R.drawable.missile), WIDTH + 10, HEIGHT / 2, (int)(45 * BITMAPSCALE), (int)(15 * BITMAPSCALE), player.getScore(), 13));

                } else {

                    missiles.add(new Missile(BitmapFactory.decodeResource(getResources(), R.drawable.missile), WIDTH + 10, (int)(rand.nextDouble() * HEIGHT), (int)(45 * BITMAPSCALE), (int)(15 * BITMAPSCALE),
                            player.getScore(), 13));

                }

                missileStartTime = System.nanoTime();

            }

            for(int i = 0; i < missiles.size(); i++) {

                missiles.get(i).update();

                if(collision(missiles.get(i), player)) {

                    missiles.remove(i);
                    player.setPlaying(false);
                    break;

                }

                if(missiles.get(i).getX() < -100) {

                    missiles.remove(i);
                    break;

                }

            }

            //Add smoke puffs on timer
            long elapsed = (System.nanoTime() - smokeStartTime) / 1000000;

            if(elapsed > 120) {
                smoke.add(new SmokePuff(player.getX(), player.getY() + 10));
                smokeStartTime = System.nanoTime();
            }

            for(int i = 0; i < smoke.size(); i++) {
                smoke.get(i).update();
                if(smoke.get(i).getX() < -10) {
                    smoke.remove(i);
                }
            }
        }

    }

    public boolean collision(GameObject a, GameObject b) {

        if(Rect.intersects(a.getRectangle(), b.getRectangle())) {
            return true;
        }

        return false;
    }

    @Override
    public void draw(Canvas canvas) {

        final float scaleFactorX = ((float)getWidth()/WIDTH);
        final float scaleFactorY = ((float)getHeight()/HEIGHT);

//        System.out.println("Screen Width " + scaleFactorX);
//        System.out.println("Screen Height " + scaleFactorY);


        if(canvas != null) {
            final int savedState = canvas.save();
            canvas.scale(scaleFactorX, scaleFactorY);
            bg.draw(canvas);
            player.draw(canvas);
            exitButton.draw(canvas);

            for(SmokePuff sp: smoke) {
                sp.draw(canvas);
            }

            for(Missile m: missiles) {
                m.draw(canvas);
            }

            canvas.restoreToCount(savedState);
        }

    }

}
