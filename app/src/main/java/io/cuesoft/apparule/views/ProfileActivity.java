package io.cuesoft.apparule.views;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import io.cuesoft.apparule.R;
import io.cuesoft.apparule.helper.BottomNavigationViewHelper;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        BottomNavigationViewHelper.removeShiftMode(navigation);
        navigation.setBackgroundColor(getResources().getColor(R.color.bottom_navigation));

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(4);
        menuItem.setChecked(true);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @TargetApi(Build.VERSION_CODES.O)
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment1;
            switch (item.getItemId()) {
                case R.id.navigation_favourites:
                    Intent intent1 = new Intent(ProfileActivity.this, MainActivity.class);
                    startActivity(intent1);
                    return true;
                case R.id.navigation_discovery:
                    Intent intent2 = new Intent(ProfileActivity.this, DiscoveryActivity.class);
                    startActivity(intent2);
                    return true;
                case R.id.navigation_post:

                    Intent intent3 = new Intent(ProfileActivity.this, PostActivity.class);
                    startActivity(intent3);
                    return true;
                case R.id.navigation_notifications:

                    Intent intent4 = new Intent(ProfileActivity.this, NotificationsActivity.class);
                    startActivity(intent4);
                    return true;
                case R.id.navigation_account:
                    return true;
            }
            return false;
        }
    };


}
