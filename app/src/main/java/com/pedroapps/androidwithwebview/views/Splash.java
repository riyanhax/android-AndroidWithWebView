package com.pedroapps.androidwithwebview.views;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.pedroapps.androidwithwebview.R;

public class Splash extends AppCompatActivity {

    // ViewHolder Class
    private ViewHolder mViewHolder = new ViewHolder();

    // Duração da splash screen.
    private static int SPLASH_TIME_OUT = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        this.mViewHolder.TextSplash = (TextView) this.findViewById(R.id.text_splash);

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.mytransition);
        this.mViewHolder.TextSplash.startAnimation(animation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent MainActivityIntent = new Intent(Splash.this, WelcomeActivity.class);
                startActivity(MainActivityIntent);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

    private static class ViewHolder {
        TextView TextSplash;
    }

}