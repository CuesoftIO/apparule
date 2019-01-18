package io.cuesoft.apparule.views.designer;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
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
import io.cuesoft.apparule.views.MainActivity;
import io.cuesoft.apparule.views.customer.CustomerSignUpActivity;

public class DesignerSignUpActivity extends AppCompatActivity {

    private static final String TAG = "DesignerEmailPassowrd";
    private TextInputEditText businessName;
    private TextInputEditText designerEmail;
    private TextInputEditText designerAddress;
    private TextInputEditText designerCountry;

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

        mFirebaseAuth = FirebaseAuth.getInstance();

        //SecondPage Signup Initialization
        designerPassword1 = findViewById(R.id.designerPasswordField1);
        designerPassword2= findViewById(R.id.designerPasswordField2);


        continueButton = findViewById(R.id.designerContinue_button);
        continueButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
             Email = designerEmail.getText().toString();
                secondButtonSignUp();
             // Intent intent = new Intent(DesignerSignUpActivity.this, MainActivity.class);
            // startActivity(intent);
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

        setContentView(R.layout.designer_signin);
        signUpButton = findViewById(R.id.designerSignInButton);
        //SecondPage Signup Initialization
        designerPassword1 = findViewById(R.id.designerPasswordField1);
        designerPassword2= findViewById(R.id.designerPasswordField2);


        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createdesignerAccount(designerEmail.getText().toString(), designerPassword1.getText().toString());
            }
        });

    }
}
