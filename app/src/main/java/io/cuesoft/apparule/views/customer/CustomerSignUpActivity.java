package io.cuesoft.apparule.views.customer;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import io.cuesoft.apparule.R;
import io.cuesoft.apparule.views.MainActivity;
import io.cuesoft.apparule.views.SignInActivity;
import io.cuesoft.apparule.views.designer.DesignerSignUpActivity;

public class CustomerSignUpActivity extends AppCompatActivity {
    private static final String TAG = "SignUpEmailPassword";

    private CardView signUpButton;

    private TextInputEditText mFullNameField;
    private TextInputEditText mEmailField;
    private TextInputEditText mPasswordField1;
    private TextInputEditText mPasswordField2;

    private FirebaseAuth mFirebaseAuth;
    //TextView
    private TextView mSignInLink;
    private TextView mDesignerText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_page);

        //Initializing signUp form
        mFullNameField = findViewById(R.id.customerFullName);
        mEmailField = findViewById(R.id.CustomerEmail);
        mPasswordField1 = findViewById(R.id.customerPassword1);
        mPasswordField2 = findViewById(R.id.customerPassword2);

        //Initializing textViews form
        mSignInLink = findViewById(R.id.signinText);
        mDesignerText = findViewById(R.id.designerSignUpText);

        signUpButton= findViewById(R.id.signUpButton);

        mFirebaseAuth = FirebaseAuth.getInstance();

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validateForm()) {

                    createAccount(mEmailField.getText().toString(), mPasswordField1.getText().toString());

                } else {
                    Toast.makeText(CustomerSignUpActivity.this , "Authentication false or Please " +
                            "check your internet connection and try again", Toast.LENGTH_LONG).show();
                }
            }
        });
        //onClickListener handling the navigation to designerSignup
        //and Signing of users
        allOnClickListener();
    }


    public void createAccount(String email, String password) {
        Log.d(TAG, "createAccount: " + email);
        //[START create_user_with_email]
        mFirebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //Sign in success, Ui with the signed-in use's information
                            FirebaseUser user = mFirebaseAuth.getCurrentUser();
                            Toast.makeText(CustomerSignUpActivity.this, "Account Created.",
                                    Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(CustomerSignUpActivity.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            //If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmailAndPassword:failure", task.getException());
                            Toast.makeText(CustomerSignUpActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });
        }

        public void allOnClickListener(){
             /*Setting click listener for designerText to take users to designer
              SignUpPage*/
              mDesignerText.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      Intent intent = new Intent(CustomerSignUpActivity.this, DesignerSignUpActivity.class);
                      startActivity(intent);
                  }
              });
              /*Setting click listener for SignInLink to take users to Sign
              * in Page*/
              mSignInLink.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      Intent intent = new Intent(CustomerSignUpActivity.this, SignInActivity.class);
                      startActivity(intent);
                  }
              });
        }


    public boolean validateForm(){
        boolean valid = true;

        String email = mEmailField.getText().toString();
        if(TextUtils.isEmpty(email)){
            mEmailField.setError("Required");
            valid = false;
        }
        else{
            mEmailField.setError(null);
        }

        String password = mPasswordField1.getText().toString();
        String password2 = mPasswordField2.getText().toString();

        if(TextUtils.isEmpty(password)){
            mPasswordField1.setError("Required");
            valid =false;

        }else if(!password.equals(password2)){
            mPasswordField2.setError("Password does not match!!!");
            valid =false;
        }
        else{
            mPasswordField1.setError(null);
            mPasswordField2.setError(null);
        }

        String fullname = mFullNameField.getText().toString();
        if(TextUtils.isEmpty(fullname)){
            mFullNameField.setError("Required");
            valid =false;
        }
        else{
            mFullNameField.setError(null);
        }

        return valid;

    }


}

