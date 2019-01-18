package io.cuesoft.apparule.views;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import io.cuesoft.apparule.R;

public class SignInActivity extends AppCompatActivity {
    private static final String TAG = "EmailPassword" ;
    private CardView signInButton;
    private EditText mUsernameField;
    private EditText mPasswordField;

    private FirebaseAuth mFirebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin_page);

        signInButton =  findViewById(R.id.signButton);
        mUsernameField = findViewById(R.id.username_signinField);
        mPasswordField = findViewById(R.id.password_signinField);
        mFirebaseAuth = FirebaseAuth.getInstance();

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //land.enterMain(v);
                if (validateForm()) {
                    signIn(mUsernameField.getText().toString(), mPasswordField.getText().toString());

                }else{
                   // Toast.makeText(SignInActivity.this,"Authentication false", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public  void signIn(String email, String password){
        Log.d( TAG, "signIn: " + email );


        //[START create_user_with_email]
        mFirebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //Sign in success, Ui with the signed-in use's information
                            FirebaseUser user = mFirebaseAuth.getCurrentUser();
                            Toast.makeText(SignInActivity.this, "Authentication Success.",
                                    Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent( SignInActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                        else{
                            //If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmailAndPassword:failure", task.getException());
                            Toast.makeText(SignInActivity.this, "Authentication failed. Please" +
                                            " check your connection and try again",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });


    }
    public boolean validateForm(){
        boolean valid = true;

        String email = mUsernameField.getText().toString();
        if(TextUtils.isEmpty(email)){
            mUsernameField.setError("Required");
            valid = false;
        }
        else{
            mUsernameField.setError(null);
        }

        String password = mPasswordField.getText().toString();
        if(TextUtils.isEmpty(password)){
            mPasswordField.setError("Required");
            valid =false;
        }
        else{
            mPasswordField.setError(null);
        }
        return valid;
    }

}
