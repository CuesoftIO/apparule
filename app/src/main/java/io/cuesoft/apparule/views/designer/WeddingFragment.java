package io.cuesoft.apparule.views.designer;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.cuesoft.apparule.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class WeddingFragment extends CatalogueBaseFragment{


    public WeddingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_wedding, container, false);
        mRecyclerView = view.findViewById(R.id.recyclerView_Wedding);
        initilaizeView();
        initilaizeData();
        return view;
    }

}
