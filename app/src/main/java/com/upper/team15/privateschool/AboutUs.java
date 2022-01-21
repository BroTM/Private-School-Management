package com.upper.team15.privateschool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class AboutUs extends AppCompatActivity {

    RecyclerView rv;
    RecyclerView.Adapter adapter;
    Toolbar toolbar;
    int[] developer={R.drawable.thet_hnin_phyu,R.drawable.than_myat_soe,R.drawable.shine_thu_lwin};
    String[] name={"သက်နှင်းဖြူ","သန်းမြတ်စိုး","ရှိုင်းသူလွင်"};
    String[] address={"ရမည်းသင်း","ရမည်းသင်း","မန္တလေး"};
    String[] gmail={"thethninphyu7777@gmail.com","thanmyatsoe222@gmail.com","shinethulwin1997@gmail.com"};
    String[] phone={"tel:09787816733","tel:09767917810","tel:09402512084"};
    String[] message={"09787816733","09767917810","09402512084"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        rv= (RecyclerView) findViewById(R.id.rv_about_us);
        toolbar= (Toolbar) findViewById(R.id.about_us_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        rv.setLayoutManager(new LinearLayoutManager(AboutUs.this));
        adapter=new MyRecyclerAdapter(AboutUs.this,developer,name,address,gmail,phone,message);
        rv.setAdapter(adapter);
    }
}
