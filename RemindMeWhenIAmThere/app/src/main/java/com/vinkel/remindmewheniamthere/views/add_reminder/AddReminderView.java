package com.vinkel.remindmewheniamthere.views.add_reminder;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vinkel.remindmewheniamthere.R;
import com.vinkel.remindmewheniamthere.views.add_reminder.base.IAddReminderContracts;
import com.vinkel.remindmewheniamthere.views.base.IPresenter;

public class AddReminderView extends Fragment implements IAddReminderContracts.View {

    private IPresenter<IAddReminderContracts.View> presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_reminder, container, false);
        prepareView(view);
        return view;
    }

    @Override
    public void setPresenter(IAddReminderContracts.Presenter presenter) {

    }

    public void prepareView(View view) {

    }
}
