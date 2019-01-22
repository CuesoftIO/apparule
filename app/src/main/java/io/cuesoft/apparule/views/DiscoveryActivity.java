package io.cuesoft.apparule.views;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;

import io.cuesoft.apparule.R;
import io.cuesoft.apparule.adapter.DiscoverAdapter;
import io.cuesoft.apparule.adapter.MainAdapter;
import io.cuesoft.apparule.helper.BottomNavigationViewHelper;
import io.cuesoft.apparule.model.ItemsModel;

public class DiscoveryActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private RecyclerView mRecyclerView1;
    private RecyclerView mRecyclerView2;

    private MainAdapter mAdapter;
    private ArrayList<ItemsModel> mItemsData;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @TargetApi(Build.VERSION_CODES.O)
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment1;
            switch (item.getItemId()) {
                case R.id.navigation_favourites:
                    Intent intent1 = new Intent(DiscoveryActivity.this, MainActivity.class);
                    startActivity(intent1);
                    return true;
                case R.id.navigation_discovery:
                    return true;
                case R.id.navigation_post:
                    return true;
                case R.id.navigation_notifications:
                    return true;
                case R.id.navigation_account:
                    return true;
            }
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discovery);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        BottomNavigationViewHelper.removeShiftMode(navigation);
        navigation.setBackgroundColor(getResources().getColor(R.color.bottom_navigation));

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);

        mItemsData = new ArrayList<>();

        mRecyclerView1 = findViewById(R.id.categories_Recyclerview);
        DiscoverAdapter mAdapter1 = new DiscoverAdapter(this, mItemsData);
        mRecyclerView1.setAdapter(mAdapter1);
        mRecyclerView1.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        mRecyclerView2 = findViewById(R.id.discoverRecyclerView);
        mAdapter = new MainAdapter(this, mItemsData);
        mRecyclerView2.setAdapter(mAdapter);
        mRecyclerView2.setLayoutManager(new LinearLayoutManager(this));
        initializeData();
    }


    public void initializeData(){
        TypedArray ImageResources =
                getResources().obtainTypedArray(R.array.images);

        for(int i =0; i<ImageResources.length(); i++){
            mItemsData.add(new ItemsModel( ImageResources.getResourceId(i,0)));
        }

        ImageResources.recycle();
        mAdapter.notifyDataSetChanged();

    }


    private void loadFragment(Fragment fragment){
        //load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
