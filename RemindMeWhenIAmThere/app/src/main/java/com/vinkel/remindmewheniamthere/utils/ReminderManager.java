package com.vinkel.remindmewheniamthere.utils;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import com.vinkel.remindmewheniamthere.background.receivers.ReminderReceiver;
import com.vinkel.remindmewheniamthere.providers.base.IIntentFactory;
import com.vinkel.remindmewheniamthere.utils.base.IReminderManager;
import java.util.Calendar;

public class ReminderManager implements IReminderManager {
  public static final String EXTRA_REMINDER_ID_KEY = "ALARM_ID";

  private final Context context;
  private final AlarmManager alarmManager;
  private final IIntentFactory intentFactory;

  public ReminderManager(Context context, AlarmManager alarmManager, IIntentFactory intentFactory) {
    this.context = context;
    this.alarmManager = alarmManager;
    this.intentFactory = intentFactory;
  }

  public void setAlarm(Calendar time, int alarmId) {
    Intent intent = this.intentFactory.getIntent(ReminderReceiver.class);
    intent.putExtra(EXTRA_REMINDER_ID_KEY, alarmId);
    PendingIntent pendingIntent = this.intentFactory.getBroadcastPendingIntent(alarmId, intent, PendingIntent.FLAG_CANCEL_CURRENT);

    Calendar currentCalendar = Calendar.getInstance();
    long currentTime = currentCalendar.getTimeInMillis();
    long deltaTime = time.getTimeInMillis() - currentTime;

    this.alarmManager.set(
        AlarmManager.ELAPSED_REALTIME,
        SystemClock.elapsedRealtime() + deltaTime,
        pendingIntent);

  }

  public void cancelAlarm(int alarmId) {
    Intent intentToCancel = this.intentFactory.getIntent(ReminderReceiver.class);
    PendingIntent cancelIntent = intentFactory.getBroadcastPendingIntent(alarmId, intentToCancel, 0);

    this.alarmManager.cancel(cancelIntent);
  }
}
