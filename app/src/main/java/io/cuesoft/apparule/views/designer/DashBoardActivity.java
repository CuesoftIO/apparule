package io.cuesoft.apparule.views.designer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import io.cuesoft.apparule.R;
import io.cuesoft.apparule.adapter.DesignerFragmentPagerAdapter;

public class DashBoardActivity extends DesignerBaseActivity {

    DesignerFragmentPagerAdapter mDesignerFragmentPagerAdapter;
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_dash_board);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //inflate your activity layout here

        //@SuppressLint("")
        View contentView = inflater.inflate(R.layout.activity_dash_board, null ,false);
        drawer.addView(contentView, 0);
        navigationView.setCheckedItem(R.id.nav_dashboard);

        //Create the adapter that will retrun a fragment for each of the three
        //Primary sections of the activity
        mDesignerFragmentPagerAdapter = new DesignerFragmentPagerAdapter(getSupportFragmentManager());

        //Set up the viewPager with the sections adapter.
        mViewPager = findViewById(R.id.dashboardViewpager);
        mViewPager.setAdapter(mDesignerFragmentPagerAdapter);


        tabLayout.setupWithViewPager(mViewPager);


    }
}
