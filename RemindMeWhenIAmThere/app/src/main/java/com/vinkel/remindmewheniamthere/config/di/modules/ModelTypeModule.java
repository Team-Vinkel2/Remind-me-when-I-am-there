package com.vinkel.remindmewheniamthere.config.di.modules;

import com.vinkel.remindmewheniamthere.config.di.annotations.ReminderModel;
import com.vinkel.remindmewheniamthere.models.Reminder;
import dagger.Module;
import dagger.Provides;

@Module
public class ModelTypeModule {

  @Provides
  @ReminderModel
  Class<Reminder> provideReminderType() {
    return Reminder.class;
  }
}
