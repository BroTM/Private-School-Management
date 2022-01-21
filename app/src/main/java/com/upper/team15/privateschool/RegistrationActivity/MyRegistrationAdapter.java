package com.upper.team15.privateschool.RegistrationActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.firebase.client.Firebase;
import com.upper.team15.privateschool.Model.Money_Pay_Model;
import com.upper.team15.privateschool.Model.RegistrationModel;
import com.upper.team15.privateschool.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Aspire on 10/17/2017.
 */

public class MyRegistrationAdapter extends RecyclerView.Adapter<MyRegistrationAdapter.MyRegistrationViewHolder> {
    Context registrationContext;
    static ArrayList<RegistrationModel> registrationData=new ArrayList<>();
    static  int pos;
    Firebase money_firebase;
    Handler setDelay=new Handler();
    Runnable startDelay;

    public MyRegistrationAdapter(Context schoolServer, ArrayList<RegistrationModel> data) {
        this.registrationContext = schoolServer;
        this.registrationData = data;
    }

    public void swapList(ArrayList<RegistrationModel> data){
        this.registrationData = data;
        notifyDataSetChanged();
    }


    @Override
    public MyRegistrationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(registrationContext).inflate(R.layout.activity_schoolserver_registration_recycler, parent, false);
        MyRegistrationViewHolder holder = new MyRegistrationViewHolder(v);
        return holder;
    }


    @Override
    public void onBindViewHolder(final MyRegistrationViewHolder holder, final int position) {

        holder.tname.setText(registrationData.get(position).getStudenName());
        Glide.with(registrationContext).load(registrationData.get(position).getLicenseImage()).diskCacheStrategy(DiskCacheStrategy.ALL)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        holder.license.setVisibility(View.INVISIBLE);
                        holder.license_loading.setVisibility(View.GONE);
                        holder.license_refresh.setVisibility(View.VISIBLE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        holder.license.setVisibility(View.VISIBLE);
                        holder.license_loading.setVisibility(View.GONE);
                        holder.license_refresh.setVisibility(View.GONE);
                        return false;
                    }
                }).into(holder.license);
        holder.license_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(registrationContext, "Continue", Toast.LENGTH_SHORT).show();
                Glide.with(registrationContext).load(registrationData.get(position).getLicenseImage()).diskCacheStrategy(DiskCacheStrategy.ALL)
                        .listener(new RequestListener<String, GlideDrawable>() {
                            @Override
                            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                                holder.license.setVisibility(View.INVISIBLE);
                                holder.license_loading.setVisibility(View.GONE);
                                holder.license_refresh.setVisibility(View.VISIBLE);
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                                holder.license.setVisibility(View.VISIBLE);
                                holder.license_loading.setVisibility(View.GONE);
                                holder.license_refresh.setVisibility(View.GONE);
                                return false;
                            }
                        }).into(holder.license);
            }

        });
        holder.btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Firebase.setAndroidContext(registrationContext);
                money_firebase=new Firebase("https://private-school-85adb.firebaseio.com/");
                Money_Pay_Model money_pay_model=new Money_Pay_Model();
                money_pay_model.setAttendClass(registrationData.get(position).getAttendClass());
                money_pay_model.setAttendForm(registrationData.get(position).getAttendForm());
                money_pay_model.setFathername(registrationData.get(position).getFathername());
                money_pay_model.setFatherNRCNO(registrationData.get(position).getFatherNRCNO());
                money_pay_model.setMothername(registrationData.get(position).getMothername());
                money_pay_model.setMotherNRCNO(registrationData.get(position).getMotherNRCNO());
                money_pay_model.setStudenName(registrationData.get(position).getStudenName());
                money_pay_model.setTrnsferForm(registrationData.get(position).getTrnsferForm());
                money_pay_model.setStudent_birth(registrationData.get(position).getStudent_birth());
                money_pay_model.setLicenseImage(registrationData.get(position).getLicenseImage());
                money_pay_model.setPassedClass(registrationData.get(position).getPassedClass());
                money_pay_model.setStudentaddress(registrationData.get(position).getStudentaddress());

                money_pay_model.setPhoneno(registrationData.get(position).getPhoneno());
                money_pay_model.setParentPassword(registrationData.get(position).getParentPassword());
                money_pay_model.setParentId(registrationData.get(position).getParentId());


                money_pay_model.setIsRealStudent("false");
                money_firebase.child("Money_Pay_Registeration").child(money_pay_model.getAttendClass()).child(money_pay_model.getStudenName()).setValue(money_pay_model);
                //SchoolRegistration.firebase_School_Server.child()
                /*SchoolRegistration.firebase_School_Server.child("Money_Pay_Form").child(registrationData.get(position).getAttendClass()).child(registrationData.get(position).getStudenName()).setValue(registrationData.get(position));
                SchoolRegistration.firebase_School_Server.child("Money_Pay_Form").child(registrationData.get(position).getAttendClass()).child("isRealStudent").setValue("false");*/
                Query applesQuery = SchoolRegistration.firebase_School_Server.child("RegistrationForm").child(registrationData.get(position).getAttendClass()).orderByChild("studenName").equalTo(registrationData.get(position).getStudenName());

                Log.e("Query confirm",applesQuery+"");
                applesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        for (DataSnapshot appleSnapshot : dataSnapshot.getChildren()) {
                               /* RegistrationModel registrationModel = appleSnapshot.getValue(RegistrationModel.class);
                                registrationData.remove(registrationModel);*/
                            appleSnapshot.getRef().removeValue();
                            registrationData.remove(position);
                            notifyDataSetChanged();
                        }

                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {
                    }
                });
            }
        });
        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Query applesQuery = SchoolRegistration.firebase_School_Server.child("RegistrationForm").child(registrationData.get(position).getAttendClass()).orderByChild("studenName").equalTo(registrationData.get(position).getStudenName());
                registrationData.remove(position);
                applesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot appleSnapshot : dataSnapshot.getChildren()) {
                            appleSnapshot.getRef().removeValue();
                            notifyDataSetChanged();

                            // gettingRegistrationData();

                            // need to send notification to user
                        }
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {
                    }
                });

            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pos=position;
                Intent check_student=new Intent(registrationContext,CheckStudentRegistration.class);
                registrationContext.startActivity(check_student);
            }
        });


    }




    @Override
    public int getItemCount() {
        return registrationData.size();
    }

    public class MyRegistrationViewHolder extends RecyclerView.ViewHolder {
        ImageView license;
        TextView tname;
        ProgressBar license_loading;
        ImageView license_refresh;
        Button btn_confirm,btn_delete;
        public MyRegistrationViewHolder(View itemView) {
            super(itemView);
            license = (ImageView) itemView.findViewById(R.id.license_image);
            tname = (TextView) itemView.findViewById(R.id.student_registration_name);
            license_loading= (ProgressBar) itemView.findViewById(R.id.user_license_loading);
            license_refresh= (ImageView) itemView.findViewById(R.id.user_license_refresh);
            btn_confirm = (Button) itemView.findViewById(R.id.btn_register_confirm);
            btn_delete = (Button) itemView.findViewById(R.id.btn_register_cancel);




        }

    }


}
