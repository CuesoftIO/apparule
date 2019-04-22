package io.cuesoft.apparule.views.customer;


import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
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
import io.cuesoft.apparule.adapter.MainAdapter;
import io.cuesoft.apparule.adapter.SecondMainAdapter;
import io.cuesoft.apparule.model.DesignerCatalogueRecyclerModel;
import io.cuesoft.apparule.model.ImageModel;
import io.cuesoft.apparule.model.ItemsModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class DiscoverBaseFragment extends Fragment {

    RecyclerView mRecyclerView;
    LinearLayoutManager mLayoutManager;
    SecondMainAdapter mAdapter;
    ArrayList<ImageModel> mItemsData;
    FirebaseStorage storage;
    StorageReference storageRef;
    StorageReference imageRef;


    public void initilaizeView(){

        mItemsData = new ArrayList<>();
        mAdapter = new SecondMainAdapter(this.getActivity(), mItemsData);
        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();
        imageRef = storageRef.child("designers");

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        ViewCompat.setNestedScrollingEnabled(mRecyclerView, false);
    }

    public void initilaizeData(String [] imageArray, String categories){

      if(imageArray.length == 0) {
            imageArray = new String[]{"men10.jpg", "men8.jpg"};
        }
        for(String anImageArray : imageArray){
            imageRef = storageRef.child( categories +"/" + anImageArray);
            mItemsData.add(new ImageModel(imageRef));
        }



        mAdapter.notifyDataSetChanged();
    }

}
