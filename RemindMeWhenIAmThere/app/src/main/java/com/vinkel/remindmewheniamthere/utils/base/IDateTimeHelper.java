package com.vinkel.remindmewheniamthere.utils.base;

import java.util.Calendar;
import java.util.Date;

public interface IDateTimeHelper {
  Date parseStringToDate(String dateString);

  String parseDateToString(Date date);

  Calendar parseStringToCalendar(String dateString);

  String parseCalendarToString(Calendar calendar);
}
