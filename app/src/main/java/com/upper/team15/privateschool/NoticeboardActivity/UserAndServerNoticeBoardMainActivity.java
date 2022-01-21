package com.upper.team15.privateschool.NoticeboardActivity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.upper.team15.privateschool.Model.NoticeModel;
import com.upper.team15.privateschool.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

public class UserAndServerNoticeBoardMainActivity extends AppCompatActivity implements View.OnClickListener {

    RecyclerView recyclerView;
    RecyclerView.Adapter eventAdapter;
    Firebase eventFirebase;
    Toolbar toolbar;
    ArrayList<NoticeModel> eventData;
    ProgressBar pbar;
    FloatingActionButton floatingActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_and_server_notice_board_main);
        toolbar= (Toolbar) findViewById(R.id.notice_Toolbar);
        floatingActionButton= (FloatingActionButton) findViewById(R.id.fab_notice_server);
        floatingActionButton.setOnClickListener(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("ကြော်ငြာသင်ပုန်း");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        pbar= (ProgressBar) findViewById(R.id.notice_progress);
        eventData=new ArrayList<NoticeModel>();
        recyclerView= (RecyclerView) findViewById(R.id.notice_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(UserAndServerNoticeBoardMainActivity.this));
        Firebase.setAndroidContext(UserAndServerNoticeBoardMainActivity.this);
        eventFirebase=new Firebase("https://private-school-85adb.firebaseio.com/");
        eventFirebase.child("Noticeboard").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                eventData.clear();
                upDateData(dataSnapshot);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }

    private void upDateData(DataSnapshot dataSnapshot) {
        for (DataSnapshot sh:dataSnapshot.getChildren()) {

            NoticeModel eModel = new NoticeModel();
            eModel = sh.getValue(NoticeModel.class);
//            eModel.setEventImage(sh.getValue(NoticeModel.class).getEventImage());
//            eModel.setDate(sh.getValue(NoticeModel.class).getDate());
//            eModel.setTime(sh.getValue(NoticeModel.class).getTime());
//            eModel.setText(sh.getValue(NoticeModel.class).getText());
            //Log.e("DataEvent",sh.getValue(EventModel.class).getText());
            eventData.add(eModel);
        }
        eventAdapter=new MyNoticeAdapter(UserAndServerNoticeBoardMainActivity.this,eventData);
        pbar.setVisibility(View.INVISIBLE);
        recyclerView.setAdapter(eventAdapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab_notice_server:
                Intent i=new Intent(UserAndServerNoticeBoardMainActivity.this,ServerNoticeBoardMainActivity.class);
                startActivity(i);
                break;
        }
    }
}