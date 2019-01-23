package io.cuesoft.apparule.views.walkthrough;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.github.paolorotolo.appintro.AppIntro;

import io.cuesoft.apparule.helper.WalkThroughHelper;
import io.cuesoft.apparule.views.BlankActivity;
import io.cuesoft.apparule.views.LandingActivity;
import io.cuesoft.apparule.views.customer.CustomerActivity;
import io.cuesoft.apparule.views.customer.CustomerSignUpActivity;
import io.cuesoft.apparule.views.customer.CustomerSignUpFragment;

public class WalkThroughActivity extends AppIntro {
    private WalkThroughHelper preferenceHelper;

    @Override
        protected void onCreate (Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            //setContentView(R.layout.activity_main);

            preferenceHelper = new WalkThroughHelper(this);

            if (preferenceHelper.getIntro().equals("no")) {
                Intent intent = new Intent(WalkThroughActivity.this, LandingActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                this.finish();
            }

            addSlide(new Intro_screen1());  //extend AppIntro and comment setContentView
            addSlide(new Intro_screen2());
            addSlide(new Intro_screen3());

        }

        @Override
        public void onSkipPressed (Fragment currentFragment){
            super.onSkipPressed(currentFragment);

            preferenceHelper.putIntro("no");
            Intent intent = new Intent(WalkThroughActivity.this, LandingActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            this.finish();
        }

        @Override
        public void onDonePressed (Fragment currentFragment){
            super.onDonePressed(currentFragment);

            preferenceHelper.putIntro("no");
            Intent intent = new Intent(WalkThroughActivity.this, LandingActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            this.finish();
        }

        @Override
        public void onSlideChanged (@Nullable Fragment oldFragment, @Nullable Fragment newFragment){
            super.onSlideChanged(oldFragment, newFragment);
            // Do something when the slide changes.
        }

        @Override  // this method is used for removing top and bottom navbars(fullscreen)
        public void onWindowFocusChanged ( boolean hasFocus){
            super.onWindowFocusChanged(hasFocus);
            if (hasFocus) {
                getWindow().getDecorView().setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
            }

        }

    public void mainPage(View view) {
        Intent intent = new Intent(WalkThroughActivity.this, LandingActivity.class);
        startActivity(intent);
    }
    public void signup(View view) {
        Intent intent = new Intent(WalkThroughActivity.this, CustomerSignUpActivity.class);
        startActivity(intent);

    }

    }

