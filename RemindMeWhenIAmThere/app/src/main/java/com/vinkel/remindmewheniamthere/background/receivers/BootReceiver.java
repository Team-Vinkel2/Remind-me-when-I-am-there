package com.vinkel.remindmewheniamthere.background.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.vinkel.remindmewheniamthere.data.base.IReminderDatabase;

public class BootReceiver extends BroadcastReceiver{
  private static final String BOOT_COMPLETED_ACTION_NAME="android.intent.action.BOOT_COMPLETED";

  public IReminderDatabase reminderDatabase;

  @Override
  public void onReceive(Context context, Intent intent) {
    if(!intent.getAction().equals(BOOT_COMPLETED_ACTION_NAME)) {
      return;
    }
  }
}
