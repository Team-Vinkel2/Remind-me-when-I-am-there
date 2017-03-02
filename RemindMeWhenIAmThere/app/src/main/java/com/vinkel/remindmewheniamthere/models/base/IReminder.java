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

  float getLongitude();

  void setLongitude(float longitude);

  float getLatitude();

  void setLatitude(float latitude);

  String getLocationName();

  void setLocationName(String locationName);

  boolean getIsActive();

  void setIsActive(boolean isActive);

  boolean getIsCompleted();

  void setIsCompleted(boolean isCompleted);
}
