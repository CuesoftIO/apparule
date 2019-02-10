package io.cuesoft.apparule.views.designer;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import java.util.ArrayList;

import io.cuesoft.apparule.R;
import io.cuesoft.apparule.adapter.DesignerRequestsRecyclerAdapter;
import io.cuesoft.apparule.model.DesignerCatalogueRecyclerModel;
import io.cuesoft.apparule.model.DesignerRequestsRecyclerModel;

public class RequestBaseFragment extends Fragment {

     RecyclerView mRecyclerView;
     LinearLayoutManager mLayoutManager;
     DesignerRequestsRecyclerAdapter mAdapter;
     ArrayList<DesignerRequestsRecyclerModel> mRequestsData;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);

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
                getResources().obtainTypedArray(R.array.men);
        for(int i =0; i<imageResources.length(); i++){

            mRequestsData.add(new DesignerRequestsRecyclerModel("TChalla",
                    "Men Apparel", "2 HOURS AGO",
                    R.drawable.men2, imageResources.getResourceId(i,0)));
        }
        imageResources.recycle();
        mAdapter.notifyDataSetChanged();
    }

}
