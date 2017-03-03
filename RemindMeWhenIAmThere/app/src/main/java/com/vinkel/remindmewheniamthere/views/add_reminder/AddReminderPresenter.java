package com.vinkel.remindmewheniamthere.views.add_reminder;

import com.vinkel.remindmewheniamthere.views.add_reminder.base.IAddReminderContracts;

import javax.inject.Inject;


public class AddReminderPresenter implements IAddReminderContracts.Presenter {

    private  IAddReminderContracts.View view;

    @Inject
    public AddReminderPresenter() {

    }

    @Override
    public void setView(IAddReminderContracts.View view) {
        this.view = view;
    }

    @Override
    public void start() {

    }
}
