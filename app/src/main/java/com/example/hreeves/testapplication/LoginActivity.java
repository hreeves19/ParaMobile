package com.example.hreeves.testapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void submit(View v) {
        EditText password = (EditText) findViewById(R.id.password);
        EditText email = (EditText) findViewById(R.id.emailAddress);

        //Add code for password validation and email validation

        //Going to new activity
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish(); //Close current activity
    }

    public void register(View v) {
        //Going to new activity
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
        finish(); //Close current activity
    }
}
