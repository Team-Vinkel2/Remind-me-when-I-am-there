package com.vinkel.remindmewheniamthere.utils;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.SystemClock;
import com.vinkel.remindmewheniamthere.background.receivers.ReminderReceiver;
import com.vinkel.remindmewheniamthere.config.di.annotations.UnscopedIntentFactory;
import com.vinkel.remindmewheniamthere.providers.base.IIntentFactory;
import com.vinkel.remindmewheniamthere.utils.base.IReminderManager;
import java.util.Calendar;
import javax.inject.Inject;

public class ReminderManager implements IReminderManager {
  public static final String EXTRA_REMINDER_ID_KEY = "ALARM_ID";

  private final AlarmManager alarmManager;
  private final IIntentFactory intentFactory;

  @Inject
  public ReminderManager(AlarmManager alarmManager, @UnscopedIntentFactory IIntentFactory intentFactory) {
    this.alarmManager = alarmManager;
    this.intentFactory = intentFactory;
  }

  public void setReminder(Calendar time, int alarmId) {
    Intent intent = this.intentFactory.getIntent(ReminderReceiver.class);
    intent.putExtra(EXTRA_REMINDER_ID_KEY, (int)(long) alarmId);
    PendingIntent pendingIntent = this.intentFactory.getBroadcastPendingIntent((int)(long) alarmId, intent, PendingIntent.FLAG_CANCEL_CURRENT);

    Calendar currentCalendar = Calendar.getInstance();
    long currentTime = currentCalendar.getTimeInMillis();
    long deltaTime = time.getTimeInMillis() - currentTime;

    this.alarmManager.set(
        AlarmManager.ELAPSED_REALTIME,
        SystemClock.elapsedRealtime() + deltaTime,
        pendingIntent);

  }

  public void cancelReminder(int alarmId) {
    Intent intentToCancel = this.intentFactory.getIntent(ReminderReceiver.class);
    PendingIntent cancelIntent = intentFactory.getBroadcastPendingIntent(alarmId, intentToCancel, 0);

    this.alarmManager.cancel(cancelIntent);
  }
}
