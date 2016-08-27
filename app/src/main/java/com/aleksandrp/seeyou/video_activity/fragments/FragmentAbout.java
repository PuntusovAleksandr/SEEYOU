package com.aleksandrp.seeyou.video_activity.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aleksandrp.seeyou.R;

/**
 * Created by AleksandrP on 04.07.2016.
 */
public class FragmentAbout extends Fragment {

    private static FragmentAbout sAbout;

    public static FragmentAbout getInstance() {
        if (sAbout == null) {
            sAbout = new FragmentAbout();
        }
        return sAbout;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about, container, false);
        return view;
    }
}
