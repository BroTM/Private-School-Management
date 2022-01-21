package com.upper.team15.privateschool.AbsentActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.upper.team15.privateschool.Model.AbsentModel;
import com.upper.team15.privateschool.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;


public class ServerAbsent extends AppCompatActivity {
    Firebase absentFirebase;
    Toolbar toolbar;
    ImageView AbsentImage;
    RecyclerView.Adapter adapter;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    ArrayList<AbsentModel> absentformdata=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server_absent);
        toolbar= (Toolbar) findViewById(R.id.absent_server_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setTitle("Absent Form" + "");
        //ခွင့်တိုင်ကြားစာစစ်ရန်
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });

        recyclerView= (RecyclerView) findViewById(R.id.absent_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(ServerAbsent.this));
        progressBar= (ProgressBar) findViewById(R.id.absent_progress);
        Firebase.setAndroidContext(ServerAbsent.this);
        absentFirebase= new Firebase("https://private-school-85adb.firebaseio.com/");
        absentFirebase.child("AbsentForm").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                absentformdata.clear();
                updateData(dataSnapshot);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }



    private void updateData(DataSnapshot dataSnapshot) {
        for(DataSnapshot sh:dataSnapshot.getChildren()){
            AbsentModel absentModel=sh.getValue(AbsentModel.class);
            absentformdata.add(absentModel);


        }
        adapter=new AbsentAdapter(ServerAbsent.this,absentformdata);
        progressBar.setVisibility(View.INVISIBLE);
        recyclerView.setAdapter(adapter);

    }

}
