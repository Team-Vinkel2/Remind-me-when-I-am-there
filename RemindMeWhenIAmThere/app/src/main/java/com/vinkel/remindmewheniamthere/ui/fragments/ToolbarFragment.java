package com.vinkel.remindmewheniamthere.ui.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.MenuRes;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vinkel.remindmewheniamthere.R;
import com.vinkel.remindmewheniamthere.providers.base.IIntentFactory;
import com.vinkel.remindmewheniamthere.ui.components.drawer.base.IDrawer;
import com.vinkel.remindmewheniamthere.ui.components.drawer.base.IDrawerItemFactory;
import com.vinkel.remindmewheniamthere.ui.fragments.base.IToolbar;

import javax.inject.Inject;

public class ToolbarFragment extends Fragment implements IToolbar {

    private static final int NAV_HOME_ID = 0;
    private static final int SIGN_IN_ID = 1;
    private static final int SIGN_UP_ID = 2;
    private static final int SIGN_OUT_ID = 1;

    @Inject
    IDrawer navigationDrawer;
    @Inject
    IIntentFactory intentFactory;
    @Inject
    IDrawerItemFactory drawerItemFactory;

    private Toolbar toolbar;
    private ActionBar actionBar;
    private AppCompatActivity currentActivity;

    public ToolbarFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_toolbar, container, false);
    }

    @Override
    public void infalteMenu(@MenuRes int menuRes, Menu menu, MenuInflater menuInflater) {

    }

    @Override
    public void setNavigationOnClickListener(View.OnClickListener onClickListener) {

    }

    @Override
    public void setNavigationDrawer(@LayoutRes long selectedItem) {

    }
}
