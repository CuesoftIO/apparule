package io.cuesoft.apparule.views.designer;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
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
    final int PIC_CROP = 2;
    //first designerPage Views
    private TextInputEditText businessName;
    private TextInputEditText designerEmail;
    private TextInputEditText designerAddress;
    private TextInputEditText designerCountry;
    private CardView continueButton;
    private TextView signInText_signup;

    //SEcond Designer Views
    private TextView designer2CardViewText;
    private ProgressBar designersSignUp2progressBar;
    private CardView signUp2Button;
    private  TextView businessLogoText;
    private TextInputEditText designerPassword1;
    private TextInputEditText designerPassword2;
    String Email;
    private FirebaseAuth mFirebaseAuth;

    //Camera Views
    final int CAMERA_CAPTURE =1;
    //captured picture uri
    private Uri picUri;
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

        //Button to the second designer page
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
    /**
    ** Method for Creating Designer Account
     *
     **/
    public void secondButtonSignUp(){
        //Caliing the second layout for registration
        setContentView(R.layout.designer_signin);
        //CardView Elements
        signUp2Button = findViewById(R.id.designerSignInButton);
        designer2CardViewText = findViewById(R.id.designer2textCardView);

        designersSignUp2progressBar = findViewById(R.id.designersignIn_progressBar);

        //SecondPage Signup Initialization
        designerPassword1 = findViewById(R.id.designerPasswordField1);
        designerPassword2= findViewById(R.id.designerPasswordField2);
        businessLogoText = findViewById(R.id.business_logoText);

        businessLogoText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCamera();
            }
        });
        signUp2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                designersSignUp2progressBar.setVisibility(View.VISIBLE);
                designer2CardViewText.setVisibility(View.INVISIBLE);
                if(validateForm2()) {
                    createdesignerAccount(designerEmail.getText().toString(), designerPassword1.getText().toString());
                }
            }
        });
    }

    /**
     * Method for Creating designer Account
     * @param email
     * @param password
     */

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
                            //Making Progress Bar Invisible and text Visible
                            designersSignUp2progressBar.setVisibility(View.INVISIBLE);
                            designer2CardViewText.setVisibility(View.VISIBLE);
                        }

                        else {
                            //If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmailAndPassword:failure", task.getException());
                            Toast.makeText(DesignerSignUpActivity .this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //Making Progress Bar Invisible and text Visible
                            designersSignUp2progressBar.setVisibility(View.INVISIBLE);
                            designer2CardViewText.setVisibility(View.VISIBLE);
                        }
                    }
                });

    }



    public void openCamera(){
        try{
            //use standard intent to capture an image
            Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            //we will handle the returned data in onActivityResult
            startActivityForResult(captureIntent, CAMERA_CAPTURE);
        }catch(Exception e){
            String errorMessage = "Whoops - your device doesn't support capturing images!";
            Toast toast = Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT);
            toast.show();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            //user is returning from capturing an image using the camera
            if(requestCode == CAMERA_CAPTURE){
                //get the Uri for the captured image
                picUri = data.getData();
                performCrop();
            }else if(requestCode == PIC_CROP){
                //get the returned data
                Bundle extras = data.getExtras();
                //get the cropped bitmap
                Bitmap thePic = extras.getParcelable("data");
                //retrieve a reference to the ImageView
                ImageView picView = (ImageView)findViewById(R.id.business_logoImage);
                //display the returned cropped image
                picView.setImageBitmap(thePic);
            }
        }
    }

    private void performCrop() {
        try
        {
            Intent cropIntent = new Intent("io.cuesoft.apparule.camera.action.CROP");
            //indicate image type and Uri
            cropIntent.setDataAndType(picUri, "image/*");
            //set crop properties
            cropIntent.putExtra("crop", "true");
            //indicate aspect of desired crop
            cropIntent.putExtra("aspectX", 1);
            cropIntent.putExtra("aspectY", 1);
            //indicate output X and Y
            cropIntent.putExtra("outputX", 256);
            cropIntent.putExtra("outputY", 256);
            //retrieve data on return
            cropIntent.putExtra("return-data", true);
            //start the activity - we handle returning in onActivityResult
            startActivityForResult(cropIntent, PIC_CROP);

        }catch(ActivityNotFoundException e){
            //display an error message
            String errorMessage ="Your devicde does not support the crop activity";
            Toast toast = Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT);
            toast.show();
        }
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