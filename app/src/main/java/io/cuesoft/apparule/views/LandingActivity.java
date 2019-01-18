package io.cuesoft.apparule.views;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import io.cuesoft.apparule.R;
import io.cuesoft.apparule.views.customer.CustomerSignUpActivity;
import io.cuesoft.apparule.views.customer.CustomerSignUpFragment;
import io.cuesoft.apparule.views.designer.DesignerSignUPFragment;
import io.cuesoft.apparule.views.designer.DesignerSignUpActivity;
import io.cuesoft.apparule.views.designer.designerSecondFragment;

/**
 * This class handles the entire Signing In and Signing Up
 * of Customers and Designers
 * It uses multiple fragments to display the layouts for
 * Signing Up, Signing In, Customer SigningUp, Designer SignUp
 */
public class LandingActivity extends AppCompatActivity {

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

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();



        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
               FirebaseUser user = firebaseAuth.getCurrentUser();
               if(user == null){

               }
               else{
                   Intent intent = new Intent(LandingActivity.this, MainActivity.class);
                   startActivity(intent);
               }
            }
        };

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

    public  void createAccount(String email, String password){
        Log.d( TAG, "createAccount: " + email );


        //[START create_user_with_email]
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //Sign in success, Ui with the signed-in use's information
                            FirebaseUser user = mFirebaseAuth.getCurrentUser();
                            Toast.makeText(LandingActivity.this, "Authentication Success.",
                                    Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent( LandingActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                        else{
                            //If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmailAndPassword:failure", task.getException());
                            Toast.makeText(LandingActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });


    }



    //Sigining In
    public void signin(View view) {
        Fragment SignInFragment = new SignInFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(android.R.id.content, SignInFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    //Customer SignUp
    public void signup(View view) {
        Fragment SignUpFragment = new CustomerSignUpFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(android.R.id.content, SignUpFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    //Designer SecondFragment
    public void designer(View view) {
        Fragment designerSignSecondFragment = new designerSecondFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(android.R.id.content, designerSignSecondFragment);
        transaction.addToBackStack(null);
        transaction.commit();
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

    //ForgotPassword Fragment
    public void forgotpassword(View view) {
        Fragment ForgotPasswordFragment = new ForgotPasswordFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(android.R.id.content, ForgotPasswordFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
