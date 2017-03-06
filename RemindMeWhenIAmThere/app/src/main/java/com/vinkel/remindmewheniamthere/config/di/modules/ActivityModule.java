package com.vinkel.remindmewheniamthere.config.di.modules;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import com.vinkel.remindmewheniamthere.config.di.annotations.ActivityContext;
import com.vinkel.remindmewheniamthere.config.di.annotations.AppContext;
import com.vinkel.remindmewheniamthere.config.di.annotations.IntentFactoryForActivity;
import com.vinkel.remindmewheniamthere.providers.IntentFactory;
import com.vinkel.remindmewheniamthere.providers.base.IIntentFactory;
import com.vinkel.remindmewheniamthere.utils.Notificator;
import com.vinkel.remindmewheniamthere.utils.base.INotificator;

import dagger.Module;
import dagger.Provides;
import javax.inject.Inject;

@Module
public class ActivityModule {
  private final Activity activity;
  private FragmentManager fragmentManager;
  private IIntentFactory intentFactory;
  private INotificator notificator;

  public ActivityModule(Activity activity) {
    this.activity = activity;
  }

  public ActivityModule(Activity activity, FragmentManager fragmentManager) {
    this.activity = activity;
    this.fragmentManager = fragmentManager;
  }

  @Provides
  Activity provideActivity() {
    return this.activity;
  }

  @Provides
  @ActivityContext
  Context provideActivityContext() {
    return this.activity;
  }

  @Provides
  FragmentManager provideFragmentManager() {
    return this.fragmentManager;
  }

  @Inject
  @Provides
  @IntentFactoryForActivity
  IIntentFactory provideIntentFactory(@ActivityContext Context context) {
    if (this.intentFactory == null) {
      this.intentFactory = new IntentFactory(context);
    }

    return this.intentFactory;
  }

  @Inject
  @Provides
  INotificator provideNotificator(@ActivityContext Context context) {
    if (this.notificator == null) {
      this.notificator = new Notificator(context);
    }

    return this.notificator;
  }
}
