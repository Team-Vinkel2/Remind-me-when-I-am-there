package com.vinkel.remindmewheniamthere.views.add_reminder;

import com.vinkel.remindmewheniamthere.background.services.LocationReminderWatcherService;
import com.vinkel.remindmewheniamthere.data.base.IReminderDatabase;
import com.vinkel.remindmewheniamthere.models.base.IReminder;
import com.vinkel.remindmewheniamthere.providers.base.IReminderFactory;
import com.vinkel.remindmewheniamthere.utils.base.IDateTimeHelper;
import com.vinkel.remindmewheniamthere.utils.base.IReminderManager;
import com.vinkel.remindmewheniamthere.utils.base.IServiceHelper;
import com.vinkel.remindmewheniamthere.views.add_reminder.base.IAddReminderContracts;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import java.util.Calendar;
import javax.inject.Inject;


public class AddReminderPresenter implements IAddReminderContracts.Presenter {

  private IAddReminderContracts.View view;

  private final IReminderManager reminderManager;
  private final IReminderFactory reminderFactory;
  private final IServiceHelper serviceHelper;
  private final IReminderDatabase reminderDatabase;
  private final IDateTimeHelper dateTimeHelper;

  @Inject
  public AddReminderPresenter(
      IReminderFactory reminderFactory,
      IReminderDatabase reminderDatabase,
      IReminderManager reminderManager,
      IServiceHelper serviceHelper,
      IDateTimeHelper dateTimeHelper) {

    this.reminderFactory = reminderFactory;
    this.reminderDatabase = reminderDatabase;
    this.reminderManager = reminderManager;
    this.serviceHelper = serviceHelper;
    this.dateTimeHelper = dateTimeHelper;

  }

  @Override
  public void setView(IAddReminderContracts.View view) {
    this.view = view;
  }

  @Override
  public void start() {

  }

  @Override
  public void addLocalReminderForDate(String title, String content, int year, int month, int date, int hour, int minute) {
    final Calendar calendar = Calendar.getInstance();
    calendar.set(year, month, date, hour, minute);

    IReminder reminder = reminderFactory.getLocalDateReminder(title, content, dateTimeHelper.parseCalendarToString(calendar));
    reminderDatabase.add(reminder)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<Long>() {
          @Override
          public void accept(Long id) throws Exception {
            if (id > Integer.MAX_VALUE) {
              return;
            }
            reminderManager.setReminder(calendar, (int)(long) id);
            view.finish();
          }
        });
  }

  @Override
  public void addLocalReminderForLocation(String title, String content, double latitude, double longitude, String locationName) {
    IReminder reminder = reminderFactory.getLocalLocationReminder(title, content, longitude, latitude, locationName);
    reminderDatabase.add(reminder)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<Long>() {
          @Override
          public void accept(Long id) throws Exception {
            if (id > Integer.MAX_VALUE) {
              return;
            }

            serviceHelper.checkStartService(LocationReminderWatcherService.class);
            view.finish();
          }
        });
  }
}
