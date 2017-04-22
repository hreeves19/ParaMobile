package com.example.hreeves.testapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void registerInformation(View v) {
        EditText emailAddress = (EditText) findViewById(R.id.emailAddressRegister);
        EditText passwordText = (EditText) findViewById(R.id.passwordRegister);
        EditText retypePasswordText = (EditText) findViewById(R.id.passwordRetypeRegister);
        EditText postalAddress = (EditText) findViewById(R.id.postalAddressRegister);
        EditText phoneNumber = (EditText) findViewById(R.id.phoneNumberRegister);

        String password = passwordText.getText().toString();
        String retypePassword = retypePasswordText.getText().toString();

        //Password Validation
        if(password.equals(retypePassword)){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
