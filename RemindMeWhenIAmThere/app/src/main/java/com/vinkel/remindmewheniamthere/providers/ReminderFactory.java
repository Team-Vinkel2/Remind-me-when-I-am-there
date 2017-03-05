package com.vinkel.remindmewheniamthere.providers;

import com.vinkel.remindmewheniamthere.models.Reminder;
import com.vinkel.remindmewheniamthere.models.base.IReminder;
import com.vinkel.remindmewheniamthere.providers.base.IReminderFactory;

public class ReminderFactory implements IReminderFactory {
  private static final double DEFAULT_COORDS_VALUE = Double.MAX_VALUE - 100;

  @Override
  public IReminder getEmptyReminder() {
    return new Reminder();
  }

  @Override
  public IReminder getLocalDateReminder(
      String title,
      String content,
      String dateString) {

    return new Reminder(
        title,
        content,
        null,
        dateString,
        DEFAULT_COORDS_VALUE,
        DEFAULT_COORDS_VALUE,
        null,
        true);
  }

  @Override
  public IReminder getLocalLocationReminder(
      String title,
      String content,
      double longitude,
      double latitude,
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
        DEFAULT_COORDS_VALUE,
        DEFAULT_COORDS_VALUE,
        null,
        false);
  }

  @Override
  public IReminder getLocationReminderForOtherUser(
      String title,
      String content,
      double longitude,
      double latitude,
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
