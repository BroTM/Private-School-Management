package com.upper.team15.privateschool.RegistrationActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;
import com.upper.team15.privateschool.Model.Real_Student_Count;
import com.upper.team15.privateschool.Model.StudentModel;
import com.upper.team15.privateschool.R;

import java.util.Random;

import static com.upper.team15.privateschool.RegistrationActivity.MoneyPayAdapter.money_data;


public class CheckMoneyPayStudent extends AppCompatActivity implements View.OnClickListener {


    ImageView transfer;
    ProgressBar transfer_loading;
    ImageView transfer_refresh;
//    Button btn_confirm,btn_delete;
    TextView tattendClass,  taddress, tfatherName, tfatherNRCNo, tmotherName, tmotherNRCNo, tSBirth, tAttendForm;
    int retrieve_count;
    String username;
    String key="";
    long password;
    String atan="အဋ္ဌမတန်း";
    String className[]={"သူငယ်တန်း",
            "ပထမတန်း " ,
            "ဒုတိယတန်း" ,
            "တတိယတန်း" ,
            "စတုတ္ထတန်း" ,
            "ပဉ္စမတန်း" ,
            "ဆဠမတန်း" ,
            "သတ္တမတန်း" ,
            "အဋ္ဌမတန်း",
            "နဝမတန်း",
            "ဒသမတန်း"};
    String sign_username[]={"01","02","03","04","05","06","07","08","09","10","11"};
    String sign;
    Firebase check_money_student_firebase;
    int posi;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_money_pay_student);
        Firebase.setAndroidContext(CheckMoneyPayStudent.this);
        toolbar= (Toolbar) findViewById(R.id.check_money_pay_student_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("ငွေပေးချေ ရမည့်သူများ စာရင်း");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        posi = getIntent().getIntExtra("posi",0);
        initId();
        updateData();
    }

    private void initId() {
        transfer= (ImageView) findViewById(R.id.small_money_transfer_image);
        transfer_loading= (ProgressBar) findViewById(R.id.money_user_transfer_loading);
        transfer_refresh= (ImageView) findViewById(R.id.money_user_transfer_refresh);
        tattendClass = (TextView) findViewById(R.id.money_attend_class);
        taddress = (TextView) findViewById(R.id.money_address);
        tfatherName = (TextView) findViewById(R.id.money_father_name);
        tfatherNRCNo = (TextView) findViewById(R.id.money_father_NRC);
        tmotherName = (TextView) findViewById(R.id.money_mother_name);
        tmotherNRCNo = (TextView) findViewById(R.id.money_mother_NRC);
        tSBirth = (TextView) findViewById(R.id.money_student_birth);
        tAttendForm = (TextView) findViewById(R.id.money_student_attend_form);
//        btn_confirm = (Button) findViewById(R.id.check_money_btn_confirm);
//        btn_delete = (Button) findViewById(R.id.check_money_btn_cancel);
//        btn_confirm.setOnClickListener(this);
//        btn_delete.setOnClickListener(this);
    }
    private void updateData() {
        tattendClass.setText(money_data.get(posi).getAttendClass());
        taddress.setText(money_data.get(posi).getStudentaddress());
        tfatherName.setText( money_data.get(posi).getFathername());
        tfatherNRCNo.setText(money_data.get(posi).getFatherNRCNO());
        tmotherName.setText(money_data.get(posi).getMothername());
        tmotherNRCNo.setText( money_data.get(posi).getMotherNRCNO());
        tSBirth.setText(money_data.get(posi).getStudent_birth());
        tAttendForm.setText(money_data.get(posi).getAttendForm());
        Glide.with(CheckMoneyPayStudent.this).load(money_data.get(posi).getTrnsferForm()).diskCacheStrategy(DiskCacheStrategy.ALL)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        transfer.setVisibility(View.INVISIBLE);
                        transfer_loading.setVisibility(View.GONE);
                        transfer_refresh.setVisibility(View.VISIBLE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        transfer.setVisibility(View.VISIBLE);
                        transfer_loading.setVisibility(View.GONE);
                        transfer_refresh.setVisibility(View.GONE);
                        return false;
                    }
                }).into(transfer);
        transfer_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(registrationContext, "Continue", Toast.LENGTH_SHORT).show();
                Glide.with(CheckMoneyPayStudent.this).load(money_data.get(posi).getLicenseImage()).diskCacheStrategy(DiskCacheStrategy.ALL)
                        .listener(new RequestListener<String, GlideDrawable>() {
                            @Override
                            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                                transfer.setVisibility(View.INVISIBLE);
                                transfer_loading.setVisibility(View.GONE);
                                transfer_refresh.setVisibility(View.VISIBLE);
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                                transfer.setVisibility(View.VISIBLE);
                                transfer_loading.setVisibility(View.GONE);
                                transfer_refresh.setVisibility(View.GONE);
                                return false;
                            }
                        }).into(transfer);
            }

        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
//            case R.id.check_money_btn_confirm:
////                Toast.makeText(this, "Click", Toast.LENGTH_SHORT).show();
//                check_money_student_firebase.child("Real_Student_Count").child(money_data.get(posi).getAttendClass()).addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        retrieve_count=dataSnapshot.getValue(Real_Student_Count.class).getCount();
//                    }
//
//                    @Override
//                    public void onCancelled(FirebaseError firebaseError) {
//
//                    }
//                });
//
//
//                for(int i=0;i<className.length;i++){
//                    if(money_data.get(posi).getAttendClass().equals(className[i])){
//                        sign=sign_username[i];
//                    }
//
//                }
//                condiCount_key(retrieve_count+1);
//                username="ps"+sign+key;
//                Random r = new Random();
//                long i1 = (r.nextInt(999999) + 100000);
//                password=i1;
//                StudentModel money_pay_model=new StudentModel();
//                money_pay_model.setAttendClass(money_data.get(posi).getAttendClass());
//                money_pay_model.setAttendForm(money_data.get(posi).getAttendForm());
//                money_pay_model.setFathername(money_data.get(posi).getFathername());
//                money_pay_model.setFatherNRCNO(money_data.get(posi).getFatherNRCNO());
//                money_pay_model.setMothername(money_data.get(posi).getMothername());
//                money_pay_model.setMotherNRCNO(money_data.get(posi).getMotherNRCNO());
//                money_pay_model.setStudenName(money_data.get(posi).getStudenName());
//                money_pay_model.setTrnsferForm(money_data.get(posi).getTrnsferForm());
//                money_pay_model.setStudent_birth(money_data.get(posi).getStudent_birth());
//                money_pay_model.setLicenseImage(money_data.get(posi).getLicenseImage());
//                money_pay_model.setPassedClass(money_data.get(posi).getPassedClass());
//                money_pay_model.setStudentaddress(money_data.get(posi).getStudentaddress());
//                money_pay_model.setUsername(username);
//                money_pay_model.setPassword(password);
//                MoneyPayStudentFragment.money_pay_server.child("Real_Student").child(money_data.get(posi).getAttendClass()).child(username).setValue(money_pay_model);
//
//
//
//                Real_Student_Count model=new Real_Student_Count();
//                model.setCount(retrieve_count+1);
//                check_money_student_firebase.child("Real_Student_Count").child(money_data.get(posi).getAttendClass()).setValue(model);
//
//                Query applesQuery = MoneyPayStudentFragment.money_pay_server.child("Money_Pay_Registeration").child(money_data.get(posi).getAttendClass()).orderByChild("studenName").equalTo(money_data.get(posi).getStudenName());
//                money_data.remove(posi);
//                Log.e("Query confirm",applesQuery+"");
//                applesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//
//                        for (DataSnapshot appleSnapshot : dataSnapshot.getChildren()) {
//                               /* RegistrationModel registrationModel = appleSnapshot.getValue(RegistrationModel.class);
//                                money_data.remove(registrationModel);*/
//                            appleSnapshot.getRef().removeValue();
//                        }
//
//                    }
//
//                    @Override
//                    public void onCancelled(FirebaseError firebaseError) {
//                    }
//                });
//                break;
//            case R.id.check_money_btn_cancel:
//                applesQuery = MoneyPayStudentFragment.money_pay_server.child("Money_Pay_Registeration").child(money_data.get(posi).getAttendClass()).orderByChild("studenName").equalTo(money_data.get(posi).getStudenName());
//                money_data.remove(posi);
//                applesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        for (DataSnapshot appleSnapshot : dataSnapshot.getChildren()) {
//                            appleSnapshot.getRef().removeValue();
//                            // gettingRegistrationData();
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(FirebaseError firebaseError) {
//                    }
//                });
//                break;
//        }
//    }
//
//    private void condiCount_key(int i) {
//
//        if(retrieve_count<10) {
//            key = "00" + retrieve_count;
//        }
//        else if(retrieve_count<100)
//            key="0"+retrieve_count;
//
//
//        else if(retrieve_count == 100)
//            key=retrieve_count+"";
    }
}
}
