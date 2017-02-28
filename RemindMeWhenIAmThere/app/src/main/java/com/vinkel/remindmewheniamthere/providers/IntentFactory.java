package com.vinkel.remindmewheniamthere.providers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.vinkel.remindmewheniamthere.providers.base.IIntentFactory;

public class IntentFactory implements IIntentFactory {
  private final Context context;

  public IntentFactory(Context context) {
    this.context = context;
  }


  @Override
  public Intent getIntent(Class<? extends Activity> activityClass) {
    return new Intent(context, activityClass);
  }

  @Override
  public Intent getIntentWithSetToFrontFlag(Class<? extends Activity> activityClass) {
    Intent intent = this.getIntent(activityClass);
    intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
    return intent;
  }
}
