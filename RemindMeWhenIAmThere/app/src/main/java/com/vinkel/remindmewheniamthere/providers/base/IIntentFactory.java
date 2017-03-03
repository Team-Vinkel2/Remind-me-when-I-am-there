package com.vinkel.remindmewheniamthere.providers.base;

import android.app.PendingIntent;
import android.content.Intent;

public interface IIntentFactory {
  Intent getIntent(Class<?> activityClass);

  Intent getIntentWithSetToFrontFlag(Class<?> activityClass);

  Intent getIntentWithNoAnimatedTransitionFlag(Class<?> activityClass);

  PendingIntent getBroadcastPendingIntent(int id, Intent intent, int flags);
}
