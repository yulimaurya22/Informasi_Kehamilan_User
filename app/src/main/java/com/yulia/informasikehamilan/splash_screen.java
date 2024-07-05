package com.yulia.informasikehamilan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

public class splash_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();


     final Handler handler = new Handler();
     handler.postDelayed(new Runnable() {
         @Override
         public void run() {
             startActivity(new Intent(getApplicationContext(),login.class));
             finish();
         }
     },2000L);
    }
}