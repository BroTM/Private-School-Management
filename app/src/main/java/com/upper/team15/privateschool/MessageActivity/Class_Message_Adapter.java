package com.upper.team15.privateschool.MessageActivity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.upper.team15.privateschool.R;

import static com.upper.team15.privateschool.MessageActivity.ServerMessageActivity.alertDialog;
import static com.upper.team15.privateschool.MessageActivity.ServerMessageActivity.class_text;

/**
 * Created by hp on 13-Nov-17.
 */

public class Class_Message_Adapter extends RecyclerView.Adapter<Class_Message_Adapter.MyHolder> {
    Context context;
    String[] pp;
    static  int message_position;

    public Class_Message_Adapter(Context context, String[] pp) {
        this.context=context;
        this.pp=pp;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.spinner_message_text,parent,false);
        Class_Message_Adapter.MyHolder h=new Class_Message_Adapter.MyHolder(v);
        return h;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, final int position) {
        holder.text.setText(pp[position]);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                message_position=position;
                alertDialog.dismiss();
                if(position == 0){
                    class_text.setText("သူငယ်တန်း");
                }
                else if(position == 1){
                    class_text.setText("ပထမတန်း");
                }
                else if(position == 2){
                    class_text.setText("ဒုတိယတန်း");
                }
                else if(position == 3){
                    class_text.setText("တတိယတန်း");

                }
                else if(position == 4){
                    class_text.setText("စတုတ္ထတန်း");

                }
                else if(position == 5){
                    class_text.setText("ပဉ္စမတန်း");

                }
                else if(position == 6){
                    class_text.setText("ဆဠမတန်း");

                }
                else if(position == 7){
                    class_text.setText("သတ္တမတန်း");

                }
                else if(position == 8){
                    class_text.setText("အဋ္ဌမတန်း");

                }
                else if(position == 9){
                    class_text.setText("နဝမတန်း");

                }
                else if(position == 10){
                    class_text.setText("ဒသမတန်း");

                }

            }
        });

    }



    @Override
    public int getItemCount() {
        return pp.length;
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        TextView text;
        public MyHolder(View itemView) {
            super(itemView);
            text= (TextView) itemView.findViewById(R.id.spinner_message_text);

        }
    }


}
