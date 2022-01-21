package com.upper.team15.privateschool.TeacherServerActivity;

import android.support.v4.app.Fragment;

/**
 * Created by MAT on 11/8/2017.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;


import com.upper.team15.privateschool.AboutTeacher.TeacherInfoAdapter;
import com.upper.team15.privateschool.Model.TeacherNumberModel;
import com.upper.team15.privateschool.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;


public class TeacherInfo extends Fragment  {
    RecyclerView recyclerView;
    RecyclerView.Adapter T_info_recycler;
    Firebase fbTeacherInfo;
    Toolbar toolbar;
    ArrayList<TeacherNumberModel> TInfo;
    ProgressBar progressBar;

    public TeacherInfo() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_teacher_info, container, false);

        progressBar= (ProgressBar) view.findViewById(R.id.teacher_info_progress);
        recyclerView= (RecyclerView) view.findViewById(R.id.teach_info_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        Firebase.setAndroidContext(getContext());
        TInfo=new ArrayList<TeacherNumberModel>();
        fbTeacherInfo=new Firebase("https://private-school-85adb.firebaseio.com/");
        fbTeacherInfo.child("TeacherInformation").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                updateTeacherdata(dataSnapshot);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        return view;
    }

    private void updateTeacherdata(DataSnapshot dataSnapshot) {
        for(DataSnapshot sh:dataSnapshot.getChildren()){
            TeacherNumberModel tinfomodel=new TeacherNumberModel();
            tinfomodel.setName(sh.getValue(TeacherNumberModel.class).getName());
            tinfomodel.setSubject(sh.getValue(TeacherNumberModel.class).getSubject());
            tinfomodel.setTeacherClass(sh.getValue(TeacherNumberModel.class).getTeacherClass());
            tinfomodel.setTeacherGrade(sh.getValue(TeacherNumberModel.class).getTeacherGrade());
            TInfo.add(tinfomodel);

        }
        T_info_recycler=new TeacherInfoAdapter(getActivity(),TInfo);
        progressBar.setVisibility(View.INVISIBLE);
        recyclerView.setAdapter(T_info_recycler);


    }


}