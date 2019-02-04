package io.cuesoft.apparule.views.customer;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import io.cuesoft.apparule.R;
import io.cuesoft.apparule.helper.BottomNavigationViewHelper;

public class ProfileActivity extends AppCompatActivity {

    ImageView settingsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        BottomNavigationViewHelper.removeShiftMode(navigation);
        navigation.setBackgroundColor(getResources().getColor(R.color.bottom_navigation));

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);



       Toolbar toolbar = findViewById(R.id.profile_toolbar);
        toolbar.setTitle("Profile");
        toolbar.inflateMenu(R.menu.profile_menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(ProfileActivity.this, SettingsActivity.class);
                startActivity(intent);
                return false;
            }
        });

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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

}
