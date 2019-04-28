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

import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;

import io.cuesoft.apparule.R;
import io.cuesoft.apparule.adapter.DesignerCatalogueReyclerAdapter;
import io.cuesoft.apparule.model.DesignerCatalogueRecyclerModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllCatalogueFragment extends CatalogueBaseFragment {



    public AllCatalogueFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all_catalogue, container, false);
        mRecyclerView = view.findViewById(R.id.allCatalogueRecyclerView);
        initilaizeView();
        initilaizeData();
        return  view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    /*public void initilaizeView(){
        mCatalogueData = new ArrayList<>();
        mAdapter = new DesignerCatalogueReyclerAdapter(this.getActivity(), mCatalogueData);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();
        imageRef = storageRef.child("designers");
    }*/

}
