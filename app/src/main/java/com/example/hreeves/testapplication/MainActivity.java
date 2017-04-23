package com.example.hreeves.testapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();

        //User already signed in
        if(firebaseAuth.getCurrentUser() != null) {
            MessageBox("You are already signed in! " + FirebaseAuth.getInstance().getCurrentUser().getEmail());
        } else {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void MessageBox(String input) {

        Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
    }

    public void moveToMessenger(View v) {
        Intent intent = new Intent(this, MessengerActivity.class);
        startActivity(intent);
        finish();
    }
}
