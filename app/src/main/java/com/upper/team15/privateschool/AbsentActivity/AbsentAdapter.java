package com.upper.team15.privateschool.AbsentActivity;


import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
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
import com.upper.team15.privateschool.Model.AbsentModel;
import com.upper.team15.privateschool.R;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.util.ArrayList;


/**
 * Created by Aspire on 10/18/2017.
 */

public class AbsentAdapter extends RecyclerView.Adapter<AbsentAdapter.MyEventHolder>{
    ArrayList<AbsentModel> absentData = new ArrayList<>();
    Context context;
    private SparseBooleanArray expandState = new SparseBooleanArray();

    public AbsentAdapter(Context schoolEvents, ArrayList<AbsentModel> studentData) {
        this.context=schoolEvents;
        this.absentData=studentData;
        for (int i = 0; i < absentData.size(); i++) {
            expandState.append(i, false);
        }
    }



    @Override
    public AbsentAdapter.MyEventHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.activity_absent_adapter,parent,false);
        AbsentAdapter.MyEventHolder eventholder=new AbsentAdapter.MyEventHolder(v);
        return eventholder;

    }

    @Override
    public void onBindViewHolder(final AbsentAdapter.MyEventHolder holder, final int position) {

        final AbsentModel item = absentData.get(position);

        holder.time.setText(absentData.get(position).getTime());

        holder.date.setText(absentData.get(position).getDate());
        holder.name.setText(absentData.get(position).getName());
        holder.grade.setText("အတန်း                     "+absentData.get(position).getGrade());
        holder.start_date.setText("စတင်ခွင့်ယူမည့်ရက်       "+(absentData.get(position).getStart_date()));
        holder.end_date.setText(  "နောက်ဆုံးခွင့်ယူရက်       "+(absentData.get(position).getEnd_date()));
        holder.reason.setText(    "အကြောင်းအရာ            "+(absentData.get(position).getReason()));
        Glide.with(context).load(absentData.get(position).getAbsent_image()).diskCacheStrategy(DiskCacheStrategy.ALL)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        holder.absentImage.setVisibility(View.INVISIBLE);
                        holder.loading.setVisibility(View.GONE);
                        holder.refresh.setVisibility(View.VISIBLE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        holder.absentImage.setVisibility(View.VISIBLE);
                        holder.loading.setVisibility(View.GONE);
                        holder.refresh.setVisibility(View.GONE);
                        return false;
                    }
                }).into(holder.absentImage);
        Glide.with(context).load(absentData.get(position).getUserimage()).diskCacheStrategy(DiskCacheStrategy.ALL)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        holder.student_image.setVisibility(View.INVISIBLE);
                        holder.loading.setVisibility(View.GONE);
                        holder.refresh.setVisibility(View.VISIBLE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        holder.student_image.setVisibility(View.VISIBLE);
                        holder.loading.setVisibility(View.GONE);
                        holder.refresh.setVisibility(View.GONE);
                        return false;
                    }
                }).into(holder.student_image);
        holder.refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(registrationContext, "Continue", Toast.LENGTH_SHORT).show();
                Glide.with(context).load(absentData.get(position).getAbsent_image()).diskCacheStrategy(DiskCacheStrategy.ALL)
                        .listener(new RequestListener<String, GlideDrawable>() {
                            @Override
                            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                                holder.absentImage.setVisibility(View.INVISIBLE);
                                holder.loading.setVisibility(View.GONE);
                                holder.refresh.setVisibility(View.VISIBLE);
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                                holder.absentImage.setVisibility(View.VISIBLE);
                                holder.loading.setVisibility(View.GONE);
                                holder.refresh.setVisibility(View.GONE);
                                return false;
                            }
                        }).into(holder.absentImage);
            }

        });
        holder.expandableLayout.setExpanded(expandState.get(position));
        holder.btn_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.expandableLayout.toggle();
            }
        });


    }





    @Override
    public int getItemCount() {
        return absentData.size();
    }




    public class MyEventHolder extends RecyclerView.ViewHolder {

        TextView  start_date, end_date, reason;
        ImageView absentImage;
        TextView student_name;
        TextView name;
        ImageView refresh;
        ImageView student_image;
        ProgressBar loading;
        TextView grade;
        ExpandableLayout expandableLayout;
        TextView time,date;

        Button btn_click;
        public MyEventHolder(View itemView) {
            super(itemView);
            student_image= (ImageView) itemView.findViewById(R.id.real_stu_image);
            time= (TextView) itemView.findViewById(R.id.absent_adapter_time);
            date= (TextView) itemView.findViewById(R.id.absent_adapter_date);
            student_name= (TextView) itemView.findViewById(R.id.real_stu_name);
            start_date= (TextView) itemView.findViewById(R.id.text2);
            end_date= (TextView) itemView.findViewById(R.id.text3);
            reason= (TextView) itemView.findViewById(R.id.text4);
            absentImage= (ImageView) itemView.findViewById(R.id.image5);
            expandableLayout= (ExpandableLayout) itemView.findViewById(R.id.expandable_layout);
            name= (TextView) itemView.findViewById(R.id.real_stu_name);
            btn_click= (Button) itemView.findViewById(R.id.btnclick);
            grade= (TextView) itemView.findViewById(R.id.absent_grade);
            loading= (ProgressBar) itemView.findViewById(R.id.absent_loading);
            refresh= (ImageView) itemView.findViewById(R.id.absent_refresh);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    expandableLayout.toggle();
                }
            });
        }
    }

}
