package io.cuesoft.apparule.views.designer;


import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import io.cuesoft.apparule.R;
import io.cuesoft.apparule.adapter.DesignerCatalogueReyclerAdapter;
import io.cuesoft.apparule.adapter.DesignerRequestsRecyclerAdapter;
import io.cuesoft.apparule.model.DesignerCatalogueRecyclerModel;
import io.cuesoft.apparule.model.DesignerRequestsRecyclerModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllFragment extends Fragment {

    public RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private DesignerRequestsRecyclerAdapter mAdapter;
    private ArrayList<DesignerRequestsRecyclerModel>  mRequestsData;


    public AllFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all, container, false);
        mRecyclerView = view.findViewById(R.id.allRequestRecyclerView);
        initilaizeView();
        initilaizeData();
        return view;
    }
    public void initilaizeView(){
        mRequestsData = new ArrayList<>();
        mAdapter = new DesignerRequestsRecyclerAdapter(this.getActivity(), mRequestsData);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

    }

    public void initilaizeData(){
        TypedArray imageResources =
                getResources().obtainTypedArray(R.array.images);
        TypedArray imageCustomers =
                getResources().obtainTypedArray(R.array.images_categories);
        for(int i =0; i<imageCustomers.length(); i++){

            mRequestsData.add(new DesignerRequestsRecyclerModel("Versache Bags",
                    "A Lovely Product", "2 HOURS AGO",
              imageCustomers.getResourceId(i,0), imageResources.getResourceId(i,0)));
        }
        imageResources.recycle();
        mAdapter.notifyDataSetChanged();
    }

}
