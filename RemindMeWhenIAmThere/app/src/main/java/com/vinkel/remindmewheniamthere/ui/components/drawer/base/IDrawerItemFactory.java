package com.vinkel.remindmewheniamthere.ui.components.drawer.base;


public interface IDrawerItemFactory {

    IDrawerItem createPrimaryDrawerItem();

    IDrawerItem createSecondaryDrawerItem();

    IDrawerItem createDividerDrawerItem();
}
