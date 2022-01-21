package com.upper.team15.privateschool.NoticeboardActivity;

/**
 * Created by Aspire on 10/22/2017.
 */
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.upper.team15.privateschool.Model.NoticeModel;
import com.upper.team15.privateschool.R;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Aspire on 10/18/2017.
 */

public class MyNoticeAdapter extends RecyclerView.Adapter<MyNoticeAdapter.MyEventHolder> {
    static ArrayList<NoticeModel> NoticeData;
    Context context;
    static int notice_position;
    String str="";

    public MyNoticeAdapter(Context schoolEvents, ArrayList<NoticeModel> eventData) {
        this.context=schoolEvents;
        this.NoticeData=eventData;
    }

    @Override
    public MyNoticeAdapter.MyEventHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.activity_notice_adapter,parent,false);
        MyNoticeAdapter.MyEventHolder eventholder=new MyNoticeAdapter.MyEventHolder(v);
        return eventholder;

    }

    @Override
    public void onBindViewHolder(final MyNoticeAdapter.MyEventHolder holder, final int position) {
        Log.d("EventDaterMoney",NoticeData.get(NoticeData.size() - position - 1).getDate());
        holder.notice_date.setText(NoticeData.get(NoticeData.size() - position - 1).getDate());
        holder.notice_time.setText(NoticeData.get(NoticeData.size() - position - 1).getTime());
        holder.notice_titile.setText(NoticeData.get(NoticeData.size() - position - 1).getText());

        Log.i("ImageforNotice : ", NoticeData.get(NoticeData.size() - position - 1).getEventImage());
        str=NoticeData.get(NoticeData.size() - position - 1).getDescripiton();
        if(str.length()<100){
            holder.notice_description.setText(NoticeData.get(NoticeData.size() - position - 1).getDescripiton());
        }
        else {
            str=str.substring(0,42);
            holder.notice_description.setText(str+"Continue reading......");
        }

        Log.i("Image : ", NoticeData.get(position).getEventImage());
        //StringToBitMap(NoticeData.get(position).getEventImage());
        Glide.with(context).load(NoticeData.get(NoticeData.size() - position - 1).getEventImage()).diskCacheStrategy(DiskCacheStrategy.ALL)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        holder.notice_image.setVisibility(View.INVISIBLE);
                        holder.loading_image.setVisibility(View.GONE);
                        holder.refresh_image.setVisibility(View.VISIBLE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        holder.notice_image.setVisibility(View.VISIBLE);
                        holder.loading_image.setVisibility(View.GONE);
                        holder.refresh_image.setVisibility(View.GONE);
                        return false;
                    }
                }).into(holder.notice_image);


        holder.refresh_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Glide.with(context).load(NoticeData.get(NoticeData.size() - position - 1).getEventImage()).diskCacheStrategy(DiskCacheStrategy.ALL)
                        .listener(new RequestListener<String, GlideDrawable>() {
                            @Override
                            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                                holder.notice_image.setVisibility(View.INVISIBLE);
                                holder.loading_image.setVisibility(View.GONE);
                                holder.refresh_image.setVisibility(View.VISIBLE);
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                                holder.notice_image.setVisibility(View.VISIBLE);
                                holder.loading_image.setVisibility(View.GONE);
                                holder.refresh_image.setVisibility(View.GONE);
                                return false;
                            }
                        }).into(holder.notice_image);
            }
        });
        holder.edit_notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notice_position=NoticeData.size() - position - 1;
                Intent coming=new Intent(context,Coming_Soon_Notice.class);
                context.startActivity(coming);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notice_position=NoticeData.size() - position - 1;
                Intent reading=new Intent(context,Reading_Continue_Notice.class);
                context.startActivity(reading);
            }
        });
    }
    @Override
    public int getItemCount() {
        return NoticeData.size();
    }

    public class MyEventHolder extends RecyclerView.ViewHolder {
        TextView notice_time,notice_date;
        ImageView notice_image;
        ImageView edit_notice;
        ProgressBar loading_image;
        ImageView refresh_image;
        TextView notice_description,notice_titile;
        public MyEventHolder(View itemView) {
            super(itemView);
            notice_time=(TextView)itemView.findViewById(R.id.notice_time);
            notice_date= (TextView) itemView.findViewById(R.id.notice_date);
            notice_titile= (TextView) itemView.findViewById(R.id.notice_title);
            notice_image= (ImageView) itemView.findViewById(R.id.notice_image);
            edit_notice= (ImageView) itemView.findViewById(R.id.edit_notice_image);
            loading_image= (ProgressBar) itemView.findViewById(R.id.notice_loading);
            refresh_image= (ImageView) itemView.findViewById(R.id.notice_refresh);
            notice_description = (TextView) itemView.findViewById(R.id.notice_description);



        }
    }
}
