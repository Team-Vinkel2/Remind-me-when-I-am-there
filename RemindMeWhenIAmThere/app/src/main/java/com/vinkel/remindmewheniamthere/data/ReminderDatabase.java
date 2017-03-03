package com.vinkel.remindmewheniamthere.data;

import com.vinkel.remindmewheniamthere.config.di.annotations.ReminderModel;
import com.vinkel.remindmewheniamthere.data.base.IReminderDatabase;
import com.vinkel.remindmewheniamthere.models.base.IReminder;
import io.reactivex.Observable;
import java.util.List;
import javax.inject.Inject;


public class ReminderDatabase extends BaseDatabase<IReminder> implements IReminderDatabase {

  private static final String IS_ACTIVE_WHERE_CLAUSE="is_active = ?";
  private static final String IS_COMPLETED_WHERE_CLAUSE="is_completed = ?";

  @Inject
  public ReminderDatabase( @ReminderModel Class<? extends IReminder> classSingle) {
      super(classSingle);
  }

  @Override
  public Observable<List<IReminder>> getActiveReminders() {
    return this.find(
        IS_ACTIVE_WHERE_CLAUSE + AND_OPERATOR + IS_COMPLETED_WHERE_CLAUSE,
        BOOLEAN_TRUE,
        BOOLEAN_FALSE);
  }
}
