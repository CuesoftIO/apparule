package io.cuesoft.apparule.views.designer;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import io.cuesoft.apparule.R;

public class DesignerSettingsActivity extends DesignerBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //inflate your activity layout here!

        View contentView =inflater.inflate(R.layout.activity_designer_settings, null, false);
        drawer.addView(contentView, 0);
        navigationView.setCheckedItem(R.id.nav_settings);

        tabLayout.setVisibility(View.GONE);

    }
}
