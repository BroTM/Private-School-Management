package com.upper.team15.privateschool.TeacherServerActivity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.upper.team15.privateschool.HomeActivity.PercentPass;
import com.upper.team15.privateschool.R;

/**
 * Created by Aspire on 10/12/2017.
 */
public class HomeRecyclerView extends RecyclerView.Adapter<HomeRecyclerView.Holder> {
    int[] icon;
    String[] name;
    Context context;
    Context con;

    public HomeRecyclerView(Context home1, int[] icon, String[] name) {
        this.context = home1;
        this.icon = icon;
        this.name = name;


    }


    @Override
    public HomeRecyclerView.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.activity_teacher_cardviewdesign,parent,false);
        Holder holder=new Holder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(HomeRecyclerView.Holder holder, final int position) {

        holder.iconImage.setImageResource(icon[position]);
        holder.iconName.setText(name[position]);

    }

    @Override
    public int getItemCount() {
        return name.length;
    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView iconName;
        ImageView iconImage;
        public Holder(View v) {
            super(v);
            con = itemView.getContext();
            iconImage= (ImageView) v.findViewById(R.id.iconimage);
            iconName= (TextView) v.findViewById(R.id.iconText);
            v.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
            final Intent intent;
            switch (getAdapterPosition()){
                case 0:
                    intent =  new Intent(context, AboutActivity.class);
                    intent.putExtra("type", "teacher");
                    break;
                case 1:
                    intent =  new Intent(context, ServerUpdateHomework.class);
                    break;
                default:
                    intent=new Intent(context,PercentPass.class);
                    break;
            }
            context.startActivity(intent);

        }
    }
}