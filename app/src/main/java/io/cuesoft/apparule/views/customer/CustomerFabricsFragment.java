package io.cuesoft.apparule.views.customer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.cuesoft.apparule.R;

public class CustomerFabricsFragment extends DiscoverBaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_customer_fabrics, container, false);
        mRecyclerView = view.findViewById(R.id.custFabricRecycler);
        initilaizeView();
        String [] imageArray ={"frabic1.jpg","frabic2.jpg","frabic3.jpg","frabic4.jpg"};
      initilaizeData(imageArray,"Fabric");
        return view;
    }


}
