package com.example.hreeves.testapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.hreeves.testapplication.GamePackage.GamePanel;

public class GameActivity extends AppCompatActivity {

    private GamePanel theGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //Set to full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //turn title off
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(new GamePanel(this));

    }

}
