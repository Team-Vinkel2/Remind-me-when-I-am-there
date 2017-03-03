package com.vinkel.remindmewheniamthere.ui.components.drawer;


import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.AbstractBadgeableDrawerItem;
import com.vinkel.remindmewheniamthere.ui.components.drawer.base.IDrawer;
import com.vinkel.remindmewheniamthere.ui.components.drawer.base.IDrawerItem;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

public class Drawer implements IDrawer {

    private DrawerBuilder drawerBuilder;

    @Inject
    public Drawer(Activity activity) {
        drawerBuilder = new DrawerBuilder(activity);
    }

    @Override
    public IDrawer withDrawerItems(@NonNull IDrawerItem... drawerItems) {
        com.mikepenz.materialdrawer.model.interfaces.IDrawerItem[] setDrawerItems = parseDrawerItems(Arrays.asList(drawerItems));
        drawerBuilder.addDrawerItems(setDrawerItems);
        return this;
    }

    @Override
    public IDrawer withSelectedItem(long identifier) {
        drawerBuilder.withSelectedItem(identifier);
        return this;
    }

    @Override
    public IDrawer withWidth(int width) {
        drawerBuilder.withDrawerWidthDp(width);
        return this;
    }

    @Override
    public IDrawer withToolbar(@NonNull Toolbar toolbar) {
        drawerBuilder.withToolbar(toolbar);
        return this;
    }

    @Override
    public IDrawer withOnDrawerItemClickListener(final OnDrawerItemClickListener onDrawerItemClickListener) {
        drawerBuilder.withOnDrawerItemClickListener(new com.mikepenz.materialdrawer.Drawer.OnDrawerItemClickListener() {
            @Override
            public boolean onItemClick(View view, int position, com.mikepenz.materialdrawer.model.interfaces.IDrawerItem drawerItem) {
                return  onDrawerItemClickListener.onClick(view, position);
            }
        });

        return this;
    }

    @Override
    public void build() {
        drawerBuilder.build();
    }

    private com.mikepenz.materialdrawer.model.interfaces.IDrawerItem[] parseDrawerItems(List<IDrawerItem> drawerItems) {
        List<com.mikepenz.materialdrawer.model.interfaces.IDrawerItem> drawerItemsList = new ArrayList<>();

        for (IDrawerItem drawerItem : drawerItems) {
            Type drawerItemType = drawerItem.getDrawerItemType();
            com.mikepenz.materialdrawer.model.interfaces.IDrawerItem materialDrawerItem;
            AbstractBadgeableDrawerItem mainDrawerItem;

            if (drawerItemType == PrimaryDrawerItem.class) {
                mainDrawerItem = new com.mikepenz.materialdrawer.model.PrimaryDrawerItem();
            }
            else if (drawerItemType == SecondaryDrawerItem.class){
                mainDrawerItem = new com.mikepenz.materialdrawer.model.SecondaryDrawerItem();
            }
            else if (drawerItemType == DividerDrawerItem.class) {
                materialDrawerItem = new com.mikepenz.materialdrawer.model.DividerDrawerItem()
                    .withIdentifier(drawerItem.getIdentifier());
                drawerItemsList.add(materialDrawerItem);
                continue;
            }
            else {
                throw new UnsupportedOperationException();
            }

            mainDrawerItem.withIdentifier(drawerItem.getIdentifier());
            mainDrawerItem.withName(drawerItem.getName());

            mainDrawerItem.withIcon(drawerItem.getIcon());

            drawerItemsList.add(mainDrawerItem);
        }

        return drawerItemsList.toArray(new com.mikepenz.materialdrawer.model.interfaces.IDrawerItem[drawerItems.size()]);
    }
}
