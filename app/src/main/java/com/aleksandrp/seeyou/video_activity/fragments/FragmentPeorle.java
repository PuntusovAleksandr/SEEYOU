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
public class FragmentPeorle  extends Fragment {

    private static FragmentPeorle sPeorle;

    public static FragmentPeorle getInstance() {
        if (sPeorle == null) {
            sPeorle = new FragmentPeorle();
        }
        return sPeorle;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_people, container, false);
        return view;
    }
}
