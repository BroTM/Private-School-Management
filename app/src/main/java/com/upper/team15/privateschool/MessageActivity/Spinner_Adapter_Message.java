package com.upper.team15.privateschool.MessageActivity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.upper.team15.privateschool.Model.StudentModel;
import com.upper.team15.privateschool.R;

import java.util.ArrayList;

import static com.upper.team15.privateschool.MessageActivity.ServerMessageActivity.alertDialog;
import static com.upper.team15.privateschool.MessageActivity.ServerMessageActivity.student_text;

/**
 * Created by hp on 13-Nov-17.
 */

public class Spinner_Adapter_Message extends RecyclerView.Adapter<Spinner_Adapter_Message.MyHolder> {
    Context context;
    static ArrayList<StudentModel> pp;
    static int stu_name_position;
    public Spinner_Adapter_Message(Context context, ArrayList<StudentModel> pp) {
        this.context=context;
        this.pp=pp;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.spinner_message_text,parent,false);
        Spinner_Adapter_Message.MyHolder h=new Spinner_Adapter_Message.MyHolder(v);
        return h;
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {
        holder.text.setText(pp.get(position).getStudenName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stu_name_position=position;
                alertDialog.dismiss();
                student_text.setText(holder.text.getText());

            }
        });

    }



    @Override
    public int getItemCount() {
        return pp.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        TextView text;
        public MyHolder(View itemView) {
            super(itemView);
            text= (TextView) itemView.findViewById(R.id.spinner_message_text);

        }
    }


}
