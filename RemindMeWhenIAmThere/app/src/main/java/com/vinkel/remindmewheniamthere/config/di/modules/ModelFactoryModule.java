package com.vinkel.remindmewheniamthere.config.di.modules;

import com.vinkel.remindmewheniamthere.providers.ReminderFactory;
import com.vinkel.remindmewheniamthere.providers.UserFactory;
import com.vinkel.remindmewheniamthere.providers.base.IReminderFactory;
import com.vinkel.remindmewheniamthere.providers.base.IUserFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class ModelFactoryModule {

  private IReminderFactory reminderFactory;
  private IUserFactory userFactory;

  @Provides
  IReminderFactory provideReminderFactory() {
    if (this.reminderFactory == null) {
      this.reminderFactory = new ReminderFactory();
    }

    return this.reminderFactory;
  }

  @Provides
  IUserFactory provideUserFactory() {
    if (this.userFactory == null) {
      this.userFactory = new UserFactory();
    }

    return this.userFactory;
  }

}
