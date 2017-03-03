package com.vinkel.remindmewheniamthere.ui.components.drawer;


import android.content.Context;

import com.vinkel.remindmewheniamthere.ui.components.drawer.base.IDrawerItem;
import com.vinkel.remindmewheniamthere.ui.components.drawer.base.IDrawerItemFactory;

import javax.inject.Inject;

public class DrawerItemFactory implements IDrawerItemFactory {

    private final Context context;

    @Inject
    public DrawerItemFactory(Context context) {
        this.context = context;
    }

    @Override
    public IDrawerItem createPrimaryDrawerItem() {
        return new PrimaryDrawerItem(context);
    }

    @Override
    public IDrawerItem createSecondaryDrawerItem() {
        return  new SecondaryDrawerItem(context);
    }

    @Override
    public IDrawerItem createDividerDrawerItem() {
        return  new DividerDrawerItem(context);
    }
}
