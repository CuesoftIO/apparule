package io.cuesoft.apparule.views;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.crashlytics.android.Crashlytics;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import io.cuesoft.apparule.R;
import io.cuesoft.apparule.adapter.CustomPagerAdapter;
import io.cuesoft.apparule.helper.SignInHelper;
import io.cuesoft.apparule.helper.WalkThroughHelper;
import io.cuesoft.apparule.views.customer.CustomerSignUpActivity;
import io.cuesoft.apparule.views.customer.MainActivity;
import io.cuesoft.apparule.views.designer.DashBoardActivity;
import io.fabric.sdk.android.Fabric;


public class WalkThroughActivity extends AppCompatActivity {
    private WalkThroughHelper preferenceHelper;
    private Button skipBtn;
    private Button getStartedBtn;

    private FirebaseAuth mAuth;
    private FirebaseUser mFirebaseUser;

    private FirebaseAnalytics mFirebaseAnalytics;

    private SignInHelper signInHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walk_through2);

        Fabric.with(this, new Crashlytics());
        Crashlytics.log(Log.DEBUG, "tag", "message");

        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        signInHelper = new SignInHelper(this);

        Bundle bundle = new Bundle();
        String id="1";
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, id);
        String name= "analytics";
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, name);
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

        mAuth = FirebaseAuth.getInstance();

        mAuth.getAccessToken(true);
        mFirebaseUser = mAuth.getCurrentUser();
        mAuth.signInAnonymously();

//        if(mFirebaseUser == null){
//
//        }else{
//            Intent intent = new Intent(this, MainActivity.class);
//            startActivity(intent);
//        }

        preferenceHelper = new WalkThroughHelper(this);
        skipBtn = findViewById(R.id.skip_walkthroughBtn);
        getStartedBtn = findViewById(R.id.getStartedButton);

        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(new CustomPagerAdapter(this));

        TabLayout tabLayout = findViewById(R.id.tabDots);
        tabLayout.setupWithViewPager(viewPager,true);

        if(signInHelper.getLogin().equals("customer")) {
            Intent intent = new Intent(WalkThroughActivity.this, MainActivity.class);
            startActivity(intent);
            this.finish();
        }
        else if(signInHelper.getLogin().equals("designer")) {
            Intent intent = new Intent(WalkThroughActivity.this, DashBoardActivity.class);
            startActivity(intent);
            this.finish();
        }
        else if(signInHelper.getLogin().equals("no")){
            Intent intent = new Intent(WalkThroughActivity.this, LandingActivity.class);
            startActivity(intent);
            this.finish();
        }
        else if(preferenceHelper.getIntro().equals("no")){
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
        Intent intent = new Intent(WalkThroughActivity.this, cls.getClass());
        startActivity(intent);
        this.finish();

    }
}
