package com.vinkel.remindmewheniamthere.providers;

import com.vinkel.remindmewheniamthere.models.Reminder;
import com.vinkel.remindmewheniamthere.models.base.IReminder;
import com.vinkel.remindmewheniamthere.providers.base.IReminderFactory;

public class ReminderFactory implements IReminderFactory {
  @Override
  public IReminder getEmptyReminder() {
    return new Reminder();
  }

  @Override
  public IReminder getLocalDateReminder(
      String title,
      String content,
      String dateString) {

    return new Reminder(title, content, null, dateString, -1, -1, null, true);
  }

  @Override
  public IReminder getLocalLocationReminder(
      String title,
      String content,
      float longitude,
      float latitude,
      String locationName) {

    return new Reminder(
        title,
        content,
        null,
        null,
        longitude,
        latitude,
        locationName,
        true);
  }

  @Override
  public IReminder getDateReminderForOtherUser(
      String title,
      String content,
      String dateString,
      String toUserUsername) {

    return new Reminder(
        title,
        content,
        toUserUsername,
        dateString,
        -1,
        -1,
        null,
        false);
  }

  @Override
  public IReminder getLocationReminderForOtherUser(
      String title,
      String content,
      float longitude,
      float latitude,
      String locationName,
      String toUserUsername) {

    return new Reminder(
        title,
        content,
        toUserUsername,
        null,
        longitude,
        latitude,
        locationName,
        false);
  }



}
