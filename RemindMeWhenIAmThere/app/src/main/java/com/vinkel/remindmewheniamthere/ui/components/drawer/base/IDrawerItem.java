package com.vinkel.remindmewheniamthere.ui.components.drawer.base;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

import java.lang.reflect.Type;


public interface IDrawerItem {

    Context getContext();

    Type getDrawerItemType();

    DrawerItem withIdentifier(long identifier);

    DrawerItem withName(@StringRes int res);

    DrawerItem withIcon(@DrawableRes int res);

    long getIdentifier();

    String getName();

    Drawable getIcon();

}
