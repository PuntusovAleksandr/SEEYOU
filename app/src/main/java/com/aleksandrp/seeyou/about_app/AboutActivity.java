package com.aleksandrp.seeyou.about_app;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import com.aleksandrp.seeyou.R;
import com.aleksandrp.seeyou.about_app.fragment.AboutFragment;
import com.aleksandrp.seeyou.about_app.fragment.ConfidentialityFragment;
import com.aleksandrp.seeyou.about_app.fragment.ContentFragment;
import com.aleksandrp.seeyou.about_app.fragment.CooperationFragment;
import com.aleksandrp.seeyou.about_app.fragment.HelpFragment;
import com.aleksandrp.seeyou.about_app.fragment.TermsFragment;
import com.aleksandrp.seeyou.about_app.impl.AboutActivityPresentImpl;
import com.aleksandrp.seeyou.utils_app.STATICS_PARAMS;
import com.aleksandrp.seeyou.utils_app.UtilsApp;

public class AboutActivity extends AppCompatActivity implements View.OnClickListener,
        AboutActivityView, NavigationView.OnNavigationItemSelectedListener {

    private AboutActivityPresent mPresent;
    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;

    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        mFragmentManager = getFragmentManager();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mPresent = new AboutActivityPresentImpl();

        mPresent.showContactsFragment(this);

        findViewById(R.id.rl_back_about).setOnClickListener(this);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.openDrawer(GravityCompat.START);

    }

    @Override
    public void onClick(View mView) {
        UtilsApp.disableDoubleClick(mView);

        switch (mView.getId()) {
            case R.id.rl_back_about:
                finish();
                break;

        }
    }

    @Override
    public void onBackPressed() {

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    //  from AboutActivityView
//    ==================================================================
    @Override
    public void setContactsFragment() {
        setNextCFragment(new ContentFragment(), STATICS_PARAMS.CONTENT_FRAGMENT);
    }

    @Override
    public void setAboutFragment() {
        setNextCFragment(new AboutFragment(), STATICS_PARAMS.ABOUT_FRAGMENT);
    }

    @Override
    public void setCooperationFragment() {
        setNextCFragment(new CooperationFragment(), STATICS_PARAMS.COOPERATION_FRAGMENT);
    }

    @Override
    public void setCTermsFragment() {
        setNextCFragment(new TermsFragment(), STATICS_PARAMS.TERMS_FRAGMENT);
    }

    @Override
    public void setConfidentialityFragment() {
        setNextCFragment(new ConfidentialityFragment(), STATICS_PARAMS.CONFIDENTIALITY_FRAGMENT);
    }

    @Override
    public void setHelpFragment() {
        setNextCFragment(new HelpFragment(), STATICS_PARAMS.HELP_FRAGMENT);
    }


    //    ==================================================================

    public void setNextCFragment(Fragment fragment, String tag) {
        Fragment mFragment = mFragmentManager.findFragmentByTag(tag);
        if (mFragment == null) {
            mFragment = fragment;
            mFragmentTransaction = mFragmentManager.beginTransaction();
            mFragmentTransaction.replace(R.id.rl_container, mFragment, tag);
            mFragmentTransaction.commit();
        }
    }

    //  from NavigationView.OnNavigationItemSelectedListener
//    ==================================================================

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_contacts) {
            mPresent.showContactsFragment(this);
        } else if (id == R.id.nav_about) {
            mPresent.showAboutFragment(this);
        } else if (id == R.id.nav_cooperation) {
            mPresent.showCooperationFragment(this);
        } else if (id == R.id.nav_terms_of_use) {
            mPresent.showTermsFragment(this);
        } else if (id == R.id.nav_confidentiality) {
            mPresent.showConfidentialityFragment(this);
        } else if (id == R.id.nav_help) {
            mPresent.showHelpFragment(this);
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                drawer.closeDrawer(GravityCompat.START);
            }
        }, 150);
        return true;
    }

//    ==================================================================

}
