package com.example.hreeves.testapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        System.out.println("Game Activity activated");

        super.onCreate(savedInstanceState);
        //Set to full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //turn title off
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(new GamePanel(this));
    }





}
