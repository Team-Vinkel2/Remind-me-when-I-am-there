package com.vinkel.remindmewheniamthere.background.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.vinkel.remindmewheniamthere.RMWITApplication;
import com.vinkel.remindmewheniamthere.background.services.LocationReminderWatcherService;
import com.vinkel.remindmewheniamthere.config.di.annotations.AppContext;
import com.vinkel.remindmewheniamthere.config.di.annotations.UnscopedIntentFactory;
import com.vinkel.remindmewheniamthere.config.di.modules.BackgroundModule;
import com.vinkel.remindmewheniamthere.data.base.IReminderDatabase;
import com.vinkel.remindmewheniamthere.models.base.IReminder;
import com.vinkel.remindmewheniamthere.providers.base.IIntentFactory;
import com.vinkel.remindmewheniamthere.utils.base.IDateTimeHelper;
import com.vinkel.remindmewheniamthere.utils.base.IReminderManager;
import com.vinkel.remindmewheniamthere.utils.base.IServiceHelper;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.Calendar;
import java.util.List;
import javax.inject.Inject;

public class BootReceiver extends BroadcastReceiver {
  private static final String BOOT_COMPLETED_ACTION_NAME = "android.intent.action.BOOT_COMPLETED";
  private static final String BOOT_COMPLETED_ACTION_HTC_1 = "android.intent.action.QUICKBOOT_POWERON";
  private static final String BOOT_COMPLETED_ACTION_HTC_2 = "com.htc.intent.action.QUICKBOOT_POWERON";

  private static final String TAG = "vinkel.BOOT";

  @Inject
  public IReminderManager reminderManager;

  @Inject
  public IReminderDatabase reminderDatabase;

  @Inject
  public IDateTimeHelper dateTimeHelper;

  @Inject
  @AppContext
  public Context appContext;

  @Inject
  @UnscopedIntentFactory
  IIntentFactory intentFactory;

  @Inject
  IServiceHelper serviceHelper;

  @Override
  public void onReceive(final Context context, Intent intent) {
    Log.e(TAG, "Intent received");
    String intentAction = intent.getAction();

    if (!(intentAction.equals(BOOT_COMPLETED_ACTION_NAME)
        ||
        intentAction.equals(BOOT_COMPLETED_ACTION_HTC_1)
        ||
        intentAction.equals(BOOT_COMPLETED_ACTION_HTC_2))) {
      return;
    }
    this.injectMembers();

    Log.e(TAG, "Boot started");

    reminderDatabase.getActiveReminders()
        .observeOn(Schedulers.io())
        .subscribe(new Consumer<List<IReminder>>() {
          @Override
          public void accept(List<IReminder> activeReminders) throws Exception {
            Log.e(TAG, activeReminders.size() + "");
            for (IReminder reminder : activeReminders) {
              Log.e(TAG, reminder.getTitle() + " " + reminder.getDateString());
              if (reminder.getDateString() == null) {
                Log.e(TAG, "Found location reminder");
                if (!serviceHelper.isServiceRunning(LocationReminderWatcherService.class)) {
                  Log.e(TAG, "Starting location reminder service");
                  Intent locationReminderWatcherServiceIntent =
                      intentFactory.getIntent(LocationReminderWatcherService.class);
                  appContext.startService(locationReminderWatcherServiceIntent);
                }
                continue;
              }

              Calendar reminderTime = dateTimeHelper.parseStringToCalendar(reminder.getDateString());

              if (reminder.getId() > Integer.MAX_VALUE) {
                continue;
              }
              reminderManager.setReminder(reminderTime, (int) (long) reminder.getId());
            }
          }
        });
  }

  private void injectMembers() {
    RMWITApplication
        .getInstance()
        .getComponent()
        .getBackgroundComponent(new BackgroundModule())
        .inject(this);
  }
}
