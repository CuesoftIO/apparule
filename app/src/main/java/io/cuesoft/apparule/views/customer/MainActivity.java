package io.cuesoft.apparule.views.customer;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

import io.cuesoft.apparule.R;
import io.cuesoft.apparule.adapter.MainAdapter;
import io.cuesoft.apparule.adapter.SecondMainAdapter;
import io.cuesoft.apparule.helper.BottomNavigationViewHelper;
import io.cuesoft.apparule.model.ImageModel;
import io.cuesoft.apparule.model.ItemsModel;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private SecondMainAdapter mAdapter;
    private ArrayList<ImageModel> mItemsData;
    BottomNavigationView navigation;

    private FirebaseStorage storage;
    private StorageReference imageRef;
    StorageReference storageRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Bottom Navigation Initialization
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        //bottom navigation atrributes
        BottomNavigationViewHelper.removeShiftMode(navigation);
        navigation.setBackgroundColor(getResources().getColor(R.color.signInButton_Blue));
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //Firebase Storage
        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();
        imageRef = storageRef.child("designers");

        Toolbar toolbar = findViewById(R.id.toolbar_main);
        toolbar.setOverflowIcon(getResources().getDrawable(R.drawable.ic_shopping_cart_black_24dp));
        toolbar.setTitle("Home");
        toolbar.inflateMenu(R.menu.main_menu);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                if(id==R.id.shoppingCart){
                intentDelivery(new CartActivity());
                }
                return false;
            }
        });


        //Handling menu navigation
        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);

        //Initialization
        mItemsData = new ArrayList<>();
        mRecyclerView = findViewById(R.id.mainRecyclerView);
        mAdapter = new SecondMainAdapter(this, mItemsData);
        //recyclerview attributes
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //Adding data to recyclerview
        initializeData();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @TargetApi(Build.VERSION_CODES.O)
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment1;
            switch (item.getItemId()) {
                case R.id.navigation_favourites:
                    return true;
                case R.id.navigation_discovery:
                    intentDelivery(new DiscoveryActivity());
//                    Intent intent1 = new Intent(MainActivity.this, DiscoveryActivity.class);
//                    startActivity(intent1);
                    return true;
                case R.id.navigation_post:
                    Intent intent2 = new Intent(MainActivity.this, PostActivity.class);
                    startActivity(intent2);
                    return true;
                case R.id.navigation_notifications:

                    Intent intent3 = new Intent(MainActivity.this, NotificationsActivity.class);
                    startActivity(intent3);
                    return true;
                case R.id.navigation_account:

                    Intent intent4 = new Intent(MainActivity.this, ProfileActivity.class);
                    startActivity(intent4);
                    return true;
            }
            return false;
        }
    };

    public void initializeData(){
        String [] imageArray = {"men10.jpg","men11.jpg","men4.jpg","men8.jpg","men6.jpg"};

        for(String anImageArray: imageArray){
            imageRef = storageRef.child("designers/nkkita/" + anImageArray);
            mItemsData.add(new ImageModel(imageRef));
        }


        mAdapter.notifyDataSetChanged();

    }

    public void intentDelivery(Activity cls){
        Intent intent = new Intent(MainActivity.this, cls.getClass());
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        overridePendingTransition(0,0);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
