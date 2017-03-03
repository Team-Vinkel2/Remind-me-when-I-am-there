package com.vinkel.remindmewheniamthere.ui.components.drawer.base;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

import java.lang.reflect.Type;

public abstract class DrawerItem implements IDrawerItem {

    private final Context context;
    private final Type drawerItemType;
    private long identifier;

    private int nameRes;
    private int iconRes;

    public DrawerItem(Context context) {
        this.context = context;
        this.drawerItemType = this.getClass();
        this.identifier = -1;
        this.nameRes = -1;
        this.iconRes = -1;
    }

    @Override
    public Context getContext() {
        return context;
    }

    @Override
    public Type getDrawerItemType() {
        return drawerItemType;
    }

    @Override
    public DrawerItem withIdentifier(long identifier) {
        this.identifier = identifier;
        return this;
    }

    @Override
    public DrawerItem withName(@StringRes int res) {
        this.nameRes = res;
        return this;
    }

    @Override
    public DrawerItem withIcon(@DrawableRes int res) {
        this.iconRes = res;
        return this;
    }

    @Override
    public long getIdentifier() {
        return identifier;
    }

    @Override
    public String getName() {
        return getContext().getString(nameRes);
    }

    @Override
    public Drawable getIcon() {
        return getContext().getDrawable(iconRes);
    }
}
