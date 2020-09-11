package com.girlschema.gadsleaderboard;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.girlschema.gadsleaderboard.databinding.ActivitySplashBinding;

public class SplashActivity extends Activity {

    private ActivitySplashBinding activitySplashBinding;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
            activitySplashBinding = ActivitySplashBinding.inflate(getLayoutInflater());
            View view = activitySplashBinding.getRoot();


            setContentView(view);

            handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent =new Intent(SplashActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            },5000);//5 seconds
            }
}