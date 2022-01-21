package com.upper.team15.privateschool.TeacherServerActivity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;


import com.upper.team15.privateschool.Model.RuleModel;
import com.upper.team15.privateschool.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;


public class RuleActivity extends Fragment  {
    ImageView phoneimage, messageimage, emailimage;
    //    Button btnGoogle;
    RecyclerView recyclerView;
    RecyclerView.Adapter ruleAdapter;
    Firebase eventFirebase;
    Toolbar toolbar;
    Button btn_rule_send;
    ArrayList<RuleModel> ruleData;
    ProgressBar pbar;

    public RuleActivity() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_rule, container, false);
        pbar= (ProgressBar) view.findViewById(R.id.rule_progressbar);
        ruleData=new ArrayList<RuleModel>();
        recyclerView= (RecyclerView) view.findViewById(R.id.rule_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        Firebase.setAndroidContext(getContext());
        eventFirebase=new Firebase("https://private-school-85adb.firebaseio.com/");
        eventFirebase.child("Rule").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                upDateData(dataSnapshot);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        return view;
    }

    private void upDateData(DataSnapshot dataSnapshot) {
        for (DataSnapshot sh:dataSnapshot.getChildren()){

            RuleModel rModel=new RuleModel();
            rModel.setRule(sh.getValue(RuleModel.class).getRule());
            ruleData.add(rModel);

        }
        ruleAdapter=new MyRuleAdapter(getContext(),ruleData);
        pbar.setVisibility(View.INVISIBLE);
        recyclerView.setAdapter(ruleAdapter);
    }


}


















