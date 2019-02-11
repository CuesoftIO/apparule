package io.cuesoft.apparule.views;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

import io.cuesoft.apparule.R;
import io.cuesoft.apparule.helper.SignInHelper;
import io.cuesoft.apparule.model.Customer;
import io.cuesoft.apparule.model.Designer;
import io.cuesoft.apparule.views.customer.CustomerSignUpActivity;
import io.cuesoft.apparule.views.customer.MainActivity;
import io.cuesoft.apparule.views.designer.DashBoardActivity;

public class SignInActivity extends AppCompatActivity {

    private static final String TAG = "EmailPassword" ;
    private CardView signInButton;
    private EditText mUsernameField;
    private EditText mPasswordField;

    private TextView mForgotPassword;
    private TextView mDesignerSignUp;
    private TextView signInTextCardView;
    private ProgressBar signInProgress;

    //The variables for handling forgotten passwordpage
    private TextInputEditText mForgotEmailField;
    private CardView mForgotPasswordButton;
    private TextView signUP;
    private  SignInHelper signInHelper;

    private ProgressBar forgotPasswordProgressBar;
    private TextView forgotPassWordTextView;

    private FirebaseAuth mFirebaseAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference firebaseReference;

    String isCustomer;
    String isDesigner;
    String emailCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin_page);

        //Initailizing fields and button for signin layout
        signInButton =  findViewById(R.id.signButton);
        mUsernameField = findViewById(R.id.username_signinField);
        mPasswordField = findViewById(R.id.password_signinField);

        //SignIn ProgressBar
        signInProgress = findViewById(R.id.signin_progressBar);
        signInTextCardView = findViewById(R.id.signIn_cardViewText);

        //Firebase Authentication instance
        mFirebaseAuth = FirebaseAuth.getInstance();

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        firebaseReference = mFirebaseDatabase.getReference("customers");

        signInHelper = new SignInHelper(this);

        //Initialization for text fields
        mForgotPassword = findViewById(R.id.forgetPasswordText_signin);
        mDesignerSignUp = findViewById(R.id.designer_signupText_singup_in_signin);

        //Calling the Sign-in- method which sends credentials to Firebase for authentication
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateForm()) {
                    addFeedback();
                    signIn(mUsernameField.getText().toString(), mPasswordField.getText().toString());
                    }else{
                }
            }
        });

        // to the forgot password page
        mForgotPassword.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               forgotPasswordView();
           }
       });

       mDesignerSignUp.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(SignInActivity.this, CustomerSignUpActivity.class );
               startActivity(intent);
           }
       });

    }
    public  void signIn(final String email, String password){
        Log.d( TAG, "signIn: " + email );
        //[START create_user_with_email]
        try {
            mFirebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            emailCheck = email;

                        //Sign in success, Ui with the signed-in use's information
                            Toast.makeText(SignInActivity.this, "Authentication Success.",
                                    Toast.LENGTH_SHORT).show();
                        customerOrDesigner(Objects.requireNonNull(task.getResult()).getUser());

                            signInHelper.putLogin("yes");
                            removeFeedback();
                            finish();
                        } else {
                            //If sign in fails, display a message to the user.

                            Log.w(TAG, "createUserWithEmailAndPassword:failure", task.getException());
                            //Send toast Message to the user
                            Toast.makeText(SignInActivity.this, "Authentication failed. Please" +
                                            " check your connection and try again",
                                    Toast.LENGTH_SHORT).show();
                            //Handling the progress bar visiblitiy
                            removeFeedback();
                        }
                        }
                //Add on failure listener
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                if (e instanceof FirebaseAuthInvalidUserException) {

                    String errorCode =
                            ((FirebaseAuthInvalidUserException) e).getErrorCode();
                    //Handling progress bar visibility
                    removeFeedback();
                    if (errorCode.equals("ERROR_USER_NOT_FOUND")) {
                        mUsernameField.setError("Email not found,Signup");
                    }

                } else if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    mPasswordField.setError("Wrong Password");
                }
            }
        });
    }catch (Exception e){
        errorMessage("Error Signing in try again");
    }

    }
    /**
     * Method for validation of Username and Password
     *
     */ public void customerOrDesigner(FirebaseUser user){

          firebaseReference.child(user.getUid()).addValueEventListener( new ValueEventListener() {
              @Override
              public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                  // Get Post object and use the values to update the UI
                  Customer customer = dataSnapshot.getValue(Customer.class);
                  try{
                  assert customer != null;
                  isCustomer = customer.email;
                  }
                  catch(Exception e){
                      signInHelper.putLogin("designer");

                      Intent intent = new Intent(SignInActivity.this, DashBoardActivity.class);
                      intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                      startActivity(intent);
                  }
              /*    Designer designer = dataSnapshot.getValue(Designer.class);
                  assert designer != null;
                  isDesigner = designer.email;
*/
                 // errorMessage(isCustomer);
                  if(emailCheck.equalsIgnoreCase(isCustomer)){
                      signInHelper.putLogin("customer");
                      Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                      intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                      startActivity(intent);
                  }
                  else{
                  }
              }

              @Override
              public void onCancelled(DatabaseError databaseError) {
                  // Getting Post failed, log a message
                  Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                  // ...
              }
          });

       }


    public boolean validateForm(){
        boolean valid = true;
       //Email validation
        String email = mUsernameField.getText().toString();
            if(TextUtils.isEmpty(email)){
                mUsernameField.setError("Required");
                valid = false;
            }
            else{
                mUsernameField.setError(null);
            }
         //Password Valdating
        String password = mPasswordField.getText().toString();
           if(TextUtils.isEmpty(password)){
                mPasswordField.setError("Required");
                valid =false;
            }
         else{
            mPasswordField.setError(null);
        }return valid;
    }

    public void errorMessage(String errorMessage){
        Toast.makeText(SignInActivity.this, errorMessage,
                Toast.LENGTH_SHORT).show();
    }

    private void forgotPasswordView() {
        setContentView(R.layout.forgot_password);

        mForgotEmailField = findViewById(R.id.forgotPasswordUsername_Field);
        mForgotPasswordButton = findViewById(R.id.forgotPasswordButton);
        signUP = findViewById(R.id.signup_forgotPassword);

        forgotPassWordTextView = findViewById(R.id.forgotPasswordCardtextView);
        forgotPasswordProgressBar = findViewById(R.id.forgotPassword_progressBar);

        signUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInActivity.this, CustomerSignUpActivity.class);
                startActivity(intent);
                }
        });
        mForgotPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String email=mForgotEmailField.getText().toString();
                if(TextUtils.isEmpty(email)){
                   mForgotEmailField.setError("Required");
               }

                else{
                    forgotPasswordProgressBar.setVisibility(View.VISIBLE);
                    forgotPassWordTextView.setVisibility(View.INVISIBLE);
                    errorMessage("working on it, Try again later");
                    mForgotPasswordButton.setActivated(false);
                }
            }
        });
    }

    public void addFeedback(){
        signInTextCardView.setVisibility(View.INVISIBLE);
        signInProgress.setVisibility(View.VISIBLE);

    }

    public void removeFeedback(){
        signInTextCardView.setVisibility(View.VISIBLE);
        signInProgress.setVisibility(View.INVISIBLE);
    }

}
