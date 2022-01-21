package com.upper.team15.privateschool.AboutGuide;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.upper.team15.privateschool.Model.GuideAccountModel;
import com.upper.team15.privateschool.Model.GuideCountModel;
import com.upper.team15.privateschool.Model.GuideModel;
import com.upper.team15.privateschool.R;

import java.util.Random;


public class Guideinfoupdate extends AppCompatActivity implements View.OnClickListener {

    EditText editName,editGrade,editSubject;
    Firebase guidefirebase;
    Toolbar toolbar;
    static TextView editClass;
    Button btnSend;

    String accountAddress;

    AlertDialog.Builder builder;
    static AlertDialog alertDialog;


    String inputClass="";
    String nameClass,codeClass;

    int password=0;

    GuideCountModel countModel=new GuideCountModel();
    String array_class[]={"သူငယ်တန်း",
            "ပထမတန်း",
            "ဒုတိယတန်း",
            "တတိယတန်း",
            "စတုတ္ထတန်း",
            "ပဉ္စမတန်း",
            "သတ္တမတန်း",
            "အဋ္ဌမတန်း",
            "နဝမတန်း",
            "ဒသမတန်း"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_infoupdate);
        Firebase.setAndroidContext(Guideinfoupdate.this);
        guidefirebase=new Firebase("https://private-school-85adb.firebaseio.com/");

        toolbar= (Toolbar) findViewById(R.id.guidetoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("လေ့ကျင့်ပေးသောဆရာထပ်ထည့်ရန်");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnSend= (Button) findViewById(R.id.btn_guide_send);
        btnSend.setOnClickListener(this);
        editName= (EditText) findViewById(R.id.edit_guide_name);
        editClass= (TextView) findViewById(R.id.edit_guide_class);
        editGrade= (EditText) findViewById(R.id.edit_guide_grade);

        editClass.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_guide_send:
                if (editName.getText().length() <= 0 || editClass.getText().length() <= 0 || editGrade.getText().length() <= 0) {
                    Toast.makeText(this, R.string.please_enter_again, Toast.LENGTH_SHORT).show();
                } else {

                    getCount();
                    countToCodeAddress(countModel.getCount()+1);
                    inputClass=editClass.getText().toString();
                    searchClassByKey(inputClass);

                    GuideModel tnumModel = new GuideModel();
                    tnumModel.setName(editName.getText().toString());
                    tnumModel.setTeacherClass(editClass.getText().toString());
                    tnumModel.setTeacherGrade(editGrade.getText().toString());

                    //username and password
                    accountAddress="psg"+codeClass+accountAddress;
                    tnumModel.setUsername(accountAddress);
                    Random randomPassword=new Random();
                    password=randomPassword.nextInt(999999)+1000000;
                    tnumModel.setPassword(password+"");

                    guidefirebase.child("GuideInformation").push().setValue(tnumModel);

                    //retrieve account
                    GuideAccountModel accountModel=new GuideAccountModel();
                    accountModel.setAttendclass(tnumModel.getTeacherClass());
                    accountModel.setPassword(tnumModel.getPassword());
                    accountModel.setUsername(tnumModel.getUsername());

                    guidefirebase.child("GuideAccount").child(accountModel.
                            getAttendclass()).child(accountModel.getUsername()).setValue(accountModel);

                    //updatecount
                    GuideCountModel count=new GuideCountModel();
                    count.setCount(countModel.getCount()+1);
                    guidefirebase.child("GuideCount").setValue(count);
                    editName.setText(" ");
                    editClass.setText(" ");
                    editGrade.setText(" ");
                    //editSubject.setText(" ");

                    Toast.makeText(this, "လေ့ကျင့်ပေးနေသော ဆရာ ထပ်ထည့်ပြီးပါပြီ။", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.edit_guide_class:
                View view= LayoutInflater.from(Guideinfoupdate.this).inflate(R.layout.guide_class_dialog,null);

                RecyclerView spinner_recycler=(RecyclerView) view.findViewById(R.id.guide_class_dialog_recycler);
                spinner_recycler.setLayoutManager(new LinearLayoutManager(Guideinfoupdate.this));

                MySpinnerAdapter adapter=new MySpinnerAdapter(Guideinfoupdate.this,array_class);
                spinner_recycler.setAdapter(adapter);

                builder=new AlertDialog.Builder(Guideinfoupdate.this);
                builder.setView(view);
                editClass.setText("");
                alertDialog=builder.create();
                alertDialog.show();
                searchClassByKey(editClass.getText().toString());
                break;
        }
    }

    private void countToCodeAddress(int curentcount) {
        if(curentcount<10)
            accountAddress="00"+curentcount;
        else if(curentcount<100)
            accountAddress="0"+curentcount;
        else
            accountAddress=curentcount+"";
    }

    public void getCount() {
        guidefirebase.child("GuideCount").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                countModel=new GuideCountModel();
                countModel.setCount(dataSnapshot.getValue(GuideCountModel.class).getCount());
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
    private void searchClassByKey(String index) {
//        Toast.makeText(this, substring, Toast.LENGTH_SHORT).show();
        if(index.equals("သူငယ်တန်း")){

            codeClass="01";
        }
        else if(index.equals("ပထမတန်း")) {
            codeClass = "02";
        }
        else if(index.equals("ဒုတိယတန်း")) {

            codeClass="03";
        }
        else if(index.equals("တတိယတန်း")) {

            codeClass="04";
        }
        else if(index.equals("စတုတ္ထတန်း")) {
            codeClass="05";
        }
        else if(index.equals("ပဉ္စမတန်း")) {
            codeClass="06";
        }
        else if(index.equals("ဆဠမတန်း")) {
            codeClass="07";
        }
        else if(index.equals("သတ္တမတန်း")) {
            codeClass="08";
        }
        else if(index.equals("အဋ္ဌမတန်း")) {
            codeClass="09";
        }
        else if(index.equals("နဝမတန်း")) {
            codeClass="10";
        }
        else if(index.equals("ဒသမတန်း")) {
            codeClass="11";
        }
    }
}