package com.example.hreeves.testapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MessengerActivity extends AppCompatActivity {

    private TextView timeSentAt;
    private EditText inputOfText;
    private FirebaseAuth firebaseAuth;
    private String room_name;
    private DatabaseReference root;
    private String temp_key;
    private String userEmail;
    private String message;
    private String timeSent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger);

        //Getting information from ChatRoomListActivity
        room_name = getIntent().getExtras().get("room_name").toString();
        setTitle("Room - " + room_name);

        //Establishing database reference
        root = FirebaseDatabase.getInstance().getReference().child(room_name);

        //Initializing variables
        inputOfText = (EditText) findViewById(R.id.input);
        firebaseAuth = FirebaseAuth.getInstance();
        userEmail = firebaseAuth.getCurrentUser().getEmail();
        timeSentAt = (TextView) findViewById(R.id.timeSent);

        root.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                appendChatConversation(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                appendChatConversation(dataSnapshot);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void addMessage(View v) {

        Map<String,Object> map = new HashMap<String,Object>();
        temp_key = root.push().getKey();
        root.updateChildren(map);

        DatabaseReference message_root = root.child(temp_key);
        Map<String,Object> map2 = new HashMap<String,Object>();
        map2.put("email", FirebaseAuth.getInstance().getCurrentUser().getEmail());
        map2.put("msg", inputOfText.getText().toString());
        map2.put("sentAt" , DateFormat.getDateTimeInstance().format(new Date()));

        message_root.updateChildren(map2);
    }

    public void appendChatConversation(DataSnapshot dataSnapshot) {
        Iterator i = dataSnapshot.getChildren().iterator();

        while(i.hasNext()) {
            userEmail = (String) ((DataSnapshot)i.next()).getValue();
            message = (String) ((DataSnapshot)i.next()).getValue();
            timeSent = (String) ((DataSnapshot)i.next()).getValue();
            timeSentAt.append("Sent at: " + DateFormat.getDateTimeInstance().format(new Date())
            + "\n" + userEmail + "\n" + message + "\n\n");
        }

        inputOfText.setText("");
    }

}
