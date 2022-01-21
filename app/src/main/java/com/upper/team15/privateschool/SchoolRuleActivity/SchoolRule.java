package com.upper.team15.privateschool.SchoolRuleActivity;

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
import com.upper.team15.privateschool.Model.RuleModel;
import com.upper.team15.privateschool.R;

import java.util.ArrayList;

public class SchoolRule extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter ruleAdapter;
    Firebase eventFirebase;
    Toolbar toolbar;
    FloatingActionButton floatingActionButton1;
    ArrayList<RuleModel> ruleData;
    ProgressBar pbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_rule);
        toolbar= (Toolbar) findViewById(R.id.serverRuleToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("စည်းကမ်းချက်များ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//show arraw key
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        floatingActionButton1= (FloatingActionButton) findViewById(R.id.arcmenu_android_example_layout);
        floatingActionButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent updaterule=new Intent(SchoolRule.this,SchoolServerUpdateRule.class);
                startActivity(updaterule);
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//show arraw key
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        pbar= (ProgressBar) findViewById(R.id.rule_progressbar);
        ruleData=new ArrayList<RuleModel>();

        recyclerView= (RecyclerView) findViewById(R.id.rule_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(SchoolRule.this));
        Firebase.setAndroidContext(SchoolRule.this);
        eventFirebase=new Firebase("https://private-school-85adb.firebaseio.com/");
        eventFirebase.child("Rule").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ruleData.clear();
                upDateData(dataSnapshot);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }

    private void upDateData(DataSnapshot dataSnapshot) {
        for (DataSnapshot sh:dataSnapshot.getChildren()){
            RuleModel rModel=new RuleModel();
            rModel.setRule(sh.getValue(RuleModel.class).getRule());
            ruleData.add(rModel);

        }
        ruleAdapter=new MyRuleAdapter(SchoolRule.this,ruleData);
        pbar.setVisibility(View.INVISIBLE);
        recyclerView.setAdapter(ruleAdapter);
    }

}
