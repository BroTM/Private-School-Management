package com.upper.team15.privateschool.RegistrationActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

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
import com.upper.team15.privateschool.Model.Money_Pay_Model;
import com.upper.team15.privateschool.Model.RegistrationModel;
import com.upper.team15.privateschool.R;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.util.ArrayList;

import static com.upper.team15.privateschool.RegistrationActivity.MoneyPayAdapter.money_data;
import static com.upper.team15.privateschool.RegistrationActivity.MyRegistrationAdapter.pos;
import static com.upper.team15.privateschool.RegistrationActivity.MyRegistrationAdapter.registrationData;

public class CheckStudentRegistration extends AppCompatActivity implements View.OnClickListener {

    ImageView transfer;
    ProgressBar transfer_loading;
    ImageView transfer_refresh;
    Button btn_confirm,btn_delete;
    Firebase money_firebase;
    Toolbar toolbar;
    TextView tattendClass,  taddress, tfatherName, tfatherNRCNo, tmotherName, tmotherNRCNo, tSBirth, tAttendForm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(CheckStudentRegistration.this);
        money_firebase=new Firebase("https://private-school-85adb.firebaseio.com/");
        setContentView(R.layout.activity_check_student_registration);
        initId();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("ကျောင်းအပ်ထားသူများ စာရင်း");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        updateData();

    }



    private void initId() {
        transfer= (ImageView) findViewById(R.id.transfer_image);
        toolbar= (Toolbar) findViewById(R.id.check_student_registration_toolbar);
        transfer_loading= (ProgressBar) findViewById(R.id.user_transfer_loading);
        transfer_refresh= (ImageView) findViewById(R.id.user_transfer_refresh);
        tattendClass = (TextView) findViewById(R.id.attend_class);
        taddress = (TextView) findViewById(R.id.address);
        tfatherName = (TextView) findViewById(R.id.father_name);
        tfatherNRCNo = (TextView) findViewById(R.id.father_NRC);
        tmotherName = (TextView) findViewById(R.id.mother_name);
        tmotherNRCNo = (TextView) findViewById(R.id.mother_NRC);
        tSBirth = (TextView) findViewById(R.id.student_birth);
        tAttendForm = (TextView) findViewById(R.id.student_attend_form);
        btn_confirm = (Button) findViewById(R.id.check_btn_confirm);
        btn_delete = (Button) findViewById(R.id.check_btn_cancel);
        btn_confirm.setOnClickListener(this);
        btn_delete.setOnClickListener(this);
    }

    private void updateData() {
        tattendClass.setText(registrationData.get(pos).getAttendClass());
        taddress.setText(registrationData.get(pos).getStudentaddress());
        tfatherName.setText( registrationData.get(pos).getFathername());
        tfatherNRCNo.setText(registrationData.get(pos).getFatherNRCNO());
        tmotherName.setText(registrationData.get(pos).getMothername());
        tmotherNRCNo.setText( registrationData.get(pos).getMotherNRCNO());
        tSBirth.setText(registrationData.get(pos).getStudent_birth());
        tAttendForm.setText(registrationData.get(pos).getAttendForm());
        //transfer.set(sh.getValue(RegistrationModel.class).getTrnsferForm());
        Glide.with(CheckStudentRegistration.this).load(registrationData.get(pos).getTrnsferForm()).diskCacheStrategy(DiskCacheStrategy.ALL)
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
                Glide.with(CheckStudentRegistration.this).load(money_data.get(pos).getLicenseImage()).diskCacheStrategy(DiskCacheStrategy.ALL)
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
            case R.id.check_btn_confirm:
                Money_Pay_Model money_pay_model=new Money_Pay_Model();
                money_pay_model.setAttendClass(registrationData.get(pos).getAttendClass());
                money_pay_model.setAttendForm(registrationData.get(pos).getAttendForm());
                money_pay_model.setFathername(registrationData.get(pos).getFathername());
                money_pay_model.setFatherNRCNO(registrationData.get(pos).getFatherNRCNO());
                money_pay_model.setMothername(registrationData.get(pos).getMothername());
                money_pay_model.setMotherNRCNO(registrationData.get(pos).getMotherNRCNO());
                money_pay_model.setStudenName(registrationData.get(pos).getStudenName());
                money_pay_model.setTrnsferForm(registrationData.get(pos).getTrnsferForm());
                money_pay_model.setStudent_birth(registrationData.get(pos).getStudent_birth());
                money_pay_model.setLicenseImage(registrationData.get(pos).getLicenseImage());
                money_pay_model.setPassedClass(registrationData.get(pos).getPassedClass());
                money_pay_model.setStudentaddress(registrationData.get(pos).getStudentaddress());
                money_pay_model.setIsRealStudent("false");
                money_firebase.child("Money_Pay_Registeration").child(money_pay_model.getAttendClass()).child(money_pay_model.getStudenName()).setValue(money_pay_model);
                //SchoolRegistration.firebase_School_Server.child()
                /*SchoolRegistration.firebase_School_Server.child("Money_Pay_Form").child(registrationData.get(pos).getAttendClass()).child(registrationData.get(pos).getStudenName()).setValue(registrationData.get(pos));
                SchoolRegistration.firebase_School_Server.child("Money_Pay_Form").child(registrationData.get(pos).getAttendClass()).child("isRealStudent").setValue("false");*/
                Query applesQuery  = SchoolRegistration.firebase_School_Server.child("RegistrationForm").child(registrationData.get(pos).getAttendClass()).orderByChild("studenName").equalTo(registrationData.get(pos).getStudenName());
               /* tattendClass.setVisibility(View.GONE);
                taddress.setVisibility(View.GONE);
                tfatherName.setVisibility(View.GONE);
                tfatherNRCNo.setVisibility(View.GONE);
                tmotherName.setVisibility(View.GONE);
                tmotherNRCNo.setVisibility(View.GONE);
                tSBirth.setVisibility(View.GONE);
                tAttendForm.setVisibility(View.GONE);
                transfer.setVisibility(View.GONE);
                btn_confirm.setVisibility(View.GONE);
                btn_delete.setVisibility(View.GONE);*/
                registrationData.remove(pos);
                Log.e("Query confirm",applesQuery+"");

                applesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        for (DataSnapshot appleSnapshot : dataSnapshot.getChildren()) {
                               /* RegistrationModel registrationModel = appleSnapshot.getValue(RegistrationModel.class);
                                registrationData.remove(registrationModel);*/
                            appleSnapshot.getRef().removeValue();

                        }

                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {
                    }

                });
                break;
            case R.id.check_btn_cancel:
                applesQuery = SchoolRegistration.firebase_School_Server.child("RegistrationForm").child(registrationData.get(pos).getAttendClass()).orderByChild("studenName").equalTo(registrationData.get(pos).getStudenName());
                registrationData.remove(pos);
                applesQuery.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot appleSnapshot : dataSnapshot.getChildren()) {
                            appleSnapshot.getRef().removeValue();

                            // gettingRegistrationData();

                            // need to send notification to user
                        }
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {
                    }
                });

        }
    }
}
