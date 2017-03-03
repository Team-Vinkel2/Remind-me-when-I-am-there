package com.vinkel.remindmewheniamthere.config.di.modules;

import android.app.AlarmManager;
import com.vinkel.remindmewheniamthere.config.di.annotations.UnscopedIntentFactory;
import com.vinkel.remindmewheniamthere.providers.base.IIntentFactory;
import com.vinkel.remindmewheniamthere.utils.DateTimeHelper;
import com.vinkel.remindmewheniamthere.utils.ReminderManager;
import com.vinkel.remindmewheniamthere.utils.UriUtils;
import com.vinkel.remindmewheniamthere.utils.base.IDateTimeHelper;
import com.vinkel.remindmewheniamthere.utils.base.IReminderManager;
import com.vinkel.remindmewheniamthere.utils.base.IUriParser;
import dagger.Module;
import dagger.Provides;

@Module
public class UtilsModule {

  private IReminderManager reminderManager;

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

  @Provides
  IDateTimeHelper provideDateTimeHelper() {
    return new DateTimeHelper();
  }
}
