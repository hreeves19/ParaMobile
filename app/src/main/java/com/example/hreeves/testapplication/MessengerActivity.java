package com.example.hreeves.testapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MessengerActivity extends AppCompatActivity {

    /*private ListView listOfText;
    private EditText inputOfText;
    private ArrayList<String> listItems = new ArrayList<String>();
    private ArrayAdapter<String> adapter;*/
    ListView listOfMessages = (ListView)findViewById(R.id.list_of_messages);
    //private FirebaseListAdapter<ChatMessage> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger);

        //Initializing variables
        /*inputOfText = (EditText) findViewById(R.id.input);
        listOfText = (ListView) findViewById(R.id.list_of_messages);

        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                listItems);
        listOfText.setAdapter(adapter);*/
        EditText input = (EditText)findViewById(R.id.input);

        // Read the input field and push a new instance
        // of ChatMessage to the Firebase database
        FirebaseDatabase.getInstance()
                .getReference()
                .push()
                .setValue(new ChatMessage(input.getText().toString(),
                        FirebaseAuth.getInstance()
                                .getCurrentUser()
                                .getDisplayName())
                );
        //Random Comment
        // Clear the input
        input.setText("");
    }

    public void addMessage(View v) {
        /*String inputMessage = inputOfText.getText().toString();
        listItems.add(inputMessage);
        adapter.notifyDataSetChanged();*/
    }
}
