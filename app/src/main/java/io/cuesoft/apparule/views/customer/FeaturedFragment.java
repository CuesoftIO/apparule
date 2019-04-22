package io.cuesoft.apparule.views.customer;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.cuesoft.apparule.R;
import io.cuesoft.apparule.views.designer.CatalogueBaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class FeaturedFragment extends DiscoverBaseFragment{


    public FeaturedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_featured, container, false);
        mRecyclerView = view.findViewById(R.id.featured_recyclerView);

        initilaizeView();
        String [] imageArray = {"men10.jpg"};
        initilaizeData(imageArray, "men");
        // Inflate the layout for this fragment
        return view;
    }

}
