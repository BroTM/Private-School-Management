package com.upper.team15.privateschool.SchoolServerEvent;

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
import com.upper.team15.privateschool.Model.EventModel;
import com.upper.team15.privateschool.R;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Aspire on 10/18/2017.
 */

public class SchoolEventAdapter extends RecyclerView.Adapter<SchoolEventAdapter.MyEventHolder> {
    public static ArrayList<EventModel> Edata=new ArrayList<>();
    Context context;
    String str="";
    static int pos;
    public SchoolEventAdapter(Context schoolEvents, ArrayList<EventModel> eventData) {
        this.context = schoolEvents;
        this.Edata = eventData;
    }


    @Override
    public SchoolEventAdapter.MyEventHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.activity_school_event_recycler, parent, false);
        SchoolEventAdapter.MyEventHolder eventholder = new SchoolEventAdapter.MyEventHolder(v);
        return eventholder;

    }
    @Override
    public void onBindViewHolder(final SchoolEventAdapter.MyEventHolder holder, final int position) {

        Log.d("EventDaterMoney", Edata.get(Edata.size() - position - 1).getDate());
        holder.eventDate.setText(Edata.get(Edata.size() - position - 1).getDate());
        holder.eventTime.setText(Edata.get(Edata.size() - position - 1).getTime());
        holder.eventTitle.setText(Edata.get(Edata.size() - position - 1).getText());

        str=Edata.get(Edata.size() - position - 1).getDescripiton();
        if(str.length()<100){
            holder.description.setText(Edata.get(Edata.size() - position - 1).getDescripiton());
        }
        else {
            str=str.substring(0,42);
            holder.description.setText(str+"Continue reading......");
        }

        Log.i("Image : ", Edata.get(position).getEventImage());
        //StringToBitMap(Edata.get(position).getEventImage());
        Glide.with(context).load(Edata.get(Edata.size() - position - 1).getEventImage()).diskCacheStrategy(DiskCacheStrategy.ALL)
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
        //holder.eventImage.setImageBitmap(eventB);
        /*Imageview imageView = (ImageView) findViewById(R.id.image_view);

        Glide.with(this).load("https://an-awesome-image.com").into(imageView);*/

        holder.refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Glide.with(context).load(Edata.get(Edata.size() - position - 1).getEventImage()).diskCacheStrategy(DiskCacheStrategy.ALL)
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
        holder.edit_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context, "Coming Soon!", Toast.LENGTH_SHORT).show();
                pos=Edata.size() - position - 1;
                Intent coming_soon=new Intent(context,Coming_Soon.class);
                context.startActivity(coming_soon);


            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pos=Edata.size() - position - 1;
                Intent read = new Intent(context,Reading_Continue.class);
                context.startActivity(read);
            }
        });

    }

    @Override
    public int getItemCount() {
        return Edata.size();
    }

    public class MyEventHolder extends RecyclerView.ViewHolder {
        TextView eventTitle, eventDate, eventTime;
        ImageView eventImage;
        ImageView edit_icon;
        ImageView refresh;
        ProgressBar loading;

        TextView  description;


        public MyEventHolder(View itemView) {
            super(itemView);
            eventTime = (TextView) itemView.findViewById(R.id.activity_time);
            eventDate = (TextView) itemView.findViewById(R.id.activity_date);
            eventTitle = (TextView) itemView.findViewById(R.id.text_about);
            edit_icon = (ImageView) itemView.findViewById(R.id.edit_event_image);
            eventImage = (ImageView) itemView.findViewById(R.id.activity_image);
            refresh = (ImageView) itemView.findViewById(R.id.refresh);
            loading = (ProgressBar) itemView.findViewById(R.id.loading);
            description = (TextView) itemView.findViewById(R.id.description);

        }
    }
}
