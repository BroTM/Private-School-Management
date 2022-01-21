package com.upper.team15.privateschool.TeacherServerActivity;

import android.content.Context;
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

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.upper.team15.privateschool.Model.EventModel;
import com.upper.team15.privateschool.R;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Aspire on 10/18/2017.
 */

public class MyEventAdapter extends RecyclerView.Adapter<MyEventAdapter.MyEventHolder> {
    ArrayList<EventModel> Edata;
    Context context;
    Bitmap eventB;
    public MyEventAdapter(Context schoolEvents, ArrayList<EventModel> eventData) {
        this.context=schoolEvents;
        Edata =new ArrayList<EventModel>();
        this.Edata=eventData;
    }

    @Override
    public MyEventHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.activity_user_event_recycler,parent,false);
        MyEventHolder eventholder=new MyEventHolder(v);
        return eventholder;

    }

    @Override
    public void onBindViewHolder(final MyEventHolder holder, final int position) {
        Log.d("EventDaterMoney",Edata.get(position).getDate());
        holder.eventDate.setText(Edata.get(position).getDate());
        holder.eventTime.setText(Edata.get(position).getTime());
        holder.eventTitle.setText(Edata.get(position).getText());
        Glide.with(context).load(Edata.get(position).getEventImage()).diskCacheStrategy(DiskCacheStrategy.ALL)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        holder.eventImage.setVisibility(View.INVISIBLE);
                        holder.loading.setVisibility(View.GONE);
                        holder.refresh.setVisibility(View.VISIBLE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        holder.eventImage.setVisibility(View.VISIBLE);
                        holder.loading.setVisibility(View.GONE);
                        holder.refresh.setVisibility(View.GONE);
                        return false;
                    }
                }).into(holder.eventImage);


        holder.refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Glide.with(context).load(Edata.get(position).getEventImage()).diskCacheStrategy(DiskCacheStrategy.ALL)
                        .listener(new RequestListener<String, GlideDrawable>() {
                            @Override
                            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                                holder.eventImage.setVisibility(View.INVISIBLE);
                                holder.loading.setVisibility(View.GONE);
                                holder.refresh.setVisibility(View.VISIBLE);
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                                holder.eventImage.setVisibility(View.VISIBLE);
                                holder.loading.setVisibility(View.GONE);
                                holder.refresh.setVisibility(View.GONE);
                                return false;
                            }
                        }).into(holder.eventImage);
            }
        });

    }




    @Override
    public int getItemCount() {
        return Edata.size();
    }

    public class MyEventHolder extends RecyclerView.ViewHolder {
        TextView eventTitle,eventDate,eventTime;
        ImageView eventImage;
        ImageView refresh;
        ProgressBar loading;
        public MyEventHolder(View itemView) {
            super(itemView);
            eventTime=(TextView)itemView.findViewById(R.id.activity_time);
            eventDate= (TextView) itemView.findViewById(R.id.activity_date);
            eventTitle= (TextView) itemView.findViewById(R.id.text_about);
            eventImage= (ImageView) itemView.findViewById(R.id.activity_image);
            refresh = (ImageView) itemView.findViewById(R.id.refresh);
            loading = (ProgressBar) itemView.findViewById(R.id.loading);


        }
    }
}
