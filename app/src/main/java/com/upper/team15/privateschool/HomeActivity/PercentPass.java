package com.upper.team15.privateschool.HomeActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.upper.team15.privateschool.R;

public class PercentPass extends AppCompatActivity {

            RecyclerView rv;
            RecyclerView.Adapter adapter;
            Toolbar toolbar;
            int[] developer={R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};
            String[] name={"သက်နှင်းဖြူ","သန်းမြတ်စိုး","ရှိုင်းသူလွင်"};
            String[] address={"ရမည်းသင်း","ရမည်းသင်း","မန္တလေး"};

            String[] acdamic_year={"2016_2017","2015_2016","2014_2015"};
            String[] d6={"6","4","2"};
            String[] d5={"4","5","2"};
            String[] d4={"4","3","2"};
            String[] d3={"5","2","2"};
            String[] d2={"8","6","2"};
            String[] d1={"0","1","2"};
            String[] percent={"78.57","78.57","78.57"};
            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_percent_pass);
                rv= (RecyclerView) findViewById(R.id.rv_about_us);

                toolbar= (Toolbar) findViewById(R.id.percent_toolbar);
                setSupportActionBar(toolbar);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
                adapter=new MyPasswordPercentAdapter(PercentPass.this,acdamic_year,d6,d5,d4,d3,d2,d1,percent);
                rv.setLayoutManager(new LinearLayoutManager(PercentPass.this));
                rv.setAdapter(adapter);
            }
        }



