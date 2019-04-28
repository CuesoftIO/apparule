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

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

import io.cuesoft.apparule.R;
import io.cuesoft.apparule.adapter.DesignerCatalogueReyclerAdapter;
import io.cuesoft.apparule.model.DesignerCatalogueRecyclerModel;
import io.cuesoft.apparule.viewmodel.MainActivityViewModel;

public class CatalogueBaseFragment extends Fragment {

    RecyclerView mRecyclerView;
    LinearLayoutManager mLayoutManager;
    DesignerCatalogueReyclerAdapter mAdapter;
    ArrayList<DesignerCatalogueRecyclerModel> mCatalogueData;

    FirebaseStorage storage;
    StorageReference storageRef;
    StorageReference imageRef;


    public void initilaizeView(){
        mCatalogueData = new ArrayList<>();
        mAdapter = new DesignerCatalogueReyclerAdapter(this.getActivity(), mCatalogueData);
        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();
        imageRef = storageRef.child("designers");

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
    }


    public void initilaizeData(){
        String [] imageResources = {"product01.jpg","product02.jpg",
                "product03.jpg","product05.jpg","product04.jpg"};


        for(String anImageArray : imageResources){
            imageRef =storageRef.child("women/" + anImageArray);
            mCatalogueData.add(new DesignerCatalogueRecyclerModel("Versache Bags",
                    "Designed with love from Nikkita Coure", "2 HOURS AGO",
                    "#61,000", imageRef));
        }

        mAdapter.notifyDataSetChanged();
    }

}
