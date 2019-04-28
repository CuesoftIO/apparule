package io.cuesoft.apparule.views.designer;


import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.cuesoft.apparule.R;
import io.cuesoft.apparule.model.DesignerCatalogueRecyclerModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class WomenFragment extends CatalogueBaseFragment{


    public WomenFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_women, container, false);
        mRecyclerView = view.findViewById(R.id.recyclerView_Women);
        initilaizeView();
        initilaizeData();
        return view;
    }


}
