package com.vinkel.remindmewheniamthere.views.home.base;

import com.vinkel.remindmewheniamthere.models.base.IReminder;
import com.vinkel.remindmewheniamthere.views.base.IPresenter;
import com.vinkel.remindmewheniamthere.views.base.IView;

import java.util.ArrayList;
import java.util.List;

public interface IHomeContracts {

  interface View extends IView<Presenter> {
    void setTitle(String title);

    void setReminders(ArrayList<IReminder> reminders);
  }

  interface Presenter extends IPresenter<View> {}
}
