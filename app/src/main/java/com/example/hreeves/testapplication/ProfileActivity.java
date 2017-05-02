package com.example.hreeves.testapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by hreeves on 4/23/2017.
 */

public class ProfileActivity extends AppCompatActivity {

    //Firebase user
    FirebaseAuth firebaseAuth;

    //Database referencing
    private DatabaseReference databaseReference;

    //Fields to hold information from the activity
    private EditText fullNameText;
    private EditText addressText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        fullNameText = (EditText) findViewById(R.id.fullName);
        addressText = (EditText) findViewById(R.id.postalAddress);

        //Initializing firebase authentication object
        firebaseAuth = FirebaseAuth.getInstance();

        //If the user is not logged in
        if(firebaseAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

        firebaseAuth.getCurrentUser();

        //getting firebase database instance
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    //Purpose: To move to the main page
    public void goToMain(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    //Purpose: To update the information on the database
    public void updateProfileChanges(View v) {
        String name = fullNameText.getText().toString().trim();
        String address = addressText.getText().toString().trim();

        UserInformation userInformation = new UserInformation(name, address);

        FirebaseUser user = firebaseAuth.getCurrentUser();

        //This could potentially throw a null pointer exception if a user is not signed in
        try{
            databaseReference.child(user.getUid()).setValue(userInformation);
            Toast.makeText(this, "Information saved!", Toast.LENGTH_SHORT).show();
        } catch(NullPointerException e) {
            Toast.makeText(this, "Information could not be saved.", Toast.LENGTH_SHORT).show();
        }
    }
}
