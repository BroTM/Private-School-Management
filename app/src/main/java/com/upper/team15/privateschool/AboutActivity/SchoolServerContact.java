package com.upper.team15.privateschool.AboutActivity;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.upper.team15.privateschool.R;


public class SchoolServerContact extends AppCompatActivity implements View.OnClickListener {

    ImageView phoneimage, messageimage, emailimage;
    Button btnGoogle;
    Toolbar toolbar;
    TextView textPhone,textMessage,textGmail;
    Intent goPhone,goMessage,intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_server_contact);
        phoneimage = (ImageView) findViewById(R.id.phoneicon);
        toolbar= (Toolbar) findViewById(R.id.contact_toolbar);
        textPhone= (TextView) findViewById(R.id.text_phone);
        textMessage= (TextView) findViewById(R.id.text_message);
        textGmail= (TextView) findViewById(R.id.text_gamil);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//show arraw key
        getSupportActionBar().setTitle("ဆက်သွယ်ရန်");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        messageimage = (ImageView) findViewById(R.id.messageicon);
        emailimage = (ImageView) findViewById(R.id.emailicon);
        phoneimage.setOnClickListener(this);
        messageimage.setOnClickListener(this);
        emailimage.setOnClickListener(this);
        textPhone.setOnClickListener(this);
        textMessage.setOnClickListener(this);
        textGmail.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.phoneicon:
                goPhone=new Intent(Intent.ACTION_DIAL);
                goPhone.setData(Uri.parse("tel:09402512084"));
                startActivity(goPhone);
                break;
            case R.id.messageicon:
                goMessage=new Intent(Intent.ACTION_VIEW,Uri.fromParts("sms","09402512084",null));
                startActivity(goMessage);
                break;
            case R.id.emailicon:
                intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto","thethninphyu7777@gmail.com", null));
                startActivity(Intent.createChooser(intent, "Choose an Email client :"));
                break;
            case R.id.text_phone:
                goPhone=new Intent(Intent.ACTION_DIAL);
                goPhone.setData(Uri.parse("tel:09402512084"));
                startActivity(goPhone);
                break;

            case R.id.text_message:
                goMessage=new Intent(Intent.ACTION_VIEW,Uri.fromParts("sms","09402512084",null));
                startActivity(goMessage);
                break;

            case R.id.text_gamil:
                intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto","thethninphyu7777@gmail.com", null));
                startActivity(Intent.createChooser(intent, "Choose an Email client :"));
                break;

        }

    }


}