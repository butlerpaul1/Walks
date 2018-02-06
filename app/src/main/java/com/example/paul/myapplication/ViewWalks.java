package com.example.paul.myapplication;

/**
 * Created by butle on 29-Jan-18.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewWalks extends AppCompatActivity {


    private DatabaseReference mDatabase;
    private ListView mWalkName;

    private ArrayList<String> walkName = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // set the view now
        setContentView(R.layout.view_walks);

        //create instance that points to database
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mWalkName = (ListView) findViewById(R.id.user_list);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNav_View);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.nav_Home:
                        startActivity(new Intent(ViewWalks.this, MainActivity.class));

                        break;

                    case R.id.nav_View:
                        startActivity(new Intent(ViewWalks.this, ViewWalks.class));
                        break;

                    case R.id.nav_Add:
                        startActivity(new Intent(ViewWalks.this, AddWalk.class));
                        break;
                }

                return false;
            }
        });

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,walkName);

        mWalkName.setAdapter(arrayAdapter);

        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                String value = dataSnapshot.getValue(String.class);

                //add to array
                walkName.add(value);

                arrayAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

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
}
