package io.cuesoft.apparule.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import io.cuesoft.apparule.R;

public class LandingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
    }

    public void signin(View view) {

        setContentView(R.layout.signin_page);
    }

    public void signup(View view) {
        setContentView(R.layout.siginup_page);
    }

    public void designer(View view) {
        setContentView(R.layout.designer_signin);
    }

    public void designerSignUP(View view) {
        setContentView(R.layout.designer_signup);
    }
}
