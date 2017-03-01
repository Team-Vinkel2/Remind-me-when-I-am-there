package com.vinkel.remindmewheniamthere.providers.base;

import android.app.Activity;
import android.content.Intent;

public interface IIntentFactory {
  Intent getIntent(Class<? extends Activity> activityClass);

  Intent getIntentWithSetToFrontFlag(Class<? extends Activity> activityClass);

  Intent getIntentWithNoAnimatedTransitionFlag(Class<? extends Activity> activityClass);
}
