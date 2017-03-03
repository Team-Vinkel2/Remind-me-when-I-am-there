package com.vinkel.remindmewheniamthere.config.di.modules;

import com.vinkel.remindmewheniamthere.config.di.annotations.ReminderModel;
import com.vinkel.remindmewheniamthere.data.ReminderDatabase;
import com.vinkel.remindmewheniamthere.data.base.IReminderDatabase;
import com.vinkel.remindmewheniamthere.models.Reminder;
import dagger.Module;
import dagger.Provides;
import javax.inject.Inject;

@Module
public class LocalDatabaseModule {

  private IReminderDatabase reminderDatabase;
  private Object reminderDatabaseLockObject;

  @Inject
  @Provides
  IReminderDatabase provideReminderDatabase(@ReminderModel Class<Reminder> classSingle) {
    if (this.reminderDatabase == null) {
      this.reminderDatabase = new ReminderDatabase(classSingle);
    }

    return this.reminderDatabase;
  }
}
