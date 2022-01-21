package com.upper.team15.privateschool.AboutTeacher;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.upper.team15.privateschool.AboutGuide.Guideinfoupdate;
import com.upper.team15.privateschool.R;

/**
 * Created by Aspire on 11/14/2017.
 */
class MySpinnerTeacherAdapter extends RecyclerView.Adapter<MySpinnerTeacherAdapter.MyHolder>{

    Context context;
    String[] array;


    public MySpinnerTeacherAdapter(Context guideinfoupdate, String[] array_class) {

        this.context=guideinfoupdate;
        this.array=array_class;
    }



    @Override
    public MySpinnerTeacherAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.add_teacher_class, parent, false);
        MySpinnerTeacherAdapter.MyHolder h=new MySpinnerTeacherAdapter.MyHolder(v);
        return h;
    }

    @Override
    public void onBindViewHolder(MySpinnerTeacherAdapter.MyHolder holder, final int position) {

        holder.textView.setText(array[position]);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Teacherinfoupdate.editClass.setText(array[position]);
                Teacherinfoupdate.alertDialog.dismiss();
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
            textView= (TextView) itemView.findViewById(R.id.text_class_teacher);
        }
    }
}
