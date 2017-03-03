package com.vinkel.remindmewheniamthere.config.di.modules;

import android.app.Activity;

import com.vinkel.remindmewheniamthere.ui.components.drawer.Drawer;
import com.vinkel.remindmewheniamthere.ui.components.drawer.DrawerItemFactory;
import com.vinkel.remindmewheniamthere.ui.components.drawer.base.IDrawer;
import com.vinkel.remindmewheniamthere.ui.components.drawer.base.IDrawerItemFactory;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewModule {
    @Inject
    @Provides
    IDrawer provideDrawer(Activity activity) {
        return new Drawer(activity);
    }

    @Inject
    @Provides
    IDrawerItemFactory provideDrawerItemFactory(Activity activity) {
        return  new DrawerItemFactory(activity);
    }
}
