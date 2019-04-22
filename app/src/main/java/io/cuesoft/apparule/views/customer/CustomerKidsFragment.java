package io.cuesoft.apparule.views.customer;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.cuesoft.apparule.R;


public class CustomerKidsFragment extends DiscoverBaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      // Inflate the layout for this fragment
      View view = inflater.inflate(R.layout.fragment_customer_kids, container, false);
      mRecyclerView = view.findViewById(R.id.custKidsRecycler);
      initilaizeView();
       String [] imageArray = {"kid.jpg"};
      initilaizeData(imageArray ,"kids");
      return view;
    }

}
