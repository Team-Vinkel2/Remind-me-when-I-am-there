package com.vinkel.remindmewheniamthere.providers;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import com.vinkel.remindmewheniamthere.providers.base.IIntentFactory;

public class IntentFactory implements IIntentFactory {
  private final Context context;

  public IntentFactory(Context context) {
    this.context = context;
  }


  @Override
  public Intent getIntent(Class<?> activityClass) {
    return new Intent(context, activityClass);
  }

  @Override
  public Intent getIntentWithSetToFrontFlag(Class<?> activityClass) {
    Intent intent = this.getIntent(activityClass);
    intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
    return intent;
  }

  @Override
  public Intent getIntentWithNoAnimatedTransitionFlag(Class<?> activityClass) {
    Intent intent = this.getIntent(activityClass);
    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
    return intent;
  }

  @Override
  public PendingIntent getBroadcastPendingIntent(int id, Intent intent, int flags) {
    return PendingIntent.getBroadcast(this.context, id, intent, flags);
  }
}
