package com.vinkel.remindmewheniamthere.background.receivers;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;
import com.vinkel.remindmewheniamthere.RMWITApplication;
import com.vinkel.remindmewheniamthere.config.di.annotations.UnscopedIntentFactory;
import com.vinkel.remindmewheniamthere.config.di.modules.BackgroundModule;
import com.vinkel.remindmewheniamthere.providers.base.IIntentFactory;
import com.vinkel.remindmewheniamthere.utils.ReminderManager;
import com.vinkel.remindmewheniamthere.views.reminder_poup.ReminderPopupActivity;
import javax.inject.Inject;

public class ReminderReceiver extends WakefulBroadcastReceiver {

  private static final int DEFAULT_ALARM_ID = -1;

  @Inject
  @UnscopedIntentFactory
  IIntentFactory intentFactory;

  @Override
  public void onReceive(Context context, Intent intent) {
    this.injectMembers();
    int reminderID = intent.getIntExtra(ReminderManager.EXTRA_REMINDER_ID_KEY, DEFAULT_ALARM_ID);

    if (reminderID == DEFAULT_ALARM_ID) {
      return;
    }

    Intent reminderIntent = intentFactory.getIntent(ReminderPopupActivity.class);
    reminderIntent.putExtra(ReminderManager.EXTRA_REMINDER_ID_KEY, reminderID);
    reminderIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
    context.startActivity(reminderIntent);
  }

  private void injectMembers() {
    RMWITApplication
        .getInstance()
        .getComponent()
        .getBackgroundComponent(new BackgroundModule())
        .inject(this);
  }
}
