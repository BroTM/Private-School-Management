package com.upper.team15.privateschool.AboutGuide;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.upper.team15.privateschool.Model.GuideModel;
import com.upper.team15.privateschool.R;

import java.util.ArrayList;

/**
 * Created by Aspire on 10/19/2017.
 */

public class GuideInfoAdapter extends RecyclerView.Adapter<GuideInfoAdapter.TInfoHolder> {
    ArrayList<GuideModel> tdata=new ArrayList<GuideModel>();;
    Context context;
    public GuideInfoAdapter(Context teacherInfo, ArrayList<GuideModel> TInfo) {
        this.context=teacherInfo;
        this.tdata=TInfo;

    }

    @Override
    public TInfoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.guide_info_cardview,parent,false);
        TInfoHolder holder=new TInfoHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(TInfoHolder holder, int position) {

        holder.label_password.setText(R.string.apppassword);
        holder.label_username.setText(R.string.appusername);
        holder.tech_name.setText(tdata.get(position).getName());
        holder.tech_class.setText(tdata.get(position).getTeacherClass());
        holder.tech_destination.setText(tdata.get(position).getTeacherGrade());
        holder.password.setText(tdata.get(position).getPassword());
        holder.username.setText(tdata.get(position).getUsername());

    }

    @Override
    public int getItemCount() {
        return tdata.size();
    }


    public class TInfoHolder extends RecyclerView.ViewHolder{
        TextView tech_name,tech_class,tech_destination,password,username;
        TextView label_password,label_username;
        public TInfoHolder(View itemView) {
            super(itemView);
            tech_name= (TextView) itemView.findViewById(R.id.guide_name);
            tech_class= (TextView) itemView.findViewById(R.id.guide_major);
            tech_destination= (TextView) itemView.findViewById(R.id.guide_distinction);

            password= (TextView) itemView.findViewById(R.id.guide_password);
            username= (TextView) itemView.findViewById(R.id.username);
            label_password= (TextView) itemView.findViewById(R.id.password_label);
            label_username= (TextView) itemView.findViewById(R.id.username_label);

        }
    }
}
