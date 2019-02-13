package io.cuesoft.apparule.views.customer;


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
import io.cuesoft.apparule.adapter.MainAdapter;
import io.cuesoft.apparule.model.DesignerCatalogueRecyclerModel;
import io.cuesoft.apparule.model.ItemsModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class DiscoverBaseFragment extends Fragment {

    RecyclerView mRecyclerView;
    LinearLayoutManager mLayoutManager;
    MainAdapter mAdapter;
    ArrayList<ItemsModel> mItemsData;



    public void initilaizeView(){
        mItemsData = new ArrayList<>();
        mAdapter = new MainAdapter(this.getActivity(), mItemsData);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

    }
    public void initilaizeData(){

        TypedArray ImageResources =
                getResources().obtainTypedArray(R.array.men_accesories);

        for(int i =0; i<ImageResources.length(); i++){
            mItemsData.add(new ItemsModel( ImageResources.getResourceId(i,0)));
        }

        ImageResources.recycle();
        mAdapter.notifyDataSetChanged();
    }

}
