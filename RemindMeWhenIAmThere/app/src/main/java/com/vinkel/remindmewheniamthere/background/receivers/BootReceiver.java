package com.vinkel.remindmewheniamthere.background.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.vinkel.remindmewheniamthere.RMWITApplication;
import com.vinkel.remindmewheniamthere.config.di.modules.BackgroundModule;
import com.vinkel.remindmewheniamthere.data.base.IReminderDatabase;
import com.vinkel.remindmewheniamthere.models.base.IReminder;
import com.vinkel.remindmewheniamthere.utils.base.IDateTimeHelper;
import com.vinkel.remindmewheniamthere.utils.base.IReminderManager;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.Calendar;
import java.util.List;
import javax.inject.Inject;

public class BootReceiver extends BroadcastReceiver{
  private static final String BOOT_COMPLETED_ACTION_NAME="android.intent.action.BOOT_COMPLETED";

  @Inject
  public IReminderManager reminderManager;

  @Inject
  public IReminderDatabase reminderDatabase;

  @Inject
  public IDateTimeHelper dateTimeHelper;

  @Override
  public void onReceive(final Context context, Intent intent) {

    if(!intent.getAction().equals(BOOT_COMPLETED_ACTION_NAME)) {
      return;
    }
    this.injectMembers();

    Log.e("KUR", Boolean.toString(reminderDatabase == null) + " " + reminderDatabase.toString());
    Log.e("KUR", Boolean.toString(reminderManager == null) + " " + reminderManager.toString());

    reminderDatabase.getActiveReminders()
        .observeOn(Schedulers.io())
        .subscribe(new Consumer<List<IReminder>>() {
          @Override
          public void accept(List<IReminder> activeReminders) throws Exception {
              for (IReminder reminder: activeReminders) {
                if(reminder.getDateString() == null) {
                  // Start location service
                  continue;
                }

                String message = String.format("%d, %s, %s, %s", reminder.getId(), reminder.getTitle(), reminder.getContent(), Boolean.toString(reminder.getIsActive()));
                Log.i("KUR", message);
                if (reminder.getIsActive()) {
                  continue;
                }

                Calendar reminderTime = dateTimeHelper.parseStringToCalendar(reminder.getDateString());

                if(reminder.getId() > Integer.MAX_VALUE){
                  continue;
                }
                reminderManager.setReminder(reminderTime, (int)(long) reminder.getId());
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
