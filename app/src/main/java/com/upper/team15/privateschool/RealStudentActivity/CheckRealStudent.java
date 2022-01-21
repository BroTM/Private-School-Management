package com.upper.team15.privateschool.RealStudentActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.upper.team15.privateschool.R;
import static com.upper.team15.privateschool.RealStudentActivity.MyRealAdapter.check_money_data;
import static com.upper.team15.privateschool.RealStudentActivity.MyRealAdapter.p;
public class CheckRealStudent extends AppCompatActivity {


    ImageView transfer;
    ProgressBar transfer_loading;
    ImageView transfer_refresh;
    TextView cphone;
    private TextView parentPassword,parentId;
    TextView tattendClass,  taddress, tfatherName, tfatherNRCNo, tmotherName, tmotherNRCNo, tSBirth, tAttendForm;

    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_real_student);
        toolbar= (Toolbar) findViewById(R.id.student_registration_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("ကျောင်းသားစာရင်း");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initId();
        updateData();
    }

    private void updateData() {

            tattendClass.setText(check_money_data.get(p).getAttendClass());
            taddress.setText(check_money_data.get(p).getStudentaddress());
            tfatherName.setText(check_money_data.get(p).getFathername());
            tfatherNRCNo.setText(check_money_data.get(p).getFatherNRCNO());
            tmotherName.setText(check_money_data.get(p).getMothername());
            tmotherNRCNo.setText( check_money_data.get(p).getMotherNRCNO());
        parentId.setText(check_money_data.get(p).getParentId());
        parentPassword.setText(check_money_data.get(p).getParentPassword());
            tSBirth.setText(check_money_data.get(p).getStudent_birth());
            tAttendForm.setText(check_money_data.get(p).getAttendForm());
            Glide.with(CheckRealStudent.this).load(check_money_data.get(p).getTrnsferForm()).diskCacheStrategy(DiskCacheStrategy.ALL)
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
                    Glide.with(CheckRealStudent.this).load(check_money_data.get(p).getLicenseImage()).diskCacheStrategy(DiskCacheStrategy.ALL)
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


    private void initId() {
        transfer= (ImageView) findViewById(R.id.ctransfer_image);
        transfer_loading= (ProgressBar) findViewById(R.id.cuser_transfer_loading);
        transfer_refresh= (ImageView) findViewById(R.id.cuser_transfer_refresh);
        tattendClass = (TextView) findViewById(R.id.cattend_class);
        taddress = (TextView) findViewById(R.id.caddress);
        tfatherName = (TextView) findViewById(R.id.cfather_name);
        tfatherNRCNo = (TextView) findViewById(R.id.cfather_NRC);
        tmotherName = (TextView) findViewById(R.id.cmother_name);
        tmotherNRCNo = (TextView) findViewById(R.id.cmother_NRC);
        tSBirth = (TextView) findViewById(R.id.cstudent_birth);
        parentId= (TextView) findViewById(R.id.parent_id);
        parentPassword= (TextView) findViewById(R.id.parent_password);
        tAttendForm = (TextView) findViewById(R.id.cstudent_attend_form);


    }
}
