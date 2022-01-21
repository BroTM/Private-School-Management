package com.upper.team15.privateschool.AboutTeacher;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.upper.team15.privateschool.Model.TeacherNumberModel;
import com.upper.team15.privateschool.R;
import com.firebase.client.Firebase;

import static com.upper.team15.privateschool.R.id.editname;

public class Teacherinfoupdate extends AppCompatActivity implements View.OnClickListener {

    EditText editName,editGrade,editSubject;
    Firebase tNOfb;
    static TextView editClass;
    Toolbar toolbar;
    Button btnSend;

    AlertDialog.Builder builder;
    static AlertDialog alertDialog;

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
        setContentView(R.layout.activity_teacherinfoupdate);
        toolbar= (Toolbar) findViewById(R.id.tinfotoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("သင်ကြားနေသောဆရာထပ်ထည့်ရန်");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnSend= (Button) findViewById(R.id.btn_tSend);
        btnSend.setOnClickListener(this);
        editName= (EditText) findViewById(editname);
        editClass= (TextView) findViewById(R.id.editclass);
        editSubject= (EditText) findViewById(R.id.editsubject);
        editGrade= (EditText) findViewById(R.id.editGrade);

        editClass.setOnClickListener(this);
        Firebase.setAndroidContext(Teacherinfoupdate.this);
        tNOfb=new Firebase("https://private-school-85adb.firebaseio.com/");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_tSend:
                if(editName.getText().length()<=0 || editClass.getText().length()<=0 || editSubject.getText().length()<=0 || editGrade.getText().length()<=0
                        ){
                    Toast.makeText(this,  "ဖြည့်ရန်ကျန်ပါသေးသည်", Toast.LENGTH_SHORT).show();
                }
                else {
                    TeacherNumberModel teacherNumberModel = new TeacherNumberModel();
                    teacherNumberModel.setName(editName.getText().toString());
                    teacherNumberModel.setSubject(editSubject.getText().toString());
                    teacherNumberModel.setTeacherClass(editClass.getText().toString());
                    teacherNumberModel.setTeacherGrade(editGrade.getText().toString());
                    tNOfb.child("TeacherInformation").push().setValue(teacherNumberModel);
                    editName.setText(" ");
                    editClass.setText(" ");
                    editGrade.setText(" ");
                    editSubject.setText(" ");
                    Toast.makeText(this, "သင်ကြားနေသော ဆရာ ထပ်ထည့်ပြီးပါပြီ။", Toast.LENGTH_SHORT).show();

                }
                break;
            case R.id.editclass:

                View view= LayoutInflater.from(Teacherinfoupdate.this).inflate(R.layout.teacher,null);

                RecyclerView spinner_recycler=(RecyclerView) view.findViewById(R.id.teacher_class_dialog_recycler);
                spinner_recycler.setLayoutManager(new LinearLayoutManager(Teacherinfoupdate.this));

                MySpinnerTeacherAdapter adapter=new MySpinnerTeacherAdapter(Teacherinfoupdate.this,array_class);
                spinner_recycler.setAdapter(adapter);

                builder=new AlertDialog.Builder(Teacherinfoupdate.this);
                builder.setView(view);
                editClass.setText("");
                alertDialog=builder.create();
                alertDialog.show();
                break;

        }
    }
}
