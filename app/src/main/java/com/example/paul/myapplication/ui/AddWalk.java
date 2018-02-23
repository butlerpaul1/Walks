package com.example.paul.myapplication.ui;

/**
 * Created by butle on 29-Jan-18.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.paul.myapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddWalk extends AppCompatActivity {

    private DatabaseReference mDatabase;

    private Button mFirebaseBtn, submitWalk;
    private EditText addWalk, walkFormat, walkDiff, walkLength;

    private ProgressBar progressBar;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // set the view now
        setContentView(R.layout.add_walk);

        //create instance that points to database
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mFirebaseBtn = (Button) findViewById(R.id.firebase_btn);

        addWalk = (EditText) findViewById(R.id.add_walk);
        walkDiff = (EditText) findViewById(R.id.walk_diffuctly);
        walkFormat = (EditText) findViewById(R.id.walk_format);
        walkLength = (EditText) findViewById(R.id.walk_length);


        walkDiff.setVisibility(View.GONE);
        walkFormat.setVisibility(View.GONE);
        walkLength.setVisibility(View.GONE);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNav_View);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.nav_Home:
                        startActivity(new Intent(AddWalk.this, MainActivity.class));
                        break;

                    case R.id.nav_View:
                        startActivity(new Intent(AddWalk.this, ViewWalks.class));
                        break;

                    case R.id.nav_Add:
                        startActivity(new Intent(AddWalk.this, AddWalk.class));
                        break;
                }

                return false;
            }
        });



        mFirebaseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //create child in root object
                //assign values in that child




                if (!addWalk.getText().toString().trim().equals("")) {

                    String walkName = addWalk.getText().toString().trim();
                    String difficultly = walkDiff.getText().toString().trim();
                    String format = walkFormat.getText().toString().trim();
/*
                            HashMap<String, String> dataMap = new HashMap<String, String>();
                            dataMap.put("Format", format);
                            dataMap.put("Difficultly", difficultly);
*/


                    mDatabase.push().setValue(walkName)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(AddWalk.this, "Walk added!", Toast.LENGTH_SHORT).show();
                                        addWalk.setText(null);
                                        walkDiff.setText(null);
                                        walkFormat.setText(null);

                                    } else {
                                        Toast.makeText(AddWalk.this, "Failed to add walk!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });




                    //Toast.makeText(AddWalk.this, "It works", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(AddWalk.this, "Please enter  some data", Toast.LENGTH_LONG).show();


                }


            }
        });
    }
}
