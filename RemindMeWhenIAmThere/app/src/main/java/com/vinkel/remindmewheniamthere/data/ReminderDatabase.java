package com.vinkel.remindmewheniamthere.data;

import com.vinkel.remindmewheniamthere.config.di.annotations.ReminderModel;
import com.vinkel.remindmewheniamthere.data.base.IReminderDatabase;
import com.vinkel.remindmewheniamthere.models.base.IReminder;
import javax.inject.Inject;


public class ReminderDatabase extends BaseDatabase<IReminder> implements IReminderDatabase {

  @Inject
  public ReminderDatabase( @ReminderModel Class<? extends IReminder> classSingle) {
      super(classSingle);
  }

}
