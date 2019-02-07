package io.cuesoft.apparule.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import io.cuesoft.apparule.views.designer.AllCatalogueFragment;
import io.cuesoft.apparule.views.designer.AllFragment;
import io.cuesoft.apparule.views.designer.FabricFragment;
import io.cuesoft.apparule.views.designer.KidsAccesoriesFragment;
import io.cuesoft.apparule.views.designer.KidsFragment;
import io.cuesoft.apparule.views.designer.MenAccessoriesFragment;
import io.cuesoft.apparule.views.designer.MenFragment;
import io.cuesoft.apparule.views.designer.WeddingFragment;
import io.cuesoft.apparule.views.designer.WomenAccessoriesFragment;
import io.cuesoft.apparule.views.designer.WomenFragment;

public class DesignerCataloguePagerAdapter extends FragmentPagerAdapter {

    public DesignerCataloguePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch(position){
            case 0:
                return new AllCatalogueFragment();
            case 1:
                return new WomenFragment();
            case 2:
                return new MenFragment();
            case 3:
                return new KidsFragment();
            case 4:
                return new WomenAccessoriesFragment();
            case 5:
                return new MenAccessoriesFragment();
            case 6:
                return new KidsAccesoriesFragment();
            case 7:
                return new WeddingFragment();
            case 8:
                return new FabricFragment();
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return 9;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch(position){
            case 0:
                return "ALL";
            case 1:
                return "WOMEN";
            case 2:
                return "MEN";
            case 3:
                return "KIDS";
            case 4:
                return "WOMEN ACCESSORIES";
            case 5:
                return "MEN ACCESSORIES";
            case 6:
                return "KIDS ACCESSORIES";
            case 7:
                return "WEDDING";
            case 8:
                return "FABRICS";
            default:
                return null;
        }
    }
}
