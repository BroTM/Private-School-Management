package com.upper.team15.privateschool.HomeActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.upper.team15.privateschool.AboutApp;
import com.upper.team15.privateschool.AboutUs;
import com.upper.team15.privateschool.R;
import com.upper.team15.privateschool.SeconSplashScreen;

import java.util.Timer;
import java.util.TimerTask;

public class SchoolServerHomeActivity extends AppCompatActivity {

    ViewPager viewPager;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    int[] icon = {R.drawable.school,R.drawable.percent, R.drawable.register, R.drawable.register,  R.drawable.message, R.drawable.report, R.drawable.notice_board};
    String[] name = {"ကျောင်းအကြောင်း", "အောင်ချက်","ကျောင်းအပ်လက်ခံရန်", "ကျောင်းသားစာရင်း",  "ခွင့်တိုင်ကြားစာစစ်ရန်", "သတိပေးစာပို့ရန်", "ကြော်ငြာသင်ပုန်း"};
    private ImageView[] dots;
    boolean sliderecycle = true;
    Button btnok, btnCancel;
    AlertDialog alert2;
    AlertDialog.Builder dialog;
    EditText editText;
    String advice;
    Button btn_popup;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_server_home);

        toolbar = (Toolbar) findViewById(R.id.tool);
        setSupportActionBar(toolbar);

        initCollapsingToolbar();
        viewPager = (ViewPager) findViewById(R.id.server_view_pager);

        ViewpagerAdapter viewPagerAdapter = new ViewpagerAdapter(this);
        viewPager.setAdapter(viewPagerAdapter);


        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(), 10000, 6000);


        recyclerView = (RecyclerView) findViewById(R.id.recycler_home);
        adapter = new SchoolServerHomeRecyclerview(SchoolServerHomeActivity.this, icon, name);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        SeconSplashScreen.addAdminLogin(SchoolServerHomeActivity.this, true);
    }

    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(getString(R.string.app_name));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.logout:
                SeconSplashScreen.addAdminLogin(SchoolServerHomeActivity.this,false);
                Intent logout=new Intent(SchoolServerHomeActivity.this, SeconSplashScreen.class);
                startActivity(logout);
                finish();
                break;

            case R.id.feedback:
                View v= LayoutInflater.from(SchoolServerHomeActivity.this).inflate(R.layout.feecback_dialog,null);
                btnok= (Button) v.findViewById(R.id.dbutton);
                btnok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        advice=editText.getText().toString();
                        if(advice.length()==0)
                        {
                            Toast.makeText(SchoolServerHomeActivity.this, "အကြံပြုချက်ရေးပြီးမှ\"ပို့မည်\"ကိုနှိပ်ပါ", Toast.LENGTH_SHORT).show();

                        }
                        else
                        {
                            Toast.makeText(SchoolServerHomeActivity.this, "ဝေဖန်အကြံပြုချက်များ ပေးပို့ပေးသည့်အတွက် \n" +
                                    "အထူးကျေးဇူးတင်ပါသည်။အကြံပြုချက် များကို နောက်ထွက်မည့်               \"ကိုယ်ပိုင်ကျောင်း App\" အသစ်တွင်\n" +
                                    "အကောင်းဆုံးထည့်သွင်းပေးသွားပါမည်။", Toast.LENGTH_LONG).show();
                        }
                        alert2.dismiss();

                    }

                });
                btnCancel= (Button) v.findViewById(R.id.dbutton2);
                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alert2.dismiss();
                    }
                });
                editText= (EditText) v.findViewById(R.id.dialogedit);

                dialog=new AlertDialog.Builder(SchoolServerHomeActivity.this);
                dialog.setView(v);
                alert2=dialog.create();
                alert2.show();
                break;
            case R.id.aboutApp:
                Intent i=new Intent(SchoolServerHomeActivity.this,AboutApp.class);
                startActivity(i);
                break;
            case R.id.aboutus:
                Intent us=new Intent(SchoolServerHomeActivity.this, AboutUs.class);
                startActivity(us);
                break;
        }
        return super.onOptionsItemSelected(item);
    }



    public class MyTimerTask extends TimerTask {
        @Override
        public void run() {
            SchoolServerHomeActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (sliderecycle == true) {
                        if (viewPager.getCurrentItem() == 0) {
                            viewPager.setCurrentItem(1);
                        } else if (viewPager.getCurrentItem() == 1) {
                            viewPager.setCurrentItem(2);
                        } else if (viewPager.getCurrentItem() == 2) {
                            viewPager.setCurrentItem(3);
                        } else {
                            viewPager.setCurrentItem(2);
                            sliderecycle = false;

                        }
                    } else {
                        if (viewPager.getCurrentItem() == 2) {
                            viewPager.setCurrentItem(1);
                        } else if (viewPager.getCurrentItem() == 1) {
                            viewPager.setCurrentItem(0);
                            sliderecycle = true;
                        }

                    }

                }
            });
        }
    }
}
