package com.upper.team15.privateschool.TeacherServerActivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;


import com.upper.team15.privateschool.AboutGuide.GuideInfoAdapter;
import com.upper.team15.privateschool.Model.GuideModel;
import com.upper.team15.privateschool.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;


public class GuideInfo extends Fragment  {
    RecyclerView recyclerView;
    RecyclerView.Adapter T_info_recycler;
    Firebase fbTeacherInfo;
    Toolbar toolbar;
    ProgressBar progressBar;
    ArrayList<GuideModel> TInfo;


    public GuideInfo() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_guide_info, container, false);
        recyclerView= (RecyclerView) view.findViewById(R.id.tguide_info_recycler);
        progressBar= (ProgressBar) view.findViewById(R.id.guide_info_progress);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        Firebase.setAndroidContext(getContext());
        TInfo=new ArrayList<GuideModel>();
        fbTeacherInfo=new Firebase("https://private-school-85adb.firebaseio.com/");
        fbTeacherInfo.child("GuideInformation").addValueEventListener(new ValueEventListener() {
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
            GuideModel tinfomodel=new GuideModel();
            tinfomodel=sh.getValue(GuideModel.class);
            Log.e("password",tinfomodel.getPassword());
            TInfo.add(tinfomodel);

        }
        T_info_recycler=new GuideInfoAdapter(getContext(),TInfo);
        progressBar.setVisibility(View.INVISIBLE);
        recyclerView.setAdapter(T_info_recycler);


    }


}
