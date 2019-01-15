package io.cuesoft.apparule.views;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.cuesoft.apparule.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignInFragment extends Fragment {
    LandingActivity land = new LandingActivity();
    CardView signInButton;

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
        signInButton = (CardView) view.findViewById(R.id.signButton);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // land.enterMain(v);
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });super.onViewCreated(view, savedInstanceState);

    }
}
