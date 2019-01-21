package io.cuesoft.apparule.views;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import io.cuesoft.apparule.R;
import io.cuesoft.apparule.adapter.MainAdapter;
import io.cuesoft.apparule.helper.BottomNavigationViewHelper;

public class DiscoveryActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private RecyclerView mRecyclerView;
    private MainAdapter mAdapter;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @TargetApi(Build.VERSION_CODES.O)
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment1;
            switch (item.getItemId()) {
                case R.id.navigation_favourites:
                    Intent intent1 = new Intent(DiscoveryActivity.this, MainActivity.class);
                    startActivity(intent1);
                    return true;
                case R.id.navigation_discovery:
                    return true;
                case R.id.navigation_post:
                    return true;
                case R.id.navigation_notifications:
                    return true;
                case R.id.navigation_account:
                    return true;
            }
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        BottomNavigationViewHelper.removeShiftMode(navigation);
        navigation.setBackgroundColor(getResources().getColor(R.color.bottom_navigation));

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);

//        mRecyclerView = findViewById(R.id.mainRecyclerView);
//        mAdapter = new MainAdapter(this);
//        mRecyclerView.setAdapter(mAdapter);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }


    private void loadFragment(Fragment fragment){
        //load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
