package com.aleksandrp.seeyou.about_app.fragment;

import com.aleksandrp.seeyou.R;

/**
 * Created by AleksandrP on 21.06.2016.
 */
public class ContentFragment extends BaseFragment {

    @Override
    public void setData() {
        tvTitle.setText(getResources().getString(R.string.contacts));
        tvDescription.setText(getResources().getString(R.string.description_contacts));
        super.setData();
    }
}
