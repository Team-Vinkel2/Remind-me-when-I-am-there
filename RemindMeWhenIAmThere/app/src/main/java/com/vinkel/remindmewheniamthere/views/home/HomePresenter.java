package com.vinkel.remindmewheniamthere.views.home;

import com.vinkel.remindmewheniamthere.data.base.IReminderDatabase;
import com.vinkel.remindmewheniamthere.models.base.IReminder;
import com.vinkel.remindmewheniamthere.views.home.base.IHomeContracts;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class HomePresenter implements IHomeContracts.Presenter {

  private IHomeContracts.View view;


  private final IReminderDatabase reminderDatabase;

  @Inject
  public HomePresenter(IReminderDatabase reminderDatabase) {
    this.reminderDatabase = reminderDatabase;
  }

  @Override
  public void setView(IHomeContracts.View view) {
    this.view = view;
  }

  @Override
  public void start() {
    this.loadReminders();
  }


  @Override
  public void loadReminders() {
    this.view.setRefreshing(true);

    this.reminderDatabase
        .getActiveReminders()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<List<IReminder>>() {
          @Override
          public void accept(List<IReminder> activeReminders) throws Exception {
            view.setReminders(new ArrayList<>(activeReminders));
            view.setRefreshing(false);
          }
        });
  }
}
