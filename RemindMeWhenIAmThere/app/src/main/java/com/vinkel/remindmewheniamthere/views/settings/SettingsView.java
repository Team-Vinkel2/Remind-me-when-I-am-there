package com.vinkel.remindmewheniamthere.views.settings;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vinkel.remindmewheniamthere.R;
import com.vinkel.remindmewheniamthere.views.settings.base.ISettingsContracts;

public class SettingsView extends Fragment implements ISettingsContracts.View {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        prepareView(view);
        return view;
    }

    @Override
    public void setPresenter(ISettingsContracts.Presenter presenter) {

    }

    public void prepareView(View view) {

    }
}
