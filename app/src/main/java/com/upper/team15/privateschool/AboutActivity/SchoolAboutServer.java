package com.upper.team15.privateschool.AboutActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.upper.team15.privateschool.AboutGuide.SchoolServerGuideInfo;
import com.upper.team15.privateschool.AboutTeacher.SchoolServerTeacherInfo;
import com.upper.team15.privateschool.Model.EventModel;
import com.upper.team15.privateschool.R;
import com.upper.team15.privateschool.SchoolRuleActivity.SchoolRule;
import com.upper.team15.privateschool.SchoolServerEvent.SchoolEventAdapter;
import com.upper.team15.privateschool.SchoolServerEvent.SchoolServerUpdateEvent;

import java.util.ArrayList;

;

public class SchoolAboutServer extends AppCompatActivity implements View.OnClickListener {

    RecyclerView recyclerView;
    RecyclerView.Adapter eventAdapter;
    Firebase eventFirebase;
    ArrayList<EventModel> eventData=new ArrayList<>();
    ProgressBar pbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    FloatingActionButton fab_server_event;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_about_server);
        drawerLayout= (DrawerLayout) findViewById(R.id.drawerlayout);
        fab_server_event= (FloatingActionButton) findViewById(R.id.fab_event_server);
        fab_server_event.setOnClickListener(this);
        navigationView= (NavigationView) findViewById(R.id.navigation);
        toolbar= (Toolbar) findViewById(R.id.activity_about_server_toolbar);
        pbar= (ProgressBar) findViewById(R.id.event_progress);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("ကျောင်းအကြောင်း");
        recyclerView= (RecyclerView) findViewById(R.id.event_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(SchoolAboutServer.this));
        Firebase.setAndroidContext(SchoolAboutServer.this);
        eventFirebase=new Firebase("https://private-school-85adb.firebaseio.com/");
        eventFirebase.child("Event").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                eventData.clear();
                Log.i("Data From Server ","Get");
                upDateData(dataSnapshot);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.i("Data From Server ","Error");
            }
        });



        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(SchoolAboutServer.this,drawerLayout,toolbar,R.string.open,R.string.close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };

        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.contact:
                        Intent contact=new Intent(SchoolAboutServer.this,SchoolServerContact.class);
                        startActivity(contact);
                        drawerLayout.closeDrawers();
                        item.setChecked(true);
                        break;
                    case R.id.uniform:
                        Intent uniform=new Intent(SchoolAboutServer.this,SchoolServerUniform.class);
                        startActivity(uniform);
                        drawerLayout.closeDrawers();
                        item.setChecked(true);
                        break;
                    case R.id.rule_activity:
                        Intent rule=new Intent(SchoolAboutServer.this,SchoolRule.class);
                        startActivity(rule);
                        drawerLayout.closeDrawers();
                        item.setChecked(true);
                        break;
                    case R.id.learningteacher:
                        Intent learn=new Intent(SchoolAboutServer.this,SchoolServerTeacherInfo.class);
                        startActivity(learn);
                        drawerLayout.closeDrawers();
                        item.setChecked(true);
                        break;
                    case R.id.guideteacher:
                        Intent guideteacher=new Intent(SchoolAboutServer.this,SchoolServerGuideInfo.class);
                        startActivity(guideteacher);
                        drawerLayout.closeDrawers();
                        item.setChecked(true);
                        break;
                    case R.id.exist:
                        finish();
                        break;

                }
                return false;
            }
        });



    }
    private void upDateData(DataSnapshot dataSnapshot) {
        for (DataSnapshot sh:dataSnapshot.getChildren()){

            EventModel eModel=new EventModel();
            //eModel=sh.getValue(EventModel.class);
            Log.d("Event Date","Event Date");
            eModel.setEventImage(sh.getValue(EventModel.class).getEventImage());
            eModel.setDate(sh.getValue(EventModel.class).getDate());
            eModel.setTime(sh.getValue(EventModel.class).getTime());
            eModel.setText(sh.getValue(EventModel.class).getText());
            eModel.setDescripiton(sh.getValue(EventModel.class).getDescripiton());
            //Log.e("DataEvent",sh.getValue(EventModel.class).getText());
            eventData.add(eModel);
            eventAdapter=new SchoolEventAdapter(SchoolAboutServer.this,eventData);
            pbar.setVisibility(View.INVISIBLE);
            recyclerView.setAdapter(eventAdapter);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab_event_server:
                Intent event=new Intent(SchoolAboutServer.this,SchoolServerUpdateEvent.class);
                startActivity(event);
                break;
        }
    }
}