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
    CardView signInButton, signInButton2;
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
        Fragment SignInFragment = new SignInFragment();
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
        Fragment designerSignSecondFragment = new designerSecondFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(android.R.id.content, designerSignSecondFragment);
        transaction.addToBackStack(null);
        transaction.commit();}

    public void designerSignUP(View view)
    {
       Fragment designerSignFragment = new DesignerSignUPFragment();
       FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
       transaction.replace(android.R.id.content, designerSignFragment);
       transaction.addToBackStack(null);
       transaction.commit();
    }

    public void enterMain(View view) {
        Intent intent = new Intent(LandingActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void forgotpassword(View view) {
        Fragment ForgotPasswordFragment = new ForgotPasswordFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(android.R.id.content, ForgotPasswordFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
