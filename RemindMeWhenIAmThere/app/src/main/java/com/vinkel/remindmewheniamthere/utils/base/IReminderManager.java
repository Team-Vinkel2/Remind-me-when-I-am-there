package com.vinkel.remindmewheniamthere.utils.base;

import java.util.Calendar;

public interface IReminderManager {
  void setReminder(Calendar time, int alarmId);

  void cancelReminder(int alarmId);
}
