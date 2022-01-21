package com.upper.team15.privateschool.NoticeboardActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.upper.team15.privateschool.HomeActivity.SchoolServerHomeActivity;
import com.upper.team15.privateschool.Model.NoticeModel;
import com.upper.team15.privateschool.R;
import com.firebase.client.Firebase;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ServerNoticeBoardMainActivity extends AppCompatActivity implements View.OnClickListener {

    Firebase fbServerEvent;
    // for image storage;
    StorageReference notice_image_storage;
    private ProgressDialog pd;
    EditText notice_title;
    int RESULT_LOAD_IMG;
    String imageStr;
    Toolbar toolbar;
    ImageView choose_notice_image;
    String fromStorageUri="";
    Button btn;
    public  String sday;
    public  String stime;
    EditText notice_description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        notice_image_storage= FirebaseStorage.getInstance().getReference("noticeimage");
        setContentView(R.layout.activity_server_notice_board_main);
        toolbar= (Toolbar) findViewById(R.id.server_notice_toolbar);
        setSupportActionBar(toolbar);
        btn= (Button) findViewById(R.id.server_notice_post_button);
        btn.setOnClickListener(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("ကြော်ငြာသင်ပုန်းတင်ရန်");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Firebase.setAndroidContext(ServerNoticeBoardMainActivity.this);
        //for image storage;
        notice_title= (EditText) findViewById(R.id.server_notice_title_box);
        notice_description= (EditText) findViewById(R.id.server_notice_description_box);

        choose_notice_image= (ImageView) findViewById(R.id.server_notice_choose_image);
        fbServerEvent=new Firebase("https://private-school-85adb.firebaseio.com/");
        choose_notice_image.setOnClickListener(this);
        pd=new ProgressDialog(this);
        pd.setMessage("Please wait");
    }
    public  String getTime(){
        DateFormat time = new SimpleDateFormat(" HH:mm:ss");
        time.setLenient(false);
        Date todaytime = new Date();
        stime = time.format(todaytime);
        return stime;
    }
    public  String getDate(){
        DateFormat date = new SimpleDateFormat("yyyy:MM:dd ");
        date.setLenient(false);
        Date todaydate = new Date();
        sday = date.format(todaydate);
        return sday;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.server_notice_choose_image:
                getImageFromAlbum();
                break;
            case R.id.server_notice_post_button:
                if(notice_title.getText().length()<=0){
                    Toast.makeText(this,  "ဖြည့်ရန်ကျန်ပါသေးသည်", Toast.LENGTH_SHORT).show();
                }
                else {
                    getTime();
                    getDate();
                    NoticeModel eventModel=new NoticeModel();
                    eventModel.setDate(sday);
                    eventModel.setTime(stime);
                    eventModel.setText(notice_title.getText().toString());
                    eventModel.setEventImage(fromStorageUri);
                    eventModel.setDescripiton(notice_description.getText().toString());
                    fbServerEvent.child("Noticeboard").child(eventModel.getDate()+eventModel.getTime()).setValue(eventModel);
                    notice_title.setText(" ");
                    notice_description.setText(" ");
                    choose_notice_image.setImageResource(R.drawable.notice_gallery);
                }

                break;
        }

    }

    private void getImageFromAlbum() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG);
    }
    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);


        if (resultCode == RESULT_OK) {
            if(pd!=null){
                pd.setCancelable(false);
                pd.show();
            }
            final Uri imageUri = data.getData();
            UploadTask uploadTask=notice_image_storage.child(imageUri.toString()).putFile(imageUri);
            uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    fromStorageUri=taskSnapshot.getDownloadUrl().toString();
                    pd.dismiss();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    pd.dismiss();
                }
            });
            Glide.with(this).load(imageUri).into(choose_notice_image);
        }else {
            Toast.makeText(ServerNoticeBoardMainActivity.this, "You haven't picked Image", Toast.LENGTH_LONG).show();
        }
    }







}