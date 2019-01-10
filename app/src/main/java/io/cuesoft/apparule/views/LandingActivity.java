package io.cuesoft.apparule.views;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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
        Fragment SignInFragment = new CustomerSignInFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(android.R.id.content, SignInFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void signup(View view) {
        Fragment SignUpFragment = new CustomerSignUpFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(android.R.id.content, SignUpFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void designer(View view) {
        setContentView(R.layout.designer_signin);
    }

    public void designerSignUP(View view) {
        setContentView(R.layout.designer_signup);
    }

    public void mainPage(View view) {
        Intent intent = new Intent(LandingActivity.this, HomeActivity.class);
        startActivity(intent);
    }
}
