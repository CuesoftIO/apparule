package io.cuesoft.apparule.views.customer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import io.cuesoft.apparule.R;
import io.cuesoft.apparule.helper.SignInHelper;
import io.cuesoft.apparule.views.LandingActivity;


public class SettingsActivity extends AppCompatActivity {
    TextView signOutBtn;
    private SignInHelper signInHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        signOutBtn = findViewById(R.id.signOutBtn);

        signInHelper = new SignInHelper(this);

        Toolbar toolbar = findViewById(R.id.toolbar1);
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.black));
        toolbar.setTitle("Settings");

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, ProfileActivity.class);
                startActivity(intent);

            }
        });

        signOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this , LandingActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |Intent.FLAG_ACTIVITY_CLEAR_TASK
                        |Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                signInHelper.putLogin("no");
            }
        });

    }
}
