package io.cuesoft.apparule.views;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import io.cuesoft.apparule.R;
import io.cuesoft.apparule.adapter.MainAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class DiscoveryFragment extends Fragment {


    private TextView mTextMessage;
    private RecyclerView mRecyclerView;
    private MainAdapter mAdapter;
    public DiscoveryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_discovery, container, false);
        //mRecyclerView = view.findViewById(R.id.);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_discovery, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        mAdapter = new MainAdapter(getActivity());
//        mRecyclerView.setAdapter(mAdapter);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


    }
}
