package com.example.android.bakingapp.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.android.bakingapp.IngredientFragment;
import com.example.android.bakingapp.StepsFragment;

public class FragPageAdapter extends FragmentPagerAdapter {
    public FragPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: // Fragment # 0 - This will show FirstFragment
                return new IngredientFragment();
            case 1: // Fragment # 0 - This will show FirstFragment different title
                return new StepsFragment();
            default:
                return null;
        }
    }
    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Ingredients";
            case 1:
                return "Steps";
            default:
                return null;
        }
    }

}
