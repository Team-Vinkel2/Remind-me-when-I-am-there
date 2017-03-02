package com.vinkel.remindmewheniamthere.config.di.modules;

import com.vinkel.remindmewheniamthere.providers.ReminderFactory;
import com.vinkel.remindmewheniamthere.providers.base.IReminderFactory;
import dagger.Module;
import dagger.Provides;

@Module
public class ModelFactoryModule {

  private IReminderFactory reminderFactory;

  @Provides
  IReminderFactory provideReminderFactory() {
    if (this.reminderFactory == null) {
      this.reminderFactory = new ReminderFactory();
    }

    return this.reminderFactory;
  }

}
