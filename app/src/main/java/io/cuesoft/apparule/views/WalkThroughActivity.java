package io.cuesoft.apparule.views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import io.cuesoft.apparule.R;
import io.cuesoft.apparule.adapter.CustomPagerAdapter;
import io.cuesoft.apparule.adapter.WalkThroughAdapter;
import io.cuesoft.apparule.helper.WalkThroughHelper;
import io.cuesoft.apparule.views.customer.CustomerActivity;
import io.cuesoft.apparule.views.customer.CustomerSignUpActivity;


public class WalkThroughActivity extends AppCompatActivity {
    private WalkThroughHelper preferenceHelper;
    private Button skipBtn;
    private Button getStartedBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walk_through2);

        preferenceHelper = new WalkThroughHelper(this);
        skipBtn = findViewById(R.id.skip_walkthroughBtn);
        getStartedBtn = findViewById(R.id.getStartedButton);

        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(new CustomPagerAdapter(this));
        TabLayout tabLayout = findViewById(R.id.tabDots);
        tabLayout.setupWithViewPager(viewPager,true);

        if(preferenceHelper.getIntro().equals("no")){
            Intent intent = new Intent(WalkThroughActivity.this, LandingActivity.class);
            startActivity(intent);
            this.finish();
        }


    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            getWindow().getDecorView().setSystemUiVisibility(
                   View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                   | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                   | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                   | View.SYSTEM_UI_FLAG_FULLSCREEN
                   | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                   | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            );
        }
    }

    public void skipMethod(View view){
        preferenceHelper.putIntro("no");
        sendIntent(new LandingActivity());
    }

    public void getStartedMethod(View view) {
        preferenceHelper.putIntro("no");
        sendIntent(new CustomerSignUpActivity());
    }

    public void sendIntent(Activity cls){
        Intent intent = new Intent(WalkThroughActivity.this, cls.getClass());;
        startActivity(intent);
        this.finish();

    }
}
