package com.vinkel.remindmewheniamthere.views.add_reminder.base;


import com.vinkel.remindmewheniamthere.views.base.IPresenter;
import com.vinkel.remindmewheniamthere.views.base.IView;

public interface IAddReminderContracts {

  interface View extends IView<Presenter> {
    void finish();
  }

  interface Presenter extends IPresenter<View> {
    void addLocalReminderForDate(String title, String content, int year, int month, int date, int hour, int minute);

    void addLocalReminderForLocation(String title, String content, double latitude, double longitude, String locationName);
  }
}
