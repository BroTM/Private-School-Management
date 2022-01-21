package com.upper.team15.privateschool.TeacherServerActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.upper.team15.privateschool.AboutApp;
import com.upper.team15.privateschool.AboutUs;
import com.upper.team15.privateschool.HomeActivity.ViewpagerAdapter;
import com.upper.team15.privateschool.R;
import com.upper.team15.privateschool.SeconSplashScreen;

import java.util.Timer;
import java.util.TimerTask;

public class TeacherActivity extends AppCompatActivity implements View.OnClickListener {

    ViewPager viewPager;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    int[] icon={R.drawable.school,R.drawable.teacher,R.drawable.percent};
    String[] name = {"ကျောင်းအကြောင်း", "အိမ်စာပေးရန်","အောင်ချက်"};
    private ImageView[] dots;

    boolean sliderecycle=true;
    Button popupMenu;
    Button btnok,btnCancel;
    AlertDialog alert2;
    AlertDialog.Builder dialog;
    EditText editText;
    String advice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);

        viewPager= (ViewPager) findViewById(R.id.view_pager);
        popupMenu= (Button) findViewById(R.id.teacher_popup);
        popupMenu.setOnClickListener(this);
        ViewpagerAdapter viewPagerAdapter=new ViewpagerAdapter(this);
        viewPager.setAdapter(viewPagerAdapter);


        Timer timer=new Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(),10000,6000);


        recyclerView = (RecyclerView) findViewById(R.id.recycler_home);
        adapter = new HomeRecyclerView(TeacherActivity.this, icon, name);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        SeconSplashScreen.addLogin(TeacherActivity.this,true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.teacher_popup:
                PopupMenu popup = new PopupMenu(TeacherActivity.this, popupMenu);
                popup.getMenuInflater()
                        .inflate(R.menu.home_menu, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.logout:
                                SeconSplashScreen.addLogin(TeacherActivity.this,false);
                                Intent logout=new Intent(TeacherActivity.this, SeconSplashScreen.class);
                                startActivity(logout);
                                finish();
                                break;

                            case R.id.feedback:
                                View v= LayoutInflater.from(TeacherActivity.this).inflate(R.layout.feecback_dialog,null);
                                btnok= (Button) v.findViewById(R.id.dbutton);
                                btnok.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        advice=editText.getText().toString();
                                        if(advice.length()==0)
                                        {
                                            Toast.makeText(TeacherActivity.this, "အကြံပြုချက်ရေးပြီးမှ\"ပို့မည်\"ကိုနှိပ်ပါ", Toast.LENGTH_SHORT).show();

                                        }
                                        else
                                        {
                                            Toast.makeText(TeacherActivity.this, "ဝေဖန်အကြံပြုချက်များ ပေးပို့ပေးသည့်အတွက် \n" +
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

                                dialog=new AlertDialog.Builder(TeacherActivity.this);
                                dialog.setView(v);
                                alert2=dialog.create();
                                alert2.show();
                                break;
                            case R.id.aboutApp:
                                Intent i=new Intent(TeacherActivity.this,AboutApp.class);
                                startActivity(i);
                                break;
                            case R.id.aboutus:
                                Intent us=new Intent(TeacherActivity.this, AboutUs.class);
                                startActivity(us);
                                break;
                        }
                        return true;
                    }
                });

                popup.show(); //showing popup menu
                break;
        }
    }

    public class MyTimerTask extends TimerTask{
        @Override
        public void run() {
            TeacherActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(sliderecycle==true) {
                        if (viewPager.getCurrentItem() == 0) {
                            viewPager.setCurrentItem(1);
                        } else if (viewPager.getCurrentItem() == 1) {
                            viewPager.setCurrentItem(2);
                        } else if (viewPager.getCurrentItem() == 2) {
                            viewPager.setCurrentItem(3);
                        } else {
                            viewPager.setCurrentItem(2);
                            sliderecycle=false;

                        }
                    }
                    else
                    {
                        if(viewPager.getCurrentItem()==2)
                        {viewPager.setCurrentItem(1);}
                        else if(viewPager.getCurrentItem()==1)
                        {viewPager.setCurrentItem(0);
                            sliderecycle=true; }

                    }

                }
            });
        }
    }
}
