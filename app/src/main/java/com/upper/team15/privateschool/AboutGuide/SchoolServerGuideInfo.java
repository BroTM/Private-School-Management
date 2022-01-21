package com.upper.team15.privateschool.AboutGuide;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.upper.team15.privateschool.Model.GuideModel;
import com.upper.team15.privateschool.R;

import java.util.ArrayList;

public class SchoolServerGuideInfo extends AppCompatActivity implements View.OnClickListener {
    RecyclerView recyclerView;
    RecyclerView.Adapter T_info_recycler;
    Firebase fbTeacherInfo;
    ProgressBar pbar;
    Toolbar toolbar;
    FloatingActionButton fab;
    ArrayList<GuideModel> TInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_server_guide_info);
        pbar=(ProgressBar)findViewById(R.id.guide_progress);
        toolbar= (Toolbar) findViewById(R.id.guide_info_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("လေ့ကျင့်ပေးသောဆရာများ");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        fab=(FloatingActionButton) findViewById(R.id.fab_guide_btn);
        fab.setOnClickListener(this);
        recyclerView= (RecyclerView) findViewById(R.id.guide_info_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(SchoolServerGuideInfo.this));
        Firebase.setAndroidContext(SchoolServerGuideInfo.this);
        TInfo=new ArrayList<GuideModel>();
        fbTeacherInfo=new Firebase("https://private-school-85adb.firebaseio.com/");
        fbTeacherInfo.child("GuideInformation").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                TInfo.clear();
                updateData(dataSnapshot);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    private void updateData(DataSnapshot dataSnapshot) {
        for(DataSnapshot sh:dataSnapshot.getChildren()){
            GuideModel tinfomodel=new GuideModel();
            tinfomodel.setName(sh.getValue(GuideModel.class).getName());
            tinfomodel.setTeacherClass(sh.getValue(GuideModel.class).getTeacherClass());
            tinfomodel.setTeacherGrade(sh.getValue(GuideModel.class).getTeacherGrade());
            tinfomodel.setUsername(sh.getValue(GuideModel.class).getUsername());
            tinfomodel.setPassword(sh.getValue(GuideModel.class).getPassword());
            TInfo.add(tinfomodel);
        }
        T_info_recycler=new GuideInfoAdapter(SchoolServerGuideInfo.this, TInfo);
        pbar.setVisibility(View.INVISIBLE);
        recyclerView.setAdapter(T_info_recycler);
    }
    @Override
    public void onClick(View v) {
        Intent teacherintent=new Intent(SchoolServerGuideInfo.this, Guideinfoupdate.class);
        startActivity(teacherintent);
    }
}
