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
public class WomenAccessoriesFragment extends CatalogueBaseFragment{


    public WomenAccessoriesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_women_accessories, container, false);
        mRecyclerView = view.findViewById(R.id.recyclerView_womenAccessories);
        initilaizeView();
        initilaizeData();
        return view;

    }


    @Override
    public void initilaizeData() {
        // super.initilaizeData();
        TypedArray imageResources =
                getResources().obtainTypedArray(R.array.women_images);
        for(int i =0; i<imageResources.length(); i++){
            mCatalogueData.add(new DesignerCatalogueRecyclerModel("Women Styles",
                    "Designed with love from Nikkita Coure", "7 HOURS AGO",
                    "#14,000", imageResources.getResourceId(i,0)));
        }
        imageResources.recycle();
        mAdapter.notifyDataSetChanged();


    }
}
