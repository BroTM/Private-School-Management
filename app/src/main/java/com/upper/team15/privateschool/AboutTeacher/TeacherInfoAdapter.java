package com.upper.team15.privateschool.AboutTeacher;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.upper.team15.privateschool.Model.TeacherNumberModel;
import com.upper.team15.privateschool.R;

import java.util.ArrayList;

/**
 * Created by Aspire on 10/19/2017.
 */

public class TeacherInfoAdapter extends RecyclerView.Adapter<TeacherInfoAdapter.TInfoHolder> {
    ArrayList<TeacherNumberModel> tdata;
    Context context;
    public TeacherInfoAdapter(Context teacherInfo, ArrayList<TeacherNumberModel> TInfo) {
        this.context=teacherInfo;
        tdata=new ArrayList<TeacherNumberModel>();
        this.tdata=TInfo;

    }

    @Override
    public TInfoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.teacher_see_info_cardveiw,parent,false);
        TInfoHolder holder=new TInfoHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(TInfoHolder holder, int position) {
        holder.tech_name.setText(tdata.get(position).getName());
        holder.tech_class.setText(tdata.get(position).getTeacherClass());
        holder.tech_major.setText(tdata.get(position).getSubject());
        holder.tech_destination.setText(tdata.get(position).getTeacherGrade());

    }

    @Override
    public int getItemCount() {
        return tdata.size();
    }


    public class TInfoHolder extends RecyclerView.ViewHolder{
        TextView tech_name,tech_major,tech_class,tech_destination;
        public TInfoHolder(View itemView) {
            super(itemView);
            tech_name= (TextView) itemView.findViewById(R.id.tech_name);
            tech_major= (TextView) itemView.findViewById(R.id.tech_major);
            tech_class= (TextView) itemView.findViewById(R.id.tech_class);
            tech_destination= (TextView) itemView.findViewById(R.id.tech_distinction);
        }
    }
}
