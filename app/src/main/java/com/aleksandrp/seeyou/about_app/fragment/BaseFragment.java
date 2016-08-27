package com.aleksandrp.seeyou.about_app.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aleksandrp.seeyou.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BaseFragment extends Fragment {

    public TextView tvTitle, tvDescription;

    public BaseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_base, container, false);
        tvTitle = (TextView) view.findViewById(R.id.title_base_fragment);
        tvDescription = (TextView) view.findViewById(R.id.tv_description_fragment);

        setData();

        return view;
    }

    public void setData() {

    }

}
