package com.upper.team15.privateschool;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by hp on 27-Oct-17.
 */


public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyHolder> {
    int[] developer;
    String[] address, name,gmail,phonea,messagea;
    Context con;


    public MyRecyclerAdapter(Context context, int[] developer, String[] name, String[] address, String[] gmail, String[] phone, String[] message) {
        this.con = context;
        this.developer = developer;
        this.name = name;
        this.address = address;
        this.gmail=gmail;
        this.phonea=phone;
        this.messagea=message;
    }

    @Override
    public MyRecyclerAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(con).inflate(R.layout.cardview_design, parent, false);
        MyHolder h = new MyHolder(v);
        return h;
    }

    @Override
    public void onBindViewHolder(MyRecyclerAdapter.MyHolder holder, final int position) {
        holder.developername.setText(name[position]);
        holder.developeraddress.setText(address[position]);
        holder.developerimage.setImageResource(developer[position]);
        holder.phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                con=v.getContext();
                Intent goPhone=new Intent(Intent.ACTION_DIAL);
                goPhone.setData(Uri.parse(phonea[position]));
                con.startActivity(goPhone);

            }
        });
        holder.email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto",gmail[position], null));
                con.startActivity(Intent.createChooser(intent, "Choose an Email client :"));
            }
        });
        holder.message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent goMessage=new Intent(Intent.ACTION_VIEW,Uri.fromParts("sms",messagea[position],null));
                con.startActivity(goMessage);
            }
        });

    }

    @Override
    public int getItemCount() {
        return developer.length;
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        ImageView developerimage, phone, message, email;
        TextView developername, developeraddress;

        public MyHolder(View itemView) {
            super(itemView);
            developerimage = (ImageView) itemView.findViewById(R.id.school_developer_image);
            phone = (ImageView) itemView.findViewById(R.id.developer_phone);
            message = (ImageView) itemView.findViewById(R.id.developer_message);
            email = (ImageView) itemView.findViewById(R.id.developer_email);
            developeraddress = (TextView) itemView.findViewById(R.id.titleaddress);
            developername = (TextView) itemView.findViewById(R.id.titlename);
        }
    }
}