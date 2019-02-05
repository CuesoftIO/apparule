package io.cuesoft.apparule.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import io.cuesoft.apparule.views.designer.AllFragment;
import io.cuesoft.apparule.views.designer.CompletedFragment;
import io.cuesoft.apparule.views.designer.OnGoingFragment;

public class DesignerFragmentPagerAdapter  extends FragmentPagerAdapter {

   public DesignerFragmentPagerAdapter(FragmentManager fm){
       super(fm);

   }
    @Override
    public Fragment getItem(int position) {
        // geItem is called to instantiate the fagment for the given page
        //Return a PlaceHolderFragment(defined as a static inner class below)
       switch (position){
           case 0:
               return new AllFragment();
           case 1:
               return new OnGoingFragment();
           case 2:
               return new CompletedFragment();
           default:
               return null;

       }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
       switch (position){
           case 0:
               return  "ALL";
           case 1:
               return  "ONGOING";
           case 2:
               return "COMPLETED";
       }
       return null;

   }
}
