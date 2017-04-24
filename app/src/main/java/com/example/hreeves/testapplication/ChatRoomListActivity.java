package com.example.hreeves.testapplication;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ChatRoomListActivity extends AppCompatActivity {

    private EditText roomNameText;
    private ListView listOfRoomView;
    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String> listOfRooms = new ArrayList<>();
    private DatabaseReference root = FirebaseDatabase.getInstance().getReference().getRoot();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room_list);

        roomNameText = (EditText) findViewById(R.id.roomText);
        listOfRoomView = (ListView) findViewById(R.id.chatRoomList);

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                listOfRooms);

        listOfRoomView.setAdapter(arrayAdapter);

        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterator i = dataSnapshot.getChildren().iterator();
                Set<String> set = new HashSet<String>();
                while(i.hasNext()){
                    set.add(((DataSnapshot)i.next()).getKey());
                }
                listOfRooms.clear();
                listOfRooms.addAll(set);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        listOfRoomView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplication(), MessengerActivity.class);
                intent.putExtra("room_name", ((TextView)view).getText().toString());
                startActivity(intent);
            }
        });

    }

    public void addRoom(View v) {

        Map<String,Object> map = new HashMap<String, Object>();
        map.put(roomNameText.getText().toString(), "");
        root.updateChildren(map);

    }
}
