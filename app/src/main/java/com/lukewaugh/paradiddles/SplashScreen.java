package com.lukewaugh.paradiddles;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;


import com.lukewaugh.paradiddles.authClasses.RegisterActivity;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Set view to relative xml
        setContentView(R.layout.splash);

        final ImageView imageView = (ImageView) findViewById(R.id.splashImageView);
        final Animation animation = AnimationUtils.loadAnimation(getBaseContext(), R.anim.rotate);
        @SuppressLint("PrivateResource")
        final Animation animation2 = AnimationUtils.loadAnimation(getBaseContext(), R.anim.abc_fade_out);

        assert imageView != null;
        imageView.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                imageView.startAnimation(animation2);
                finish();
                Intent i = new Intent(SplashScreen.this.getBaseContext(),RegisterActivity.class);
                startActivity(i);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }


}
