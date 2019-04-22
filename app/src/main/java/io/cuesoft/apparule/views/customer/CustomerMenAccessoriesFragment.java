package io.cuesoft.apparule.views.customer;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.cuesoft.apparule.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CustomerMenAccessoriesFragment extends DiscoverBaseFragment {


    public CustomerMenAccessoriesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_customer_men_accessories, container, false);
       mRecyclerView = view.findViewById(R.id.custMenAccesoriesRecycler);
       initilaizeView();
       String [] imageArray = {"menaccesories.jpg", "watch1.jpg"};
       initilaizeData(imageArray,"menAccessories");
       return view;
    }

}
