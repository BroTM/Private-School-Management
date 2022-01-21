package com.upper.team15.privateschool.AboutTeacher;

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
import com.upper.team15.privateschool.Model.TeacherNumberModel;
import com.upper.team15.privateschool.R;

import java.util.ArrayList;

public class SchoolServerTeacherInfo extends AppCompatActivity implements View.OnClickListener {
    RecyclerView recyclerView;
    RecyclerView.Adapter T_info_recycler;
    Firebase fbTeacherInfo;
    Toolbar toolbar;
    ProgressBar progressBar;
    FloatingActionButton floatingButton;
    ArrayList<TeacherNumberModel> TInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_server_teacher_info);
        toolbar= (Toolbar) findViewById(R.id.teacher_info_toolbar);
        progressBar= (ProgressBar) findViewById(R.id.teacher_progress);
        setSupportActionBar(toolbar);
        floatingButton= (FloatingActionButton) findViewById(R.id.fab_teacher_btn);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("သင်ကြားနေသောဆရာများ");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        recyclerView= (RecyclerView) findViewById(R.id.teacher_info_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(SchoolServerTeacherInfo.this));
        floatingButton.setOnClickListener(this);
        Firebase.setAndroidContext(SchoolServerTeacherInfo.this);
        TInfo=new ArrayList<TeacherNumberModel>();
        fbTeacherInfo=new Firebase("https://private-school-85adb.firebaseio.com/");
        fbTeacherInfo.child("TeacherInformation").addValueEventListener(new ValueEventListener() {
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
            TeacherNumberModel tinfomodel=new TeacherNumberModel();
            tinfomodel.setName(sh.getValue(TeacherNumberModel.class).getName());
            tinfomodel.setSubject(sh.getValue(TeacherNumberModel.class).getSubject());
            tinfomodel.setTeacherClass(sh.getValue(TeacherNumberModel.class).getTeacherClass());
            tinfomodel.setTeacherGrade(sh.getValue(TeacherNumberModel.class).getTeacherGrade());
            TInfo.add(tinfomodel);

        }
        T_info_recycler=new TeacherInfoAdapter(SchoolServerTeacherInfo.this, TInfo);
        progressBar.setVisibility(View.INVISIBLE);
        recyclerView.setAdapter(T_info_recycler);
    }
    @Override
    public void onClick(View v) {
        Intent teacherintent=new Intent(SchoolServerTeacherInfo.this, Teacherinfoupdate.class);
        startActivity(teacherintent);

    }
}
