package io.cuesoft.apparule.views.designer;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.Objects;

import io.cuesoft.apparule.R;
import io.cuesoft.apparule.helper.SignInHelper;
import io.cuesoft.apparule.model.Designer;
import io.cuesoft.apparule.views.customer.MainActivity;
import io.cuesoft.apparule.views.SignInActivity;
import pub.devrel.easypermissions.EasyPermissions;

public class DesignerSignUpActivity extends AppCompatActivity implements
        AdapterView.OnItemClickListener {
    private static final String TAG = "DesignerEmailPassowrd";
    private static final int PICK_IMAGE = 2;
   // final int PIC_CROP = 2;
    //first designerPage Views
    private TextInputEditText businessName;
    private TextInputEditText designerEmail;
    private TextInputEditText designerAddress;

    private CardView continueButton;
    private TextView signInText_signup;
    private String Countries[] ={
                "Nigeria",
                "America",
                "Togo",
                "Ghana",
                "Cameroon",
                "Algeria",
                "South Africa",
                "Benin",
                "Ivory Coast",
                "Gambia"
            };
    //Second Designer Views
    private TextView designer2CardViewText;
    private ProgressBar designersSignUp2progressBar;
    private CardView signUp2Button;
    private  TextView businessLogoText;
    private TextInputEditText designerPassword1;
    private TextInputEditText designerPassword2;
    String Email;
    private FirebaseAuth mFirebaseAuth;
    private DatabaseReference mDatabase;
    private String[] galleryPermissions = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
    //Camera Views
    final int CAMERA_CAPTURE =1;
    //captured picture uri
    private Uri picUri;
    private SignInHelper signInHelper;
    String businessName1;
    String email;
    String address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.designer_signup);

        initialization();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,Countries);

        Locale[] locale = Locale.getAvailableLocales();
        ArrayList<String> countries = new ArrayList<>();
        String country;
        for(Locale loc: locale){
            country = loc.getDisplayCountry();
            if(country.length() >0 && !countries.contains(country)){
                countries.add(country);
            }
            Collections.sort(countries, String.CASE_INSENSITIVE_ORDER);

            Spinner citizenship =(Spinner)findViewById(R.id.designerSpinner);
           ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, countries);
           citizenship.setAdapter(adapter1);
        }
        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        signInHelper = new SignInHelper(this);

//        designerCountry.setAdapter(adapter);
  //      designerCountry.setOnItemClickListener(this);

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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //fetch the user selected value
        String item = parent.getItemAtPosition(position).toString();
        //create Toast with user selected value
        //Toast.makeText(DesignerSignUpActivity.this, "Selected Item is: \t" + item, Toast.LENGTH_LONG).show();

    }

    public void initialization(){
        //FirstPage Views Signup initialization
        businessName = findViewById(R.id.designerBusinessNameField);
        designerEmail = findViewById(R.id.designerEmailField);
        designerAddress = findViewById(R.id.designerAddressField);


        signInText_signup = findViewById(R.id.designer_signupText);
        mFirebaseAuth = FirebaseAuth.getInstance();

        //SecondPage Signup Initialization
        designerPassword1 = findViewById(R.id.designerPasswordField1);
        designerPassword2= findViewById(R.id.designerPasswordField2);

        //Button to the second designer page
        continueButton = findViewById(R.id.designerContinue_button);
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
        //Business clicked
        businessLogoText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickImage();
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
                            onAuthSuccess(Objects.requireNonNull(task.getResult()).getUser());
                            //Making Progress Bar Invisible and text Visible
                            designersSignUp2progressBar.setVisibility(View.INVISIBLE);
                            designer2CardViewText.setVisibility(View.VISIBLE);
                            signInHelper.putLogin("designer");
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
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                designersSignUp2progressBar.setVisibility(View.INVISIBLE);
                designer2CardViewText.setVisibility(View.VISIBLE);
            }
        });

    }

    private void onAuthSuccess(FirebaseUser user){

        Intent intent = new Intent(DesignerSignUpActivity.this, DashBoardActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);

        writeNewUser(user.getUid(), businessName.getText().toString(), designerEmail.getText()
                .toString(), designerAddress.getText().toString(), "Nigeria");
    }

    public void writeNewUser(String userId, String businessname, String email,
                             String address, String country){
        Designer designer = new Designer(businessname,email,address,country);
        mDatabase.child("designer").child(userId).setValue(designer);
    }


   public void  pickImage(){
       Intent intent = new Intent();
       intent.setType("image/*");
       intent.setAction(Intent.ACTION_GET_CONTENT);
       startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && null != data) {
            if (EasyPermissions.hasPermissions(DesignerSignUpActivity.this, galleryPermissions)) {

                Uri selectedImage = data.getData();
                String[] filePathColumn = { MediaStore.Images.Media.DATA };

                Cursor cursor = getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String picturePath = cursor.getString(columnIndex);
                cursor.close();

                ImageView imageView = (ImageView) findViewById(R.id.business_logoImage);
                Glide.with(this)
                        .load(BitmapFactory.decodeFile(picturePath))
                        .apply(new RequestOptions(). optionalCircleCrop())
                        .into(imageView);

                //imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));


            } else {
                EasyPermissions.requestPermissions(this, "Access for storage",
                        101, galleryPermissions);
                pickImage();
            }

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
        } else{
            businessName.setError(null);
        }
        String designerAddressText = designerAddress.getText().toString();

        if(TextUtils.isEmpty(designerAddressText)){
            designerAddress.setError("Required");
            valid =false;
        } else{
            designerAddress.setError(null);
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