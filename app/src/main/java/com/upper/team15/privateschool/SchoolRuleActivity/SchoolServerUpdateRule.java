package com.upper.team15.privateschool.SchoolRuleActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.upper.team15.privateschool.HomeActivity.SchoolServerHomeActivity;
import com.upper.team15.privateschool.Model.RuleModel;
import com.upper.team15.privateschool.R;

public class SchoolServerUpdateRule extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolbar;
    EditText editRule;
    Firebase RuleFb;
    Button btn_Rule_send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_server_update_rule);
        Firebase.setAndroidContext(SchoolServerUpdateRule.this);
        toolbar= (Toolbar) findViewById(R.id.rule_update_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//show arraw key
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        RuleFb=new Firebase("https://private-school-85adb.firebaseio.com/");
        editRule= (EditText) findViewById(R.id.edit_rule);
        setSupportActionBar(toolbar);
        btn_Rule_send= (Button) findViewById(R.id.add_button);
        btn_Rule_send.setOnClickListener(this);

    }
   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.contactmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.home:
                Intent homeintent=new Intent(SchoolServerUpdateRule.this,SchoolServerHomeActivity.class);
                startActivity(homeintent);
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if(editRule.getText().length()<=0){
            Toast.makeText(this,  "ဖြည့်ရန်ကျန်ပါသေးသည်", Toast.LENGTH_SHORT).show();

        }
        else {
            RuleModel ruleModel=new RuleModel();
            ruleModel.setRule(editRule.getText().toString());
            RuleFb.child("Rule").push().setValue(ruleModel);
            editRule.setText(" ");
            Toast.makeText(this, "စည်းကမ်းချက်အား တင်ပြီးပါပြီ။", Toast.LENGTH_SHORT).show();

        }


    }
}
