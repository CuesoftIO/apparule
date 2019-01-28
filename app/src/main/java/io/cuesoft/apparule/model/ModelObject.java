package io.cuesoft.apparule.model;

import io.cuesoft.apparule.R;

public enum ModelObject {
    intro1(R.layout.intro_screen1),
    intro2(R.layout.intro_screen2),
    intro3(R.layout.intro_screen3);

    private int mLayoutResId;

    ModelObject(int layoutResID){
        mLayoutResId = layoutResID;
    }

    public int getmLayoutResId() {
        return mLayoutResId;
    }
}
