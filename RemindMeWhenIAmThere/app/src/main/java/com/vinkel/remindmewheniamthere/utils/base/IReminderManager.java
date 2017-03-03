package com.vinkel.remindmewheniamthere.utils.base;

import java.util.Calendar;

public interface IReminderManager {
  void setAlarm(Calendar time, int alarmId);

  void cancelAlarm(int alarmId);
}
