package com.upper.team15.privateschool.SchoolServerEvent;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.upper.team15.privateschool.Model.EventModel;
import com.upper.team15.privateschool.R;

import static com.upper.team15.privateschool.SchoolServerEvent.SchoolEventAdapter.Edata;
import static com.upper.team15.privateschool.SchoolServerEvent.SchoolEventAdapter.pos;
import static com.upper.team15.privateschool.SchoolServerEvent.SchoolServerUpdateEvent.getDate;
import static com.upper.team15.privateschool.SchoolServerEvent.SchoolServerUpdateEvent.getTime;

public class Coming_Soon extends AppCompatActivity implements View.OnClickListener {

    EditText title,description;
    ImageView imageView;
    Button okButton,cancelButton;
    EventModel curent_data;
    int RESULT_LOAD_IMG;
    StorageReference fbStorage;//first
    String fromStorageImg_Url = "gnfjbvchj";
    Firebase postNew;
    Toolbar toolbar;
    private ProgressDialog pd;
    String update_for_delete_key="";
    String curent_key="";
    boolean isSelect=false;
    RelativeLayout layout;
    Uri imageUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coming__soon);

        update_for_delete_key=Edata.get(pos).getDate().toString()+Edata.get(pos).getTime().toString();


        Firebase.setAndroidContext(this);
        fbStorage = FirebaseStorage.getInstance().getReference("photo");
        title = (EditText) findViewById(R.id.coming_soon_edit_event_header_box);
        toolbar= (Toolbar) findViewById(R.id.coming_soon_edit_event_toolbar);
        description = (EditText) findViewById(R.id.coming_soon_edit_event_description_box);
        imageView = (ImageView) findViewById(R.id.coming_soon_edit_event_image_box);
        okButton = (Button) findViewById(R.id.coming_soon_edit_event_positive_button);
        cancelButton = (Button) findViewById(R.id.coming_soon_edit_event_cancel_button);
        layout= (RelativeLayout) findViewById(R.id.relative);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("ကျောင်း၏လှုပ်ရှားမှု ပြင်ရန်");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        postNew=new Firebase("https://private-school-85adb.firebaseio.com/");
        pd = new ProgressDialog(this);
        pd.setMessage("Please Wait");

        title.setText(Edata.get(pos).getText().toString());
        description.setText(Edata.get(pos).getDescripiton().toString());

        Glide.with(this).load(Edata.get(pos).getEventImage()).diskCacheStrategy(DiskCacheStrategy.ALL)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        imageView.setVisibility(View.INVISIBLE);
                        return false;
                    }
                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        imageView.setVisibility(View.VISIBLE);
                        return false;
                    }
                }).into(imageView);
        layout.setOnClickListener(this);
        okButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);
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
            imageUri= data.getData();
            isSelect=true;
            uploadImagetoFirebaseStorage();
            Glide.with(this).load(imageUri).into(imageView);
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.relative:
                getImageFromAlbum();
                break;
            case R.id.coming_soon_edit_event_positive_button:
                pd.setCancelable(false);
                pd.show();
                if (title.getText().equals("") || description.getText().equals(""))
                    Toast.makeText(this, R.string.please_enter_again, Toast.LENGTH_LONG).show();
                else
                    postEvent();
                finish();
                break;
            case R.id.coming_soon_edit_event_cancel_button:
                cancenEvent();
                break;
        }
    }
    private void cancenEvent() {
        title.setText("");
        description.setText("");
        imageView.setImageResource(R.drawable.notice_gallery);
        finish();
    }
    private void postEvent() {

        pd.setCancelable(false);
        pd.show();

        if (!isSelect)
            fromStorageImg_Url = Edata.get(pos).getEventImage();


        curent_data = new EventModel();
        curent_data.setText(title.getText().toString());
        curent_data.setDescripiton(description.getText().toString());
        curent_data.setDate(getDate());
        curent_data.setTime(getTime());
        curent_data.setEventImage(fromStorageImg_Url);


        curent_key = curent_data.getDate().toString() + curent_data.getTime().toString();

        postNew.child("Event").child(curent_key).setValue(curent_data);

        postNew.child("Event").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dataCondi(dataSnapshot);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

                pd.dismiss();
            }
        });
        title.setText("");
        description.setText("");
        imageView.setImageResource(R.drawable.notice_gallery);
        Toast.makeText(this, R.string.perfect_event_post, Toast.LENGTH_SHORT).show();

    }


    private void uploadImagetoFirebaseStorage() {
        UploadTask uploadTask = fbStorage.child(imageUri.toString()).putFile(imageUri);
        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {

            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(Coming_Soon.this, imageUri+"", Toast.LENGTH_SHORT).show();
                fromStorageImg_Url = taskSnapshot.getDownloadUrl().toString();
                Log.i("Success ",fromStorageImg_Url+" Image Uploaded");
                isSelect=true;
                pd.dismiss();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.i("Error : ","Image Upload");
                Toast.makeText(Coming_Soon.this, fromStorageImg_Url+"Error", Toast.LENGTH_LONG).show();
                pd.dismiss();
            }
        });
    }

    private void dataCondi(DataSnapshot dataSnapshot) {
        for(DataSnapshot sh:dataSnapshot.getChildren()){
            if(update_for_delete_key.equals(sh.getValue(EventModel.class).getDate()+sh.getValue(EventModel.class).getTime())) {
                postNew.child("Event").child(update_for_delete_key).removeValue();
                break;
            }
        }
        pd.dismiss();
    }

}
