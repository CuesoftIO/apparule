package io.cuesoft.apparule.views.designer;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.cuesoft.apparule.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class KidsFragment extends CatalogueBaseFragment {


    public KidsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_kids, container, false);
        mRecyclerView = view.findViewById(R.id.recyclerView_kids);
        initilaizeView();
        initilaizeData();
        return view;
    }

}
