package io.cuesoft.apparule.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import io.cuesoft.apparule.R;
import io.cuesoft.apparule.views.customer.CustomerSignUpActivity;
import io.cuesoft.apparule.views.customer.MainActivity;
import io.cuesoft.apparule.views.designer.DesignerSignUpActivity;

/**
 * This class handles the entire Signing In and Signing Up
 * of Customers and Designers
 * It uses multiple fragments to display the layouts for
 * Signing Up, Signing In, Customer SigningUp, Designer SignUp
 */
public class LandingActivity extends AppCompatActivity {
    //Memebers Initialization
    private static final String TAG = "EmailPassword";
    CardView signInButton;
    CardView signUpButton;

    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);


        signInButton = findViewById(R.id.signIn_landing);
        signUpButton = findViewById(R.id.signUp_landing);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent = new Intent(LandingActivity.this, SignInActivity.class);
            startActivity(intent);

            }
        });
        signUpButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(LandingActivity.this, CustomerSignUpActivity.class);
               startActivity(intent);

           }
       });

    }


    //Designer SignUpFragment
    public void designerSignUP(View view)
    {  Intent intent = new Intent(LandingActivity.this, DesignerSignUpActivity.class);
        startActivity(intent);
    }

    // MainPage
    public void enterMain(View view) {
        Intent intent = new Intent(LandingActivity.this, MainActivity.class);
        startActivity(intent);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
     //   onDestroy();
        finish();
    }
}
