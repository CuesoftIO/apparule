package io.cuesoft.apparule.views.customer;


import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.cuesoft.apparule.R;
import io.cuesoft.apparule.model.ItemsModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class CustomerWomenFragment extends DiscoverBaseFragment {


    public CustomerWomenFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_customer_women, container, false);
        mRecyclerView = view.findViewById(R.id.custWomenRecycler);
        initilaizeView();

        String [] imageArray = {"product01.jpg","product02.jpg",
                "product03.jpg","product05.jpg","product04.jpg"};
        initilaizeData(imageArray,"women");
        return view;

    }

}
