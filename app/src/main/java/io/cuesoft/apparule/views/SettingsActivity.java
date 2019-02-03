package io.cuesoft.apparule.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import io.cuesoft.apparule.R;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = findViewById(R.id.toolbar1);
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.black));
        toolbar.setTitle("settings");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
    }
}
