package com.upper.team15.privateschool.TeacherServerActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.upper.team15.privateschool.Model.HomeWorkModel;
import com.upper.team15.privateschool.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ServerUpdateHomework extends AppCompatActivity implements View.OnClickListener {

    Firebase fb_Update_Homework;
    ArrayList<HomeWorkModel> homeworklist;
    EditText page,subject;
    Button addbtn;
    Toolbar toolbar;
    TextView class_teacher;
    public static String sday;
    public static String stime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server_update_homework);
        toolbar= (Toolbar) findViewById(R.id.HWservertoolbar);
        setSupportActionBar(toolbar);
        class_teacher= (TextView) findViewById(R.id.class_teacher);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("အိမ်စာပေးရန်");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Firebase.setAndroidContext(ServerUpdateHomework.this);
        homeworklist=new ArrayList<HomeWorkModel>();
        fb_Update_Homework=new Firebase("https://private-school-85adb.firebaseio.com/");
        class_teacher.setText(getClassName().toString());
        page= (EditText) findViewById(R.id.edit_pageno);
        subject= (EditText) findViewById(R.id.edit_subject);
        addbtn= (Button) findViewById(R.id.btn_add);
        addbtn.setOnClickListener(this);

    }
    public static String getTime(){
        DateFormat time = new SimpleDateFormat(" HH:mm:ss");
        time.setLenient(false);
        Date todaytime = new Date();
        stime = time.format(todaytime);
        return stime;
    }
    public static String getDate(){
        DateFormat date = new SimpleDateFormat("yyyy:MM:dd ");
        date.setLenient(false);
        Date todaydate = new Date();
        sday = date.format(todaydate);
        return sday;
    }
    @Override
    public void onClick(View v) {
        if(subject.getText().toString().equals("") && page.getText().toString().equals("")){
            Toast.makeText(this, R.string.enter_again, Toast.LENGTH_SHORT).show();
        }
        else {


        getDate();
        getTime();
        HomeWorkModel homeWorkModel=new HomeWorkModel();
//        homeWorkModel.setSubjectTitle(subject.getText().toString());
//        homeWorkModel.setPageNo(page.getText().toString());
//        homeWorkModel.setDate(sday);
//        homeWorkModel.setTime(stime);
        homeWorkModel.setSubjectTitle(subject.getText().toString());
        homeWorkModel.setDate(sday);
        homeWorkModel.setTime(stime);
        homeWorkModel.setPageNo(page.getText().toString());
        fb_Update_Homework.child("Homework").child(getClassName()).push().setValue(homeWorkModel);
        subject.setText("");
        page.setText("");
        Toast.makeText(this, "အိမ်စာ ပို့ပီးပါပီ။", Toast.LENGTH_SHORT).show();

    }
    }
    public String getClassName(){
        SharedPreferences shdatamain=getSharedPreferences("MyGuideProfile",MODE_PRIVATE);
        return shdatamain.getString("class","default");
    }

}
