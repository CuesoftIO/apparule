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
public class FabricFragment extends CatalogueBaseFragment {


    public FabricFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fabric, container, false);
        mRecyclerView = view.findViewById(R.id.recyclerView_fabric);
        initilaizeView();
        initilaizeData();
        return view;
    }

    @Override
    public void initilaizeData() {
        TypedArray imageResources =
                    getResources().obtainTypedArray(R.array.frabric);
            for(int i =0; i<imageResources.length(); i++){
                mCatalogueData.add(new DesignerCatalogueRecyclerModel("Fabrics",
                        "Designed with love from Nikkita Coure", "2 HOURS AGO",
                        "#10,000", imageResources.getResourceId(i,0)));
            }
            imageResources.recycle();
            mAdapter.notifyDataSetChanged();


        }

}
