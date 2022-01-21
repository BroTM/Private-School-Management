package com.upper.team15.privateschool.HomeActivity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.upper.team15.privateschool.R;

/**
 * Created by hp on 18-Nov-17.
 */

class MyPasswordPercentAdapter extends RecyclerView.Adapter<MyPasswordPercentAdapter.MyPassHolder> {
    Context context;
    String[] acdamic,d6,d5,d4,d3,d2,d1,percent;

    public MyPasswordPercentAdapter(Context context, String[] acdamic_year, String[] d6, String[] d5, String[] d4, String[] d3, String[] d2, String[] d1, String[] percent) {
        this.context=context;
        this.acdamic=acdamic_year;
        this.d1=d1;
        this.d2=d2;
        this.d3=d3;
        this.d4=d4;
        this.d5=d5;
        this.d6=d6;
        this.percent=percent;
    }

    @Override
    public MyPasswordPercentAdapter.MyPassHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(context).inflate(R.layout.pass_cardview_design,parent,false);
        MyPassHolder h=new MyPassHolder(v);
        return h;

    }

    @Override
    public void onBindViewHolder(MyPasswordPercentAdapter.MyPassHolder holder, int position) {

        holder.text_acdamic.setText(acdamic[position]);
        holder.text_d1.setText(d1[position]+"ယောက်");
        holder.text_d2.setText(d2[position]+"ယောက်");
        holder.text_d3.setText(d3[position]+"ယောက်");
        holder.text_d4.setText(d4[position]+"ယောက်");
        holder.text_d5.setText(d5[position]+"ယောက်");
        holder.text_d6.setText(d6[position]+"ယောက်");
        holder.text_percent.setText(percent[position]+"%");
    }

    @Override
    public int getItemCount() {
        return acdamic.length;
    }

    public class MyPassHolder extends RecyclerView.ViewHolder {
        TextView text_acdamic,text_d6,text_d5,text_d4,text_d3,text_d2,text_d1,text_percent;
        public MyPassHolder(View itemView) {
            super(itemView);
            text_acdamic= (TextView) itemView.findViewById(R.id.acdamic_year);
            text_d1= (TextView) itemView.findViewById(R.id.student_1d);
            text_d2= (TextView) itemView.findViewById(R.id.student_2d);
            text_d3= (TextView) itemView.findViewById(R.id.student_3d);
            text_d4= (TextView) itemView.findViewById(R.id.student_4d);
            text_d5= (TextView) itemView.findViewById(R.id.student_5d);
            text_d6= (TextView) itemView.findViewById(R.id.student_6d);
            text_percent= (TextView) itemView.findViewById(R.id.textPercent);
        }
    }
}
