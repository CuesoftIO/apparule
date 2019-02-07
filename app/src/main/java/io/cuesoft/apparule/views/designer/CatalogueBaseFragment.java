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

import java.util.ArrayList;

import io.cuesoft.apparule.R;
import io.cuesoft.apparule.adapter.DesignerCatalogueReyclerAdapter;
import io.cuesoft.apparule.model.DesignerCatalogueRecyclerModel;

public class CatalogueBaseFragment extends Fragment {

    RecyclerView mRecyclerView;
    LinearLayoutManager mLayoutManager;
    DesignerCatalogueReyclerAdapter mAdapter;
    ArrayList<DesignerCatalogueRecyclerModel> mCatalogueData;

    public void initilaizeView(){
        mCatalogueData = new ArrayList<>();
        mAdapter = new DesignerCatalogueReyclerAdapter(this.getActivity(), mCatalogueData);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

    }

    public void initilaizeData(){
        TypedArray imageResources =
                getResources().obtainTypedArray(R.array.images);
        for(int i =0; i<imageResources.length(); i++){
            mCatalogueData.add(new DesignerCatalogueRecyclerModel("Versache Bags",
                    "A Lovely Product", "2 HOURS AGO",
                    "#61,000", imageResources.getResourceId(i,0)));
        }
        imageResources.recycle();
        mAdapter.notifyDataSetChanged();
    }

}
