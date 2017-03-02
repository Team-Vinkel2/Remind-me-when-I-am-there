package com.vinkel.remindmewheniamthere.providers.base;

import com.vinkel.remindmewheniamthere.models.base.IReminder;

public interface IReminderFactory {
  IReminder getLocalDateReminder(
      String title,
      String content,
      String dateString);

  IReminder getLocalLocationReminder(
      String title,
      String content,
      float longitude,
      float latitude,
      String locationName);

  IReminder getDateReminderForOtherUser(
      String title,
      String content,
      String dateString,
      String toUserUsername);

  IReminder getLocationReminderForOtherUser(
      String title,
      String content,
      float longitude,
      float latitude,
      String locationName,
      String toUserUsername);

  IReminder getEmptyReminder();

}
