package com.upper.team15.privateschool.MessageActivity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.upper.team15.privateschool.Model.MessageModel;
import com.upper.team15.privateschool.Model.StudentModel;
import com.upper.team15.privateschool.R;

import java.util.ArrayList;

import static com.upper.team15.privateschool.MessageActivity.Class_Message_Adapter.message_position;
import static com.upper.team15.privateschool.MessageActivity.Spinner_Adapter_Message.pp;
import static com.upper.team15.privateschool.MessageActivity.Spinner_Adapter_Message.stu_name_position;

public class ServerMessageActivity extends AppCompatActivity implements View.OnClickListener {

    EditText enter_text;
    EditText enter_message_title;
    Firebase message_firebase;
    Toolbar toolbar;
    Button send;
    Spinner student;
    LinearLayout linearLayout;
    RecyclerView.Adapter spinner_adapter;
    String[] spinner_class={"သူငယ်တန်း",
            "ပထမတန်း" ,
            "ဒုတိယတန်း" ,
            "တတိယတန်း" ,
            "စတုတ္ထတန်း" ,
            "ပဉ္စမတန်း" ,
            "ဆဋ္ဌမတန်း" ,
            "သတ္တမတန်း" ,
            "အဋ္ဌမတန်း",
            "နဝမတန်း",
            "ဒသမတန်း"};
    String name;
    ArrayList<StudentModel> allData = new ArrayList<>();
    ArrayList<StudentModel> class1= new ArrayList<>();
    ArrayList<StudentModel> class2 = new ArrayList<>();
    ArrayList<StudentModel> class3 = new ArrayList<>();
    ArrayList<StudentModel> class4 = new ArrayList<>();
    ArrayList<StudentModel> class5 = new ArrayList<>();
    ArrayList<StudentModel> class6 = new ArrayList<>();
    ArrayList<StudentModel> class7 = new ArrayList<>();
    ArrayList<StudentModel> class8 = new ArrayList<>();
    ArrayList<StudentModel> class9 = new ArrayList<>();
    ArrayList<StudentModel> class10 = new ArrayList<>();
    ArrayList<StudentModel> class11=new ArrayList<>();
    ArrayList<StudentModel> registrationData=new ArrayList<>();
    static TextView class_text;
    ProgressBar   progressBar;
    RecyclerView spinner_recyclerView;
    RecyclerView choose_class_recyclerview;
    static TextView student_text;
    LinearLayout linearLayout_name;
    public static AlertDialog   alertDialog;
    AlertDialog.Builder builder;
    static boolean isName=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server_message);
        enter_text= (EditText) findViewById(R.id.message_tile);
        // student= (Spinner) findViewById(R.id.message_real_student_name);
        enter_message_title= (EditText) findViewById(R.id.addtext);
        send= (Button) findViewById(R.id.message_send);
        toolbar= (Toolbar) findViewById(R.id.message_toolbar);
        setSupportActionBar(toolbar);
        class_text= (TextView) findViewById(R.id.class_text);
        student_text= (TextView) findViewById(R.id.student_text);
        linearLayout= (LinearLayout) findViewById(R.id.message_linear);
        linearLayout.setOnClickListener(this);
        linearLayout_name= (LinearLayout) findViewById(R.id.name_linear);
        linearLayout_name.setOnClickListener(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("သတိပေးစာပို့ရန်");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        send.setOnClickListener(this);
        Firebase.setAndroidContext(ServerMessageActivity.this);
        //  ArrayAdapter<String> passed_Spinner_Adapter = new ArrayAdapter<String>(ServerMessageActivity.this, android.R.layout.simple_dropdown_item_1line, studentName);
//        student.setAdapter(passed_Spinner_Adapter);
        message_firebase=new Firebase("https://private-school-85adb.firebaseio.com/");


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.message_send:
                if(enter_text.getText().length()<=0 || enter_message_title.getText().length()<=0){
                    Toast.makeText(this,  "ဖြည့်ရန်ကျန်ပါသေးသည်", Toast.LENGTH_SHORT).show();
                }
                else {
                    MessageModel mModel = new MessageModel();
                    mModel.setTitle(enter_text.getText().toString());
                    mModel.setAbout(enter_message_title.getText().toString());

                    message_firebase.child("Message").child(class_text.getText().toString()).child(pp.get(stu_name_position).getUsername().toString()).push().setValue(mModel);
                    enter_text.setText("");
                    enter_message_title.setText(" ");
                    student_text.setText(" ");
                    class_text.setText(" ");
                    Toast.makeText(this, "သတိပေးစာ ပေးပို့ပီးပါပြီ။", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.message_linear:
                View v2= LayoutInflater.from(ServerMessageActivity.this).inflate(R.layout.homework_class_choose_dialog,null);
                choose_class_recyclerview= (RecyclerView) v2.findViewById(R.id.choose_class_dialog_message);
                choose_class_recyclerview.setLayoutManager(new LinearLayoutManager(ServerMessageActivity.this));
                spinner_adapter=new Class_Message_Adapter(ServerMessageActivity.this,spinner_class);
                choose_class_recyclerview.setAdapter(spinner_adapter);
                builder=new AlertDialog.Builder(ServerMessageActivity.this);
                builder.setView(v2);
                alertDialog=builder.create();
                alertDialog.show();
                break;
            case R.id.name_linear:

                View v3= LayoutInflater.from(ServerMessageActivity.this).inflate(R.layout.homework_spinner_dialog,null);
                spinner_recyclerView= (RecyclerView) v3.findViewById(R.id.spinner_dialog_message);
                progressBar= (ProgressBar) v3.findViewById(R.id.message_progress);
                progressBar.setVisibility(View.VISIBLE);
                spinner_recyclerView.setLayoutManager(new LinearLayoutManager(ServerMessageActivity.this));
                builder=new AlertDialog.Builder(ServerMessageActivity.this);
                //condiPosition(message_position);
                if(message_position == 0){
                    class1Data();

                }
                else if(message_position == 1){
                    class2Data();
                }
                else if(message_position == 2){
                    class3Data();
                }
                else if (message_position == 3){
                    class4Data();
                }
                else if (message_position == 4){
                   class5Data();
                }
                else if (message_position == 5){
                    class6Data();
                }
                else if (message_position == 6){
                    class7Data();
                }
                else if (message_position == 7){
                    class8Data();
                }
                else if (message_position == 8){
                    class9Data();
                }
                else if (message_position == 9){
                    class10Data();
                }
                else if (message_position == 10){
                    class11Data();
                }
                builder.setView(v3);
                student_text.setText("");
                alertDialog=builder.create();
                alertDialog.show();
                break;
        }
    }


    private void class2Data() {
        message_firebase.child("Real_Student").child("ပထမတန်း").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                class2.clear();
                class2List(dataSnapshot);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    private void class2List(DataSnapshot dataSnapshot) {
        for (DataSnapshot sh : dataSnapshot.getChildren()) {
            StudentModel smodel=new StudentModel();
            smodel.setStudenName(sh.getValue(StudentModel.class).getStudenName()) ;
            smodel.setUsername(sh.getValue(StudentModel.class).getUsername());
            class2.add(smodel);
        }
        spinner_adapter = new Spinner_Adapter_Message(ServerMessageActivity.this, class2);
        progressBar.setVisibility(View.GONE);
        spinner_recyclerView.setAdapter(spinner_adapter);
    }


    private void class11Data() {
        message_firebase.child("Real_Student").child("ဒသမတန်း").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                class11.clear();
                class11List(dataSnapshot);
            }



            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    private void class11List(DataSnapshot dataSnapshot) {
        for (DataSnapshot sh : dataSnapshot.getChildren()) {
            StudentModel smodel=new StudentModel();
            smodel.setStudenName(sh.getValue(StudentModel.class).getStudenName()) ;
            smodel.setUsername(sh.getValue(StudentModel.class).getUsername());
            class11.add(smodel);
        }
        spinner_adapter = new Spinner_Adapter_Message(ServerMessageActivity.this, class11);
        progressBar.setVisibility(View.GONE);
        spinner_recyclerView.setAdapter(spinner_adapter);
    }

    private void class10Data() {
        message_firebase.child("Real_Student").child("နဝမတန်း").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                class10.clear();
                class10List(dataSnapshot);
            }



            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    private void class10List(DataSnapshot dataSnapshot) {
        for (DataSnapshot sh : dataSnapshot.getChildren()) {
            StudentModel smodel=new StudentModel();
            smodel.setStudenName(sh.getValue(StudentModel.class).getStudenName()) ;
            smodel.setUsername(sh.getValue(StudentModel.class).getUsername());
            class10.add(smodel);
        }
        spinner_adapter = new Spinner_Adapter_Message(ServerMessageActivity.this, class10);
        progressBar.setVisibility(View.GONE);
        spinner_recyclerView.setAdapter(spinner_adapter);
    }

    private void class9Data() {
        message_firebase.child("Real_Student").child("အဋ္ဌမတန်း").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                class9.clear();
                class9List(dataSnapshot);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    private void class9List(DataSnapshot dataSnapshot) {
        for (DataSnapshot sh : dataSnapshot.getChildren()) {
            StudentModel smodel=new StudentModel();
            smodel.setStudenName(sh.getValue(StudentModel.class).getStudenName()) ;
            smodel.setUsername(sh.getValue(StudentModel.class).getUsername());
            class9.add(smodel);
        }
        spinner_adapter = new Spinner_Adapter_Message(ServerMessageActivity.this, class9);
        progressBar.setVisibility(View.GONE);
        spinner_recyclerView.setAdapter(spinner_adapter);
    }

    private void class8Data() {
        message_firebase.child("Real_Student").child("သတ္တမတန်း").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                class8.clear();
                class8List(dataSnapshot);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    private void class8List(DataSnapshot dataSnapshot) {
        for (DataSnapshot sh : dataSnapshot.getChildren()) {
            StudentModel smodel=new StudentModel();
            smodel.setStudenName(sh.getValue(StudentModel.class).getStudenName()) ;
            smodel.setUsername(sh.getValue(StudentModel.class).getUsername());
            class8.add(smodel);
        }
        spinner_adapter = new Spinner_Adapter_Message(ServerMessageActivity.this, class8);
        progressBar.setVisibility(View.GONE);
        spinner_recyclerView.setAdapter(spinner_adapter);
    }

    private void class7Data() {
        message_firebase.child("Real_Student").child("ဆဋ္ဌမတန်း").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                class7.clear();
                class7List(dataSnapshot);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    private void class7List(DataSnapshot dataSnapshot) {
        for (DataSnapshot sh : dataSnapshot.getChildren()) {
            StudentModel smodel=new StudentModel();
            smodel.setStudenName(sh.getValue(StudentModel.class).getStudenName()) ;
            smodel.setUsername(sh.getValue(StudentModel.class).getUsername());
            class7.add(smodel);
        }
        spinner_adapter = new Spinner_Adapter_Message(ServerMessageActivity.this, class7);
        progressBar.setVisibility(View.GONE);
        spinner_recyclerView.setAdapter(spinner_adapter);
    }

    private void class6Data() {
        message_firebase.child("Real_Student").child("ပဉ္စမတန်း").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                class6.clear();
                class6List(dataSnapshot);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    private void class6List(DataSnapshot dataSnapshot) {
        for (DataSnapshot sh : dataSnapshot.getChildren()) {
            StudentModel smodel=new StudentModel();
            smodel.setStudenName(sh.getValue(StudentModel.class).getStudenName()) ;
            smodel.setUsername(sh.getValue(StudentModel.class).getUsername());
            class6.add(smodel);
        }
        spinner_adapter = new Spinner_Adapter_Message(ServerMessageActivity.this, class6);
        progressBar.setVisibility(View.GONE);
        spinner_recyclerView.setAdapter(spinner_adapter);
    }

    private void class5Data() {

        message_firebase.child("Real_Student").child("စတုတ္ထတန်း").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                class5.clear();
                class5List(dataSnapshot);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    private void class5List(DataSnapshot dataSnapshot) {
        for (DataSnapshot sh : dataSnapshot.getChildren()) {
            StudentModel smodel=new StudentModel();
            smodel.setStudenName(sh.getValue(StudentModel.class).getStudenName()) ;
            smodel.setUsername(sh.getValue(StudentModel.class).getUsername());
            class5.add(smodel);
        }
        spinner_adapter = new Spinner_Adapter_Message(ServerMessageActivity.this, class5);
        progressBar.setVisibility(View.GONE);
        spinner_recyclerView.setAdapter(spinner_adapter);
    }

    private void class4Data() {

        message_firebase.child("Real_Student").child("တတိယတန်း").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                class4.clear();
                class4List(dataSnapshot);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    private void class4List(DataSnapshot dataSnapshot) {
        for (DataSnapshot sh : dataSnapshot.getChildren()) {
            StudentModel smodel=new StudentModel();
            smodel.setStudenName(sh.getValue(StudentModel.class).getStudenName()) ;
            smodel.setUsername(sh.getValue(StudentModel.class).getUsername());
            class4.add(smodel);
        }
        spinner_adapter = new Spinner_Adapter_Message(ServerMessageActivity.this, class4);
        progressBar.setVisibility(View.GONE);
        spinner_recyclerView.setAdapter(spinner_adapter);
    }

    private void class3Data() {
        message_firebase.child("Real_Student").child("ဒုတိယတန်း").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                class3.clear();
                class3List(dataSnapshot);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    private void class3List(DataSnapshot dataSnapshot) {
        for (DataSnapshot sh : dataSnapshot.getChildren()) {
            StudentModel smodel=new StudentModel();
            smodel.setStudenName(sh.getValue(StudentModel.class).getStudenName()) ;
            smodel.setUsername(sh.getValue(StudentModel.class).getUsername());
            class3.add(smodel);
        }
        spinner_adapter = new Spinner_Adapter_Message(ServerMessageActivity.this, class3);
        progressBar.setVisibility(View.GONE);
        spinner_recyclerView.setAdapter(spinner_adapter);
    }


    private void class1Data() {
        Log.e("counting", "Counting1");

        message_firebase.child("Real_Student").child("သူငယ်တန်း").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e("counting", "looping");
                registrationData.clear();
                upDateData(dataSnapshot);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    private void upDateData(DataSnapshot dataSnapshot) {
        Log.i("DATASNAPSHOT ::", "Start");
        for (DataSnapshot sh : dataSnapshot.getChildren()) {
            StudentModel smodel=new StudentModel();
            smodel.setStudenName(sh.getValue(StudentModel.class).getStudenName()) ;
            smodel.setUsername(sh.getValue(StudentModel.class).getUsername());
            registrationData.add(smodel);

        }

        spinner_adapter=new Spinner_Adapter_Message(ServerMessageActivity.this,registrationData);
        progressBar.setVisibility(View.GONE);
        spinner_recyclerView.setAdapter(spinner_adapter);


    }


    static ArrayList<StudentModel> getData(Context context, DataSnapshot dataSnapShot){
        ArrayList<StudentModel> myList=new ArrayList<StudentModel>();
        for (DataSnapshot sh:dataSnapShot.getChildren()){

            StudentModel smodel=new StudentModel();
            smodel.setStudenName(sh.getValue(StudentModel.class).getStudenName()) ;
            myList.add(smodel);
            //Log("Class1!!!!!!!",sh.getValue(StudentModel.class)+"");
        }
        return myList;
    }
}




