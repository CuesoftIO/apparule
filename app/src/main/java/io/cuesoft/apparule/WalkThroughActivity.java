package io.cuesoft.apparule;

import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class WalkThroughActivity extends AppCompatActivity {

    PreferenceManager preferenceManager;
    LinearLayout Layout_bars;
    int[] screens;
    TextView[] bototmBars;
    Button Skip,Next;
    MyViewPagerAdapter myvpAdapter;
    ViewPager vp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walk_through);

    }



    public class MyViewPagerAdapter extends PagerAdapter{
         private LayoutInflater inflater;

       public MyViewPagerAdapter(){

         }

       @Override
       public int getCount() {
          return 0;
      }

       @Override
      public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
          return false;
      }
 }

}
