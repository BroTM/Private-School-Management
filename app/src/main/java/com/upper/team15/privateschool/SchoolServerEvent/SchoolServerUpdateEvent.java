package com.upper.team15.privateschool.SchoolServerEvent;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.upper.team15.privateschool.HomeActivity.SchoolServerHomeActivity;
import com.upper.team15.privateschool.Model.EventModel;
import com.upper.team15.privateschool.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class SchoolServerUpdateEvent extends AppCompatActivity implements View.OnClickListener {

    Firebase fbServerEvent;
    EditText event_title;
    EditText description_edit;
    int RESULT_LOAD_IMG;
    Toolbar toolbar;
    Button btn_event_send;
    ImageView choose_event_image;
    public static String sday;
    public static String stime;
    StorageReference fbStorage;//first
    String imgUrl = "";
    boolean ischose=false;
    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fbStorage = FirebaseStorage.getInstance().getReference("photo");//second
        /*
        * getReference("photo")
        * */
        setContentView(R.layout.activity_school_server_event);
        toolbar= (Toolbar) findViewById(R.id.serverEventToolbar);
        btn_event_send= (Button) findViewById(R.id.btn_event_sent);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Firebase.setAndroidContext(SchoolServerUpdateEvent.this);
        event_title= (EditText) findViewById(R.id.eventtitle);
        description_edit= (EditText) findViewById(R.id.event_title_edit);
        choose_event_image= (ImageView) findViewById(R.id.choose_image_server_event);
        fbServerEvent=new Firebase("https://private-school-85adb.firebaseio.com/");
        btn_event_send.setOnClickListener(this);
        choose_event_image.setOnClickListener(this);

        pd = new ProgressDialog(this);
        pd.setMessage("Please Wait");
    }

    public static String getTime(){
        DateFormat time = new SimpleDateFormat(" HH:mm:ss");
        time.setLenient(false);
        Date todaytime = new Date();
        stime = time.format(todaytime);
        return stime;
    }
    public static String getDate(){
        DateFormat date = new SimpleDateFormat("yyyy:MM:dd ");
        date.setLenient(false);
        Date todaydate = new Date();
        sday = date.format(todaydate);
        return sday;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.choose_image_server_event:
                getImageFromAlbum();
                break;
            case R.id.btn_event_sent:
                if (description_edit.getText().toString().equals("") || event_title.getText().toString().equals("") || (!ischose))
                    Toast.makeText(this, R.string.enter_again, Toast.LENGTH_SHORT).show();
                else {
                    getTime();
                    getDate();
                    EventModel eventModel = new EventModel();
                    eventModel.setDate(sday);
                    eventModel.setTime(stime);
                    eventModel.setDescripiton(description_edit.getText().toString());
                    eventModel.setText(event_title.getText().toString());
                    eventModel.setEventImage(imgUrl);//four
                    fbServerEvent.child("Event").child(eventModel.getDate() + eventModel.getTime()).setValue(eventModel);
                    Toast.makeText(this, "ကျောင်း၏ လှုပ်ရှားမှုများ တင်ပီးပါပီ။", Toast.LENGTH_LONG).show();
                    event_title.setText(" ");
                    description_edit.setText("");
                    choose_event_image.setImageResource(R.drawable.notice_gallery);

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

//////////////////////////////third
        if (resultCode == RESULT_OK) {
//            try {
            if(pd!=null){
                pd.setCancelable(false);
                pd.show();
            }
            final Uri imageUri = data.getData();
            ischose=true;
            UploadTask uploadTask = fbStorage.child(imageUri.toString()).putFile(imageUri);
            uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    imgUrl = taskSnapshot.getDownloadUrl().toString();
                    ischose=true;
                    Log.i("Success ",imgUrl+" Image Uploaded");
                    pd.dismiss();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.i("Error : ","Image Upload");
                    pd.dismiss();
                }
            });
            Glide.with(this).load(imageUri).into(choose_event_image);

        }else {
            Toast.makeText(SchoolServerUpdateEvent.this, "You haven't picked Image", Toast.LENGTH_LONG).show();
        }
    }








}
