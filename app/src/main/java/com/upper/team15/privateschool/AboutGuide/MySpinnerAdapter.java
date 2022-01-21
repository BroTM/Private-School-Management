package com.upper.team15.privateschool.AboutGuide;

import android.content.Context;
import android.database.DataSetObserver;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.upper.team15.privateschool.R;
import com.upper.team15.privateschool.SchoolServerEvent.SchoolEventAdapter;

/**
 * Created by Aspire on 11/14/2017.
 */

class MySpinnerAdapter extends RecyclerView.Adapter<MySpinnerAdapter.MyHolder> {

    Context context;
    String[] array;


    public MySpinnerAdapter(Context guideinfoupdate, String[] array_class) {

        this.context=guideinfoupdate;
        this.array=array_class;
    }



    @Override
    public MySpinnerAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.add_class, parent, false);
        MyHolder h=new MyHolder(v);
        return h;
    }

    @Override
    public void onBindViewHolder(MySpinnerAdapter.MyHolder holder, final int position) {

        holder.textView.setText(array[position]);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Guideinfoupdate.editClass.setText(array[position]);
                Guideinfoupdate.alertDialog.dismiss();
            }
        });
    }

    @Override
    public int getItemCount() {
        return array.length;
    }


    public class MyHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public MyHolder(View itemView) {
            super(itemView);
            textView= (TextView) itemView.findViewById(R.id.text_class);
        }
    }
}
