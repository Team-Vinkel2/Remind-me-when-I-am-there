package com.vinkel.remindmewheniamthere.data.base;

import com.vinkel.remindmewheniamthere.models.base.IReminder;
import io.reactivex.Observable;
import java.util.List;

public interface IReminderDatabase extends IBaseDatabase<IReminder> {
  Observable<List<IReminder>> getActiveReminders();

  Observable<List<IReminder>> getActiveLocationReminders();
}
