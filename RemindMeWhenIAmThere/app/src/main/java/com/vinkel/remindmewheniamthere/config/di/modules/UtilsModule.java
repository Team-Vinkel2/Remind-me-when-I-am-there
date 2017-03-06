package com.vinkel.remindmewheniamthere.config.di.modules;

import android.app.ActivityManager;
import android.app.AlarmManager;
import android.content.Context;
import com.vinkel.remindmewheniamthere.config.di.annotations.AppContext;
import com.vinkel.remindmewheniamthere.config.di.annotations.UnscopedIntentFactory;
import com.vinkel.remindmewheniamthere.providers.base.IIntentFactory;
import com.vinkel.remindmewheniamthere.utils.DateTimeHelper;
import com.vinkel.remindmewheniamthere.utils.JsonParser;
import com.vinkel.remindmewheniamthere.utils.ReminderManager;
import com.vinkel.remindmewheniamthere.utils.ServiceHelper;
import com.vinkel.remindmewheniamthere.utils.UriUtils;
import com.vinkel.remindmewheniamthere.utils.base.IDateTimeHelper;
import com.vinkel.remindmewheniamthere.utils.base.IJsonParser;
import com.vinkel.remindmewheniamthere.utils.base.IReminderManager;
import com.vinkel.remindmewheniamthere.utils.base.IServiceHelper;
import com.vinkel.remindmewheniamthere.utils.base.IUriParser;
import dagger.Module;
import dagger.Provides;
import javax.inject.Inject;

@Module
public class UtilsModule {

  private IReminderManager reminderManager;
  private IServiceHelper serviceHelper;

  @Provides
  IUriParser provideUriParser() {
    return new UriUtils();
  }

  @Provides
  IReminderManager provideReminderManager(
      AlarmManager alarmManager,
      @UnscopedIntentFactory IIntentFactory intentFactory) {
    if (this.reminderManager == null) {
      this.reminderManager = new ReminderManager(alarmManager, intentFactory);
    }

    return this.reminderManager;
  }

  @Inject
  @Provides
  IServiceHelper provideServiceHelper(
      ActivityManager activityManager,
      @AppContext Context appContext,
      @UnscopedIntentFactory IIntentFactory intentFactory) {
    if (this.serviceHelper == null) {
      this.serviceHelper = new ServiceHelper(activityManager, appContext, intentFactory);
    }

    return this.serviceHelper;
  }

  @Provides
  IDateTimeHelper provideDateTimeHelper() {
    return new DateTimeHelper();
  }

  @Provides
  IJsonParser provideJsonParser() {
    return new JsonParser();
  }
}
