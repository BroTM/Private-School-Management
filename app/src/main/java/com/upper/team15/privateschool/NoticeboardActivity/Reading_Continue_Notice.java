package com.upper.team15.privateschool.NoticeboardActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.firebase.storage.StorageReference;
import com.upper.team15.privateschool.Model.NoticeModel;
import com.upper.team15.privateschool.R;
import com.upper.team15.privateschool.SchoolServerEvent.Coming_Soon;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static com.upper.team15.privateschool.NoticeboardActivity.MyNoticeAdapter.NoticeData;
import static com.upper.team15.privateschool.NoticeboardActivity.MyNoticeAdapter.notice_position;


/**
 * Created by Aspire on 11/11/2017.
 */

public class Reading_Continue_Notice extends AppCompatActivity implements View.OnClickListener {


    TextView eventTitle,eventDate,eventTime;
    ImageView eventImage;
    TextView description;
    Toolbar reading_continue_toolbar;
    NoticeModel data=new NoticeModel();

    ImageView edit_icon;
    Bitmap eventB;
    ImageView refresh;
    ProgressBar loading;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading__continue__notice);

        eventTitle= (TextView) findViewById(R.id.continue_text_about);
        eventDate= (TextView) findViewById(R.id.continue_activity_date);
        eventTime= (TextView) findViewById(R.id.continue_activity_time);
        eventImage= (ImageView) findViewById(R.id.continue_activity_image);
        description= (TextView) findViewById(R.id.continue_descripiton);


        reading_continue_toolbar= (Toolbar) findViewById(R.id.continue_reading_toolbar);

        setSupportActionBar(reading_continue_toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("တူညီ၀တ္စံု");
        reading_continue_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        edit_icon= (ImageView) findViewById(R.id.continue_edit_event_image);
        refresh= (ImageView) findViewById(R.id.continue_refresh);
        loading= (ProgressBar) findViewById(R.id.continue_loading);

        data=NoticeData.get(notice_position);
        Glide.with(Reading_Continue_Notice.this).load(data.getEventImage()).diskCacheStrategy(DiskCacheStrategy.ALL)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        eventImage.setVisibility(View.INVISIBLE);
                        loading.setVisibility(View.GONE);
                        refresh.setVisibility(View.VISIBLE);
                        return false;
                    }
                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        eventImage.setVisibility(View.VISIBLE);
                        loading.setVisibility(View.GONE);
                        refresh.setVisibility(View.GONE);
                        return false;
                    }
                }).into(eventImage);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Glide.with(Reading_Continue_Notice.this).load(data.getEventImage()).diskCacheStrategy(DiskCacheStrategy.ALL)
                        .listener(new RequestListener<String, GlideDrawable>() {
                            @Override
                            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                                eventImage.setVisibility(View.INVISIBLE);
                                loading.setVisibility(View.GONE);
                                refresh.setVisibility(View.VISIBLE);
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                                eventImage.setVisibility(View.VISIBLE);
                                loading.setVisibility(View.GONE);
                                refresh.setVisibility(View.GONE);
                                return false;
                            }
                        }).into(eventImage);
            }
        });
        edit_icon.setOnClickListener(this);
        eventTitle.setText(data.getText().toString());
        eventDate.setText(data.getDate().toString());
        eventTime.setText(data.getTime().toString());
        description.setText(data.getDescripiton().toString());
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.continue_edit_event_image:
                Intent go=new Intent(Reading_Continue_Notice.this,Coming_Soon_Notice.class);
                startActivity(go);
                finish();
                break;
        }
    }
}

