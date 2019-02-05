package io.cuesoft.apparule.views.designer;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import io.cuesoft.apparule.R;

import static android.view.View.GONE;

public class DesignerProfileActivity extends DesignerBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View contentView =inflater.inflate(R.layout.activity_designer_profile, null, false);
        drawer.addView(contentView, 0);
        navigationView.setCheckedItem(R.id.nav_profile);

        tabLayout.setVisibility(GONE);
    }
}
