package com.vinkel.remindmewheniamthere.views.home.base;

import com.vinkel.remindmewheniamthere.models.base.IReminder;
import com.vinkel.remindmewheniamthere.views.base.IPresenter;
import com.vinkel.remindmewheniamthere.views.base.IView;
import java.util.ArrayList;

public interface IHomeContracts {

  interface View extends IView<Presenter> {
    void setReminders(ArrayList<IReminder> reminders);

    void setRefreshing(boolean value);
  }

  interface Presenter extends IPresenter<View> {
    void loadReminders();
  }
}
