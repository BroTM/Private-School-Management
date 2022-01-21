package com.upper.team15.privateschool;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.upper.team15.privateschool.HomeActivity.SchoolServerHomeActivity;
import com.upper.team15.privateschool.Model.GuideAccountModel;
import com.upper.team15.privateschool.Model.StudentModel;
import com.upper.team15.privateschool.TeacherServerActivity.TeacherActivity;

public class Login extends AppCompatActivity implements View.OnClickListener {

    Button login;
    EditText editusername,editpass;
    ProgressBar progressBar;

    //login
    String nameClass,codeClass;
    String server_address;
    String allow_no;

    //input
    String name,password;
    int limit=0;

    Firebase fb_real;
    GuideAccountModel model=new GuideAccountModel();


    String child;
    boolean find=false;
    Intent go;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressBar= (ProgressBar) findViewById(R.id.splas_progressbar);
        Firebase.setAndroidContext(Login.this);
        fb_real=new Firebase("https://private-school-85adb.firebaseio.com");

        login= (Button) findViewById(R.id.loginButton);
        editusername= (EditText) findViewById(R.id.username);

        editpass= (EditText) findViewById(R.id.password);
        Log.d("loginn","No Login !!!!!!!!!!");
        login.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.loginButton:

                if(editusername.getText().toString().equals("")||editpass.getText().toString().equals("")){
                    Toast.makeText(this,R.string.enter_again, Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.INVISIBLE);
                }else {
                    progressBar.setVisibility(View.VISIBLE);
                    inputData();
                    inputEmpty();
                    if (isAdmin()) {
                        go = new Intent(Login.this, SchoolServerHomeActivity.class);
                        startActivity(go);
                        progressBar.setVisibility(View.INVISIBLE);
                        finish();
                    } else {
//                if (name.length() == 8 && password.length() < 8 ||name.length()==5&&password.length()==5) {

                        if (codeOk()) {
                            searchClassByKey(name.substring(3, 5));
                            whichClass();
                        } else {
                            incorrect();
                        }
                    }
                }
        }
    }


    private boolean isAdmin() {
        if(name.equals("admin")&&password.equals("admin")){
            return true;
        }
//        else
//            Toast.makeText(this, "not admin", Toast.LENGTH_SHORT).show();
        return false;
    }

    private void whichClass() {
        if(nameClass.equals("empty")) {
            incorrect();
//            Toast.makeText(this, "error in code class", Toast.LENGTH_SHORT).show();
        }
        else {
            toAllowaranceNo();
            whichAllowaranceNo();

        }
    }

    private void whichAllowaranceNo() {
        if(allow_no!=null) {
//            Toast.makeText(this, nameClass+server_address+codeClass+allow_no, Toast.LENGTH_SHORT).show();
            passwordFromFirebase();
        }
        else{
            incorrect();
//            Toast.makeText(this, "error in allow no string", Toast.LENGTH_SHORT).show();
        }
    }

    private void inputEmpty() {
        if(name.equals("")||password.equals("")) {
            incorrect();
//            Toast.makeText(this, "error in empty string", Toast.LENGTH_SHORT).show();
        }
    }

    private void passwordFromFirebase() {
        child=server_address+codeClass+allow_no;
        fb_real.child("GuideAccount").child(nameClass).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ReadFirebase(dataSnapshot);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                incorrect();
            }
        });
    }

    private void ReadFirebase(DataSnapshot dataSnapshot) {
        for (DataSnapshot sh:dataSnapshot.getChildren()){
            if (sh.getValue(GuideAccountModel.class).getUsername().equals(child)){
                find=true;
                model.setAttendclass(sh.getValue(GuideAccountModel.class).getAttendclass());
                model.setPassword(sh.getValue(GuideAccountModel.class).getPassword());
                model.setUsername(sh.getValue(GuideAccountModel.class).getUsername());
                break;
            }else {
                find=false;
            }
        }
        if(find){
            condiPassword(model);
        }
        else{
            incorrect();
        }
    }

    private void condiPassword(GuideAccountModel model) {
        if(model.getPassword().toString().equals(password)){
            addDataToMyProfile(model);
            go=new Intent(Login.this,TeacherActivity.class);
            startActivity(go);
            progressBar.setVisibility(View.INVISIBLE);
            finish();
        }
        else
            incorrect();
    }


    private void addDataToMyProfile(GuideAccountModel model) {
        SharedPreferences shdatamain=getSharedPreferences("MyGuideProfile",MODE_PRIVATE);
        SharedPreferences.Editor editor=shdatamain.edit();
        editor.putString("username",model.getUsername());
        editor.putString("password",model.getPassword());
        editor.putString("class",model.getAttendclass());
        editor.commit();
    }
    private void toAllowaranceNo() {
//        Toast.makeText(this, name.substring(4,7), Toast.LENGTH_SHORT).show();
        try {
            limit = Integer.parseInt(name.substring(5,8));
            if(limit>0&& limit<100)
                allow_no=name.substring(5,8);
        }catch (Exception e){
            allow_no=null;
        }

    }

    private void incorrect() {
        Toast.makeText(this, R.string.incorect_password, Toast.LENGTH_LONG).show();
        editusername.setText("");
        editpass.setText("");
        progressBar.setVisibility(View.INVISIBLE);
        return;

    }

    private void searchClassByKey(String substring) {
        if(substring.equals("01")){
            nameClass="သူငယ်တန်း";
            codeClass="01";
        }
        else if(substring.equals("02")) {
            nameClass = "ပထမတန်း";
            codeClass = "02";
        }
        else if(substring.equals("03")) {
            nameClass = "ဒုတိယတန်း";
            codeClass="03";
        }
        else if(substring.equals("04")) {
            nameClass = "တတိယတန်း";
            codeClass="04";
        }
        else if(substring.equals("05")) {
            nameClass = "စတုတ္ထတန်း";
            codeClass="05";
        }
        else if(substring.equals("06")) {
            nameClass = "ပဉ္စမတန်း";
            codeClass="06";
        }
        else if(substring.equals("07")) {
            nameClass = "ဆဠမတန်း";
            codeClass="07";
        }
        else if(substring.equals("08")) {
            nameClass = "သတ္တမတန်း";
            codeClass="08";
        }
        else if(substring.equals("09")) {
            nameClass = "အဋ္ဌမတန်း";
            codeClass="09";
        }
        else if(substring.equals("10")) {
            nameClass = "နဝမတန်း";
            codeClass="10";
        }
        else if(substring.equals("11")) {
            nameClass = "ဒသမတန်း";
            codeClass="11";
        }
        else
            nameClass="empty";
    }
    private boolean codeOk() {
        if (name.substring(0, 3).equals("psg")) {
            server_address = "psg";
        } else
            return false;
        return true;
    }

    private void inputData() {
        name=editusername.getText().toString();
        password=editpass.getText().toString();
    }
}
