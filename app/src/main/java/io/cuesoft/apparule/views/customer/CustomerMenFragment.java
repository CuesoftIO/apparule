package io.cuesoft.apparule.views.customer;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.cuesoft.apparule.R;


public class CustomerMenFragment extends DiscoverBaseFragment {

    public CustomerMenFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_customer_men, container, false);
        mRecyclerView = view.findViewById(R.id.custMenRecycler);
        initilaizeView();
        String [] imageArray = {"men10.jpg","men1.jpg","men12.png","men2.jpg"};
        initilaizeData(imageArray, "men");
        return view;
    }

}
