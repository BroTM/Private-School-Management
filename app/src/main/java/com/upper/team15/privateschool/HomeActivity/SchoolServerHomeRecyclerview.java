package com.upper.team15.privateschool.HomeActivity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.upper.team15.privateschool.AboutActivity.SchoolAboutServer;
import com.upper.team15.privateschool.AbsentActivity.ServerAbsent;
import com.upper.team15.privateschool.AddNewStudent.AddNewStudentActivity;
import com.upper.team15.privateschool.MessageActivity.ServerMessageActivity;
import com.upper.team15.privateschool.NoticeboardActivity.UserAndServerNoticeBoardMainActivity;
import com.upper.team15.privateschool.R;
import com.upper.team15.privateschool.RealStudentActivity.RealStudent;
import com.upper.team15.privateschool.RegistrationActivity.RegisterTablayout;

/**
 * Created by Aspire on 10/17/2017.
 */

class SchoolServerHomeRecyclerview extends RecyclerView.Adapter<SchoolServerHomeRecyclerview.ServerHomeHolder>{
    int[] icon;
    String[] name;
    Context context;
    Context con;
    public SchoolServerHomeRecyclerview(Context schoolServerHomeActivity, int[] icon, String[] name) {
        this.context =schoolServerHomeActivity ;
        this.icon = icon;
        this.name = name;
    }

    @Override
    public ServerHomeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.activity_school_server_home_cardview,parent,false);
        ServerHomeHolder holder=new ServerHomeHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(ServerHomeHolder holder, int position) {
        holder.iconImage.setImageResource(icon[position]);
        holder.iconName.setText(name[position]);

    }

    @Override
    public int getItemCount() {
        return name.length;
    }

    public class ServerHomeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView iconName;
        ImageView iconImage;
        public ServerHomeHolder(View v) {
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
                    intent =  new Intent(context, SchoolAboutServer.class);
                    context.startActivity(intent);
                    break;
                  case 1:
                    intent =new Intent(context, PercentPass.class);
                    context.startActivity(intent);
                    break;

                case 2:
                    intent =  new Intent(context, RegisterTablayout.class);
                    context.startActivity(intent);
                    break;
                case 3:
                    intent=new Intent(context,RealStudent.class);
                    context.startActivity(intent);
                    break;
              /*  case 3:
                    intent =new Intent(context, AddNewStudentActivity.class);
                    context.startActivity(intent);
                    break;*/
                case 4:
                    intent =  new Intent(context,ServerAbsent.class);
                    context.startActivity(intent);
                    break;
                case 5:
                    intent=new Intent(context,ServerMessageActivity.class);
                    context.startActivity(intent);
                    break;
                default:
                    intent =  new Intent(context,UserAndServerNoticeBoardMainActivity.class);
                    context.startActivity(intent);
                    break;
            }


        }
    }
}
