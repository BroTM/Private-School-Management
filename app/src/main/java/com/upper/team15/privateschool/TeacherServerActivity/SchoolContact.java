package com.upper.team15.privateschool.TeacherServerActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.upper.team15.privateschool.R;
public class SchoolContact extends Fragment implements View.OnClickListener {
    ImageView phoneimage, messageimage, emailimage;
    TextView textPhone,textMessage,textGmail;
    Intent goPhone,goMessage,intent;
    public SchoolContact() {
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_school_contact, container, false);
        phoneimage = (ImageView) view.findViewById(R.id.teacher_phoneicon);
        messageimage = (ImageView) view.findViewById(R.id.teacher_messageicon);
        emailimage = (ImageView) view.findViewById(R.id.teacher_emailicon);
        textPhone= (TextView)  view.findViewById(R.id.teacher_text_phone);
        textMessage= (TextView)  view.findViewById(R.id.teacher_text_message);
        textGmail= (TextView)  view.findViewById(R.id.teacher_text_gamil);
        phoneimage.setOnClickListener(this);
        messageimage.setOnClickListener(this);
        emailimage.setOnClickListener(this);
        textPhone.setOnClickListener(this);
        textMessage.setOnClickListener(this);
        textGmail.setOnClickListener(this);
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.teacher_phoneicon:
                goPhone=new Intent(Intent.ACTION_DIAL);
                goPhone.setData(Uri.parse("tel:09402512084"));
                startActivity(goPhone);
                break;
            case R.id.teacher_messageicon:
                goMessage=new Intent(Intent.ACTION_VIEW,Uri.fromParts("sms","09402512084",null));
                startActivity(goMessage);
                break;
            case R.id.teacher_emailicon:
                intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto","thethninphyu7777@gmail.com", null));
                startActivity(Intent.createChooser(intent, "Choose an Email client :"));
                break;
            case R.id.teacher_text_phone:
                goPhone=new Intent(Intent.ACTION_DIAL);
                goPhone.setData(Uri.parse("tel:09402512084"));
                startActivity(goPhone);
                break;

            case R.id.teacher_text_message:
                goMessage=new Intent(Intent.ACTION_VIEW,Uri.fromParts("sms","09402512084",null));
                startActivity(goMessage);
                break;

            case R.id.teacher_text_gamil:
                intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto","thethninphyu7777@gmail.com", null));
                startActivity(Intent.createChooser(intent, "Choose an Email client :"));
                break;


        }

    }
}













