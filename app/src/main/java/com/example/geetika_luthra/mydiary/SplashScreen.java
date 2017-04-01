package com.example.geetika_luthra.mydiary;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
    }

    public void onResume()
    {
        super.onResume();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable()
        {
            public void run()
            {
                Preference.getInstance().loadPreference(SplashScreen.this);
                if(Preference.getInstance().isLocked)
                {
                    Intent i = new Intent(SplashScreen.this,VerifyLock.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(i);
                    finish();
                }
                else
                {
                    Intent i = new Intent(SplashScreen.this,MainActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(i);
                    finish();
                }
            }}, 3000);
    }




}
