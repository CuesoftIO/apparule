package io.cuesoft.apparule.views.designer;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
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
import io.cuesoft.apparule.views.customer.CustomerSignUpActivity;

public class DesignerSignUpActivity extends AppCompatActivity {

    private static final String TAG = "DesignerEmailPassowrd";
    private TextInputEditText businessName;
    private TextInputEditText designerEmail;
    private TextInputEditText designerAddress;
    private TextInputEditText designerCountry;

    private TextView signInText_signup;

    private CardView continueButton;
    private CardView signUpButton;

    private TextInputEditText designerPassword1;
    private TextInputEditText designerPassword2;
    String Email;
    private FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.designer_signup);

        //FirstPage Views Signup initialization
        businessName = findViewById(R.id.designerBusinessNameField);
        designerEmail = findViewById(R.id.designerEmailField);
        designerAddress = findViewById(R.id.designerAddressField);
        designerCountry = findViewById(R.id.designerCountryField);

        signInText_signup = findViewById(R.id.designer_signupText);

        mFirebaseAuth = FirebaseAuth.getInstance();

        //SecondPage Signup Initialization
        designerPassword1 = findViewById(R.id.designerPasswordField1);
        designerPassword2= findViewById(R.id.designerPasswordField2);

        continueButton = findViewById(R.id.designerContinue_button);
        continueButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(validateForm()){
                Email = designerEmail.getText().toString();
                secondButtonSignUp();
               }
            }
        });

        signInText_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DesignerSignUpActivity.this, SignInActivity.class);
                startActivity(intent);
            }
        });
    }

    private void createdesignerAccount(String email, String password) {
        Log.d(TAG, "createAccount: " + email);
        //[START create_user_with_email]
        mFirebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //Sign in success, Ui with the signed-in use's information
                            FirebaseUser user = mFirebaseAuth.getCurrentUser();
                            Toast.makeText(DesignerSignUpActivity .this, "Account Created.",
                                    Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(DesignerSignUpActivity .this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            //If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmailAndPassword:failure", task.getException());
                            Toast.makeText(DesignerSignUpActivity .this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }


    public void secondButtonSignUp(){
        //Caliing the second layout for registration
        setContentView(R.layout.designer_signin);
        signUpButton = findViewById(R.id.designerSignInButton);

        //SecondPage Signup Initialization
        designerPassword1 = findViewById(R.id.designerPasswordField1);
        designerPassword2= findViewById(R.id.designerPasswordField2);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateForm2()) {
                    createdesignerAccount(designerEmail.getText().toString(), designerPassword1.getText().toString());
                }
            }
        });
    }

    public boolean validateForm(){
        boolean valid = true;

        String email = designerEmail.getText().toString();

        if(TextUtils.isEmpty(email)){
            designerEmail.setError("Required");
            valid = false;
        }
        else{
            designerEmail.setError(null);
        }


        String businessNameText = businessName.getText().toString();

        if(TextUtils.isEmpty(businessNameText)){
            businessName.setError("Required");
            valid =false;
        }
        else{
            businessName.setError(null);
        }

        String designerAddressText = designerAddress.getText().toString();

        if(TextUtils.isEmpty(designerAddressText)){
            designerAddress.setError("Required");
            valid =false;
        }
        else{
            designerAddress.setError(null);
        }

        String designerCountryText = designerCountry.getText().toString();

        if(TextUtils.isEmpty(designerCountryText)){
            designerCountry.setError("Required");
            valid =false;
        }
        else{
            designerCountry.setError(null);
        }


        return valid;

    }

    public boolean validateForm2(){
        boolean valid = true;
        String password = designerPassword1.getText().toString();
        String password2 = designerPassword2.getText().toString();

        if(TextUtils.isEmpty(password)){
            designerPassword1.setError("Required");
            valid =false;

        }else if(!password.equals(password2)){
            designerPassword2.setError("Password does not match!!!");
            valid =false;
        }
        else{
            designerPassword1.setError(null);
            designerPassword2.setError(null);
        }
        return valid;
    }
}
