package com.aleksandrp.seeyou.about_app.fragment;

import com.aleksandrp.seeyou.R;

/**
 * Created by AleksandrP on 21.06.2016.
 */
public class HelpFragment extends BaseFragment {



    @Override
    public void setData() {
        tvTitle.setText(getResources().getString(R.string.help));
        tvDescription.setText(getResources().getString(R.string.description_help));
        super.setData();
    }

}
