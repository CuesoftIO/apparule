package io.cuesoft.apparule.views.customer;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import io.cuesoft.apparule.R;
import io.cuesoft.apparule.adapter.DiscoverAdapter;
import io.cuesoft.apparule.adapter.MainAdapter;
import io.cuesoft.apparule.helper.BottomNavigationViewHelper;
import io.cuesoft.apparule.interfaces.OnCategoriesClickListener;
import io.cuesoft.apparule.model.CategoriesItemModel;
import io.cuesoft.apparule.model.ItemsModel;

public class DiscoveryActivity extends AppCompatActivity
              implements OnCategoriesClickListener {

    //Memeber variable
    private RecyclerView mRecyclerView1;
    private RecyclerView mRecyclerView2;

    private MainAdapter mAdapter;
    private ArrayList<ItemsModel> mItemsData;
    private  ArrayList<CategoriesItemModel> mCategoriesData;
    BottomNavigationView navigation;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discovery);
        //Arraylist for data
        mItemsData = new ArrayList<>();
        mCategoriesData = new ArrayList<>();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        BottomNavigationViewHelper.removeShiftMode(navigation);
        navigation.setBackgroundColor(getResources().getColor(R.color.bottom_navigation));
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //Setting Menu index
        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);

        Toolbar toolbar = findViewById(R.id.discover_toolbar);
        toolbar.setTitle("Discover");
        toolbar.inflateMenu(R.menu.discover_menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(DiscoveryActivity.this, SearchActivity.class);
                startActivity(intent);
                return false;
            }
        });
        //Handling RecyclerView
        bottomNavigationView();
        //Data for RecyclerView
        categoriesRecyclerView();
        detailsRecyclerView();
        initializeData(R.array.images);
        categories();

    }

    public DiscoveryActivity(){

    }

   public  DiscoveryActivity(String imageResources){
//        this.ImageResources = getResources().obtainTypedArray(R.array.images);;
    }

    public void bottomNavigationView(){


    }

    public void categoriesRecyclerView(){
        mRecyclerView1 = findViewById(R.id.categories_Recyclerview);
        final DiscoverAdapter mAdapter1 = new DiscoverAdapter(this, mCategoriesData,this);
        mRecyclerView1.setAdapter(mAdapter1);
        mRecyclerView1.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
     //   mRecyclerView1.addOnItemTouchListener(new RecyclerItemCl);
        ViewCompat.setNestedScrollingEnabled(mRecyclerView1, false);
    }

    public void detailsRecyclerView(){
        mRecyclerView2 = findViewById(R.id.discoverRecyclerView);
        mAdapter = new MainAdapter(this, mItemsData);
        mRecyclerView2.setAdapter(mAdapter);
        mRecyclerView2.setLayoutManager(new LinearLayoutManager(this));
        ViewCompat.setNestedScrollingEnabled(mRecyclerView2,false);
    }

    @Override
    public void onCategoriesClick(String CategoriesData) {
        switch(CategoriesData){
            case "Featured":
                initializeData(R.array.images);
            case "Women":
                initializeData(R.array.categories);
            case "Men":
            case "Kids":
            case "Women Accessories":
            case "Men Accessories":
            case "Kids Accessories":
            case "Wedding":
            case "Fabric":

    }


    }
    public void initializeData(int id){

        TypedArray ImageResources =
                getResources().obtainTypedArray(id);

        for(int i =0; i<ImageResources.length(); i++){
            mItemsData.add(new ItemsModel( ImageResources.getResourceId(i,0)));
        }

        ImageResources.recycle();
        mAdapter.notifyDataSetChanged();
    }
    public  void categoriesData(int id){

    TypedArray    ImageResources = getResources().obtainTypedArray(id);

        for(int i =0; i<ImageResources.length(); i++){
            mItemsData.add(new ItemsModel( ImageResources.getResourceId(i,0)));
        }

        ImageResources.recycle();
        mAdapter.notifyDataSetChanged();
    }
    public void categories(){
        String[] categories = getResources()
                .getStringArray(R.array.categories);

        TypedArray ImageCategories= getResources()
                .obtainTypedArray(R.array.images_categories);

        for(int i=0; i<ImageCategories.length(); i++){
            mCategoriesData.add(new CategoriesItemModel(categories[i], ImageCategories.getResourceId(i, 0)));
        }

        ImageCategories.recycle();
        mAdapter.notifyDataSetChanged();
    }

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
                    Intent intent2 = new Intent(DiscoveryActivity.this, PostActivity.class);
                    startActivity(intent2);
                    return true;

                case R.id.navigation_notifications:
                    Intent intent3 = new Intent(DiscoveryActivity.this, NotificationsActivity.class);
                    startActivity(intent3);
                    return true;

                case R.id.navigation_account:
                    Intent intent4 = new Intent(DiscoveryActivity.this, ProfileActivity.class);
                    startActivity(intent4);
                    return true;
            }
            return false;
        }
    };



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
