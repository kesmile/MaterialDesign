package com.test.kesmile.materialdesign.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.test.kesmile.materialdesign.R;

import java.util.List;

/**
 * Created by root on 12/01/15.
 */
public class PagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    public PagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }
    @Override
    public Fragment getItem(int position) {

        return this.fragments.get(position);
    }

    @Override
    public int getCount() {
        return this.fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String titulo = null;
        switch (position) {
            case 0:
                titulo = "Home";
                break;
            case 1:
                titulo = "Historial";
                break;
            /*case 2:
                titulo = "Historial";
                break;*/
        }
        return titulo;
    }
    public String getFragmentTag(int pos){
        return "android:switcher:"+ R.id.pager +":"+pos;
    }

}
