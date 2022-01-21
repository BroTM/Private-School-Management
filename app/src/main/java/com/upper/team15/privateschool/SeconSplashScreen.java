package com.upper.team15.privateschool;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import android.widget.ProgressBar;

import com.upper.team15.privateschool.HomeActivity.SchoolServerHomeActivity;
import com.upper.team15.privateschool.TeacherServerActivity.TeacherActivity;

/**
 * Created by Aspire on 11/14/2017.
 */

public class SeconSplashScreen extends AppCompatActivity{
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secon_splash_screen);

        progressBar= (ProgressBar) findViewById(R.id.splas_progressbar);
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (checkLogin()) {
                    startActivity(new Intent(SeconSplashScreen.this,TeacherActivity.class));
                    finish();
                }
                else if(checkAdmin()){
                    startActivity(new Intent(SeconSplashScreen.this,SchoolServerHomeActivity.class));
                    finish();
                }
                else {
                    startActivity(new Intent(SeconSplashScreen.this,Login.class));
                    finish();
                }
            }
        },4000);

    }
    private boolean checkAdmin() {
        SharedPreferences sp=getSharedPreferences("adminlogin",MODE_PRIVATE);
        return sp.getBoolean("check",false);
    }
    public static void addAdminLogin(Context context,boolean value){
        SharedPreferences sp=context.getSharedPreferences("adminlogin", MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putBoolean("check",value);
        editor.commit();

    }
    private boolean checkLogin() {
        SharedPreferences sp=getSharedPreferences("login",MODE_PRIVATE);
        return sp.getBoolean("check",false);
    }
    public static void addLogin(Context context,boolean value){
        SharedPreferences sp=context.getSharedPreferences("login", MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putBoolean("check",value);
        editor.commit();

    }

}
