package io.cuesoft.apparule.views.walkthrough;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import io.cuesoft.apparule.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Intro_screen1 extends Fragment {


    public Intro_screen1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
     return inflater.inflate(R.layout.intro_screen1,container,false);
    }

}
