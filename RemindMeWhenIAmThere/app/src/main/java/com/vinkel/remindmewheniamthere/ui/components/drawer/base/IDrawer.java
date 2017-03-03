package com.vinkel.remindmewheniamthere.ui.components.drawer.base;


import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.View;

public interface IDrawer {

    IDrawer withDrawerItems(@NonNull IDrawerItem... drawerItems);

    IDrawer withSelectedItem(long identifier);

    IDrawer withWidth(int width);

    IDrawer withToolbar(@NonNull Toolbar toolbar);

    IDrawer withOnDrawerItemClickListener(OnDrawerItemClickListener onDrawerItemClickListener);

    void build();

    interface OnDrawerItemClickListener {

        boolean onClick(View view, int position);
    }
}
