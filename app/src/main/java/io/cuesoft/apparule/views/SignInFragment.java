package io.cuesoft.apparule.views;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;
import java.util.concurrent.Executor;

import io.cuesoft.apparule.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignInFragment extends Fragment {
    private static final String TAG = "EmailPassword";

    LandingActivity land = new LandingActivity();
    private CardView signInButton;
    private EditText mUsernameField;
    private EditText mPasswordField;

    private FirebaseAuth mFirebaseAuth;

    public SignInFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.signin_page, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        signInButton =  view.findViewById(R.id.signButton);
        mUsernameField = view.findViewById(R.id.username_signinField);
        mPasswordField = view.findViewById(R.id.password_signinField);
        mFirebaseAuth = FirebaseAuth.getInstance();

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //land.enterMain(v);
                if (validateForm()) {
                    land.createAccount(mUsernameField.getText().toString(), mPasswordField.getText().toString());
                }else{
                    Toast.makeText(getActivity(),"Authentication false", Toast.LENGTH_LONG).show();
                }
            }
        });
        super.onViewCreated(view, savedInstanceState);

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
