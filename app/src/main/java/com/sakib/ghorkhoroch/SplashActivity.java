package com.sakib.ghorkhoroch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends Activity {

    private static final int SPLASH_DELAY = 3500; // 3.5 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        setContentView(R.layout.activity_splash);

        ImageView logo = findViewById(R.id.splash_logo);
        TextView appName = findViewById(R.id.splash_app_name);
        TextView tagline = findViewById(R.id.splash_tagline);
        TextView devLabel = findViewById(R.id.splash_dev_label);
        TextView devName = findViewById(R.id.splash_dev_name);

        // Animate logo: scale + fade in
        AnimationSet logoAnim = new AnimationSet(true);
        ScaleAnimation scale = new ScaleAnimation(
            0.3f, 1.0f, 0.3f, 1.0f,
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f
        );
        scale.setDuration(900);
        AlphaAnimation fadeIn = new AlphaAnimation(0f, 1f);
        fadeIn.setDuration(900);
        logoAnim.addAnimation(scale);
        logoAnim.addAnimation(fadeIn);
        logoAnim.setFillAfter(true);
        logo.startAnimation(logoAnim);

        // App name: slide up + fade in (delay 600ms)
        AnimationSet nameAnim = new AnimationSet(true);
        TranslateAnimation slide = new TranslateAnimation(0, 0, 60, 0);
        slide.setDuration(700);
        AlphaAnimation nameAlpha = new AlphaAnimation(0f, 1f);
        nameAlpha.setDuration(700);
        nameAnim.addAnimation(slide);
        nameAnim.addAnimation(nameAlpha);
        nameAnim.setStartOffset(600);
        nameAnim.setFillAfter(true);
        appName.startAnimation(nameAnim);
        tagline.startAnimation(nameAnim);

        // Developer name: fade in with pulse (delay 1400ms)
        AnimationSet devAnim = new AnimationSet(true);
        AlphaAnimation devFade = new AlphaAnimation(0f, 1f);
        devFade.setDuration(600);
        ScaleAnimation devPulse = new ScaleAnimation(
            0.7f, 1.05f, 0.7f, 1.05f,
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f
        );
        devPulse.setDuration(600);
        devAnim.addAnimation(devFade);
        devAnim.addAnimation(devPulse);
        devAnim.setStartOffset(1400);
        devAnim.setFillAfter(true);
        devLabel.startAnimation(devAnim);
        devName.startAnimation(devAnim);

        // Launch MainActivity after delay
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            finish();
        }, SPLASH_DELAY);
    }
}
