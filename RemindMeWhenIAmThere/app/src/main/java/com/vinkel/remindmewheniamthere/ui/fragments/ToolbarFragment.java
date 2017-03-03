package com.vinkel.remindmewheniamthere.ui.fragments;


import android.app.Fragment;
import android.support.annotation.LayoutRes;
import android.support.annotation.MenuRes;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import com.vinkel.remindmewheniamthere.ui.fragments.base.IToolbar;

public class ToolbarFragment extends Fragment implements IToolbar {
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
