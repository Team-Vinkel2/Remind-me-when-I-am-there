package com.vinkel.remindmewheniamthere.config.di.modules;

import com.vinkel.remindmewheniamthere.config.di.annotations.ReminderModel;
import com.vinkel.remindmewheniamthere.config.di.annotations.UserModel;
import com.vinkel.remindmewheniamthere.config.di.annotations.UserModelArray;
import com.vinkel.remindmewheniamthere.models.Reminder;
import com.vinkel.remindmewheniamthere.models.User;
import dagger.Module;
import dagger.Provides;

@Module
public class ModelTypeModule {

  @Provides
  @ReminderModel
  Class<Reminder> provideReminderType() {
    return Reminder.class;
  }

  @Provides
  @UserModel
  Class<User> provideUserModelType() {
    return User.class;
  }

  @Provides
  @UserModelArray
  Class<User[]> provideUserModelArrayType() {
    return User[].class;
  }
}
