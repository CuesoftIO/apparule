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
import com.google.firebase.storage.StorageReference;

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
    FirebaseStorage storage;
    StorageReference storageRef;
    StorageReference imageRef;


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

        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();
        imageRef = storageRef.child("designers");
    }

    public void initilaizeData(){
        String [] imageResources = {"product01.jpg","product02.jpg",
                "product03.jpg","product05.jpg","product04.jpg"};
      //  String [] imageCustomers = {" "};

        for(String anImageArray :imageResources){

            imageRef =storageRef.child("women/" + anImageArray);
            mRequestsData.add(new DesignerRequestsRecyclerModel("TChalla",
                    "Men Apparel", "2 HOURS AGO",
                    R.drawable.men2, imageRef ));
        }

        mAdapter.notifyDataSetChanged();
    }

}
