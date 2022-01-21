package com.upper.team15.privateschool.RealStudentActivity;

import android.content.Context;
import android.content.Intent;
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
import com.upper.team15.privateschool.Model.StudentModel;
import com.upper.team15.privateschool.R;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by MAT on 11/10/2017.
 */

public class MyRealAdapter extends RecyclerView.Adapter<MyRealAdapter.MyRegistrationViewHolder> {
    Context moneyContext;
    static ArrayList<StudentModel> check_money_data=new ArrayList<>();
    static  int p;


    public MyRealAdapter(Context schoolServer, ArrayList<StudentModel> data) {
        this.moneyContext = schoolServer;
        this.check_money_data = data;
    }



    @Override
    public MyRealAdapter.MyRegistrationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(moneyContext).inflate(R.layout.activity_schoolserver_real_registration_recycler, parent, false);
        MyRealAdapter.MyRegistrationViewHolder holder = new MyRealAdapter.MyRegistrationViewHolder(v);
        return holder;
    }


    @Override
    public void onBindViewHolder(final MyRealAdapter.MyRegistrationViewHolder holder, final int position) {

        holder.tname.setText(check_money_data.get(position).getStudenName());
        Log.i("Student Name ", check_money_data.get(position).getStudenName());
        Glide.with(moneyContext).load(check_money_data.get(position).getLicenseImage()).diskCacheStrategy(DiskCacheStrategy.ALL)
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
                //Toast.makeText(moneyContext, "Continue", Toast.LENGTH_SHORT).show();
                Glide.with(moneyContext).load(check_money_data.get(position).getLicenseImage()).diskCacheStrategy(DiskCacheStrategy.ALL)
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

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p=position;
                Intent check_student=new Intent(moneyContext,CheckRealStudent.class);
                moneyContext.startActivity(check_student);
            }
        });


    }



    @Override
    public int getItemCount() {
        return check_money_data.size();
    }

    public class MyRegistrationViewHolder extends RecyclerView.ViewHolder {
        ImageView license;
        TextView tname;
        ProgressBar license_loading;
        ImageView license_refresh;
        public MyRegistrationViewHolder(View itemView) {
            super(itemView);
            license = (ImageView) itemView.findViewById(R.id.real_license_image);
            tname = (TextView) itemView.findViewById(R.id.student_real_name);
            license_loading= (ProgressBar) itemView.findViewById(R.id.real_license_loading);
            license_refresh= (ImageView) itemView.findViewById(R.id.real_license_refresh);





        }

    }


}

