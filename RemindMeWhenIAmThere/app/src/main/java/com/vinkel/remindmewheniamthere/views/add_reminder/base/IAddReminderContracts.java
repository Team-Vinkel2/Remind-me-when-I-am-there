package com.vinkel.remindmewheniamthere.views.add_reminder.base;


import com.vinkel.remindmewheniamthere.views.base.IPresenter;
import com.vinkel.remindmewheniamthere.views.base.IView;

public interface IAddReminderContracts {

    interface View extends IView<Presenter> {

    }

    interface Presenter extends IPresenter<View> {

    }
}
