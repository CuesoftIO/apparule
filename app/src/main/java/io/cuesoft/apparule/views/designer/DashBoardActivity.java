package io.cuesoft.apparule.views.designer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import io.cuesoft.apparule.R;

public class DashBoardActivity extends DesignerBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_dash_board);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //inflate your activity layout here

        //@SuppressLint("")
        View contentView = inflater.inflate(R.layout.activity_dash_board, null ,false);
        drawer.addView(contentView, 0);
        navigationView.setCheckedItem(R.id.nav_dashboard);


    }
}
