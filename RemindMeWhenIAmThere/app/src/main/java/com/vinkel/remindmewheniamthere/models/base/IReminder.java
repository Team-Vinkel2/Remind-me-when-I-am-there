package com.vinkel.remindmewheniamthere.models.base;

public interface IReminder {
  Long getId();

  void setId(Long id);

  String getRemoteId();

  void setRemoteId(String remoteId);

  String getTitle();

  void setTitle(String title);

  String getContent();

  void setContent(String content);

  String getFromUser();

  void setFromUser(String username);

  String getToUser();

  void setToUser(String username);

  String getDateString();

  void setDateString(String dateString);

  double getLongitude();

  void setLongitude(double longitude);

  double getLatitude();

  void setLatitude(double latitude);

  String getLocationName();

  void setLocationName(String locationName);

  boolean getIsActive();

  void setIsActive(boolean isActive);

  boolean getIsCompleted();

  void setIsCompleted(boolean isCompleted);
}
