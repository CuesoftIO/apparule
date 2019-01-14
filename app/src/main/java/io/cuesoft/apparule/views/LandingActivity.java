package io.cuesoft.apparule.views;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import io.cuesoft.apparule.R;

public class LandingActivity extends AppCompatActivity {
    CardView signInButton;
    CardView signUpButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        signInButton = findViewById(R.id.signIn_landing);
        signUpButton = findViewById(R.id.signUp_landing);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            signin(v);
            }
        });

       signUpButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               signup(v);
           }
       });
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

    public void designerSignUP(View view)
    {
        setContentView(R.layout.designer_signup);
    }

    public void enterMain(View view) {
        Intent intent = new Intent(LandingActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
