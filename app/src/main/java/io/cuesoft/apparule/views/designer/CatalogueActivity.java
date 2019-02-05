package io.cuesoft.apparule.views.designer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;

import io.cuesoft.apparule.R;
import io.cuesoft.apparule.adapter.DesignerCataloguePagerAdapter;

public class CatalogueActivity extends DesignerBaseActivity {

    DesignerCataloguePagerAdapter mDesignerCataloguePagerAdapter;
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View contentView = inflater.inflate(R.layout.activity_catalogue, null , false);
        drawer.addView(contentView, 0);
        navigationView.setCheckedItem(R.id.nav_catalogue);
        toolbar.setTitle("Catalogue");

        mDesignerCataloguePagerAdapter = new DesignerCataloguePagerAdapter(getSupportFragmentManager());
        mViewPager = findViewById(R.id.catalogueViewPager);
        mViewPager.setAdapter(mDesignerCataloguePagerAdapter);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setupWithViewPager(mViewPager);

      //  toolbar.setBackgroundColor(getResources().getColor(R.color.bottom_navigation));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CatalogueActivity.this, AddCatalogueActivity.class);
                startActivity(intent);
            }
        });
    }

}
