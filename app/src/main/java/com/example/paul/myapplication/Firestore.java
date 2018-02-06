package com.example.paul.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.paul.myapplication.Lists.AllWalks;
import com.example.paul.myapplication.Lists.WalksListAdapter;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by butle on 26-Jan-18.
 */

public class Firestore extends AppCompatActivity {


    private RecyclerView mMainList;
    private static final String TAG = "MainActivity";

    private FirebaseFirestore mFirestore;

    private List<AllWalks> allWalksList;
    private WalksListAdapter walksListAdapter;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firestore);

        //Assign list
        allWalksList = new ArrayList<>();
        walksListAdapter = new WalksListAdapter(allWalksList);


        mMainList = (RecyclerView) findViewById(R.id.main_list);
        mMainList.setHasFixedSize(true);
        mMainList.setLayoutManager(new LinearLayoutManager(this));
        mMainList.setAdapter(walksListAdapter);

        mFirestore = FirebaseFirestore.getInstance();

        mFirestore.collection("Walks2").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                if(e != null)
                {
                    Log.d(TAG, "Error" + e.getMessage());
                }
                for (DocumentChange doc: documentSnapshots.getDocumentChanges()){
                    if (doc.getType() == DocumentChange.Type.ADDED){

                      AllWalks allWalks = doc.getDocument().toObject(AllWalks.class);
                      allWalksList.add(allWalks);

                      walksListAdapter.notifyDataSetChanged();


                    }
                }
            }
        });
    }
}
