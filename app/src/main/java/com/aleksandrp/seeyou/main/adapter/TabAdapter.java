package com.aleksandrp.seeyou.main.adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentStatePagerAdapter;

import com.aleksandrp.seeyou.main.fragment.AdultOnlyFragment;
import com.aleksandrp.seeyou.main.fragment.BroadcastsFragment;
import com.aleksandrp.seeyou.main.fragment.VideoSelfieFragment;

/**
 * Created by AleksandrP on 17.06.2016.
 */
public class TabAdapter extends FragmentStatePagerAdapter {

    private int numberOfTabs;
    private int position;

    public TabAdapter(FragmentManager fm, int numberOfTabst) {
        super(fm);
        this.numberOfTabs = numberOfTabst;
        position = 0;
    }


    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                this.position = 0;
                return VideoSelfieFragment.gewInstance();
            case 1:
                this.position = 1;
                return BroadcastsFragment.gewInstance();
            case 2:
                this.position = 2;
                return AdultOnlyFragment.gewInstance();
            default:
                this.position = 0;
                return null;
        }
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }

    public int getNumberOfTabs() {
        return position;
    }
}
