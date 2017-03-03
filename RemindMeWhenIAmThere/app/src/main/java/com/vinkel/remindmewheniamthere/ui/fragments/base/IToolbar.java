package com.vinkel.remindmewheniamthere.ui.fragments.base;


import android.support.annotation.LayoutRes;
import android.support.annotation.MenuRes;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

public interface IToolbar {

    void infalteMenu(@MenuRes int menuRes, Menu menu, MenuInflater menuInflater);

    void setNavigationOnClickListener();

    void setNavigationOnClickListener(View.OnClickListener onClickListener);

    void setNavigationDrawer(@LayoutRes long selectedItem);
}
