package com.vinkel.remindmewheniamthere.utils;

import com.vinkel.remindmewheniamthere.utils.base.IDateTimeHelper;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeHelper implements IDateTimeHelper {
  private static final String DATE_TIME_FORMAT_STRING = "dd/MM/yyyy HH:mm";
  private static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat(DATE_TIME_FORMAT_STRING);

  public Date parseStringToDate(String dateString) {
    try {
      return DATE_TIME_FORMAT.parse(dateString);
    } catch (ParseException ex) {
      return null;
    }
  }

  public String parseDateToString(Date date) {
    return DATE_TIME_FORMAT.format(date);
  }

  public Calendar parseStringToCalendar(String dateString) {
    Date date = this.parseStringToDate(dateString);

    if(date == null) {
      return null;
    }

    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);

    return calendar;
  }

  public String parseCalendarToString(Calendar calendar) {
    Date date = calendar.getTime();
    return this.parseDateToString(date);
  }
}
