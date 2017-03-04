package com.vinkel.remindmewheniamthere.models;

import com.orm.dsl.Table;
import com.vinkel.remindmewheniamthere.models.base.IReminder;

@Table
public class Reminder implements IReminder {

  private Long id;
  private String remoteId;
  private String title;
  private String content;
  private String fromUser;
  private String toUser;
  private String dateString;
  private double longitude;
  private double latitude;
  private String locationName;
  private boolean isActive;
  private boolean isCompleted;

  public Reminder(){}

  public Reminder(
      String title,
      String content,
      String toUser,
      String dateString,
      double longitude,
      double latitude,
      String locationName,
      boolean isActive) {
    this.title = title;
    this.content = content;
    this.toUser = toUser;
    this.dateString = dateString;
    this.longitude = longitude;
    this.latitude = latitude;
    this.locationName = locationName;
    this.isActive = isActive;
  }

  @Override
  public Long getId() {
    return this.id;
  }

  @Override
  public void setId(Long id) {
    this.id = id;
  }

  @Override
  public String getRemoteId() {
    return this.remoteId;
  }

  @Override
  public void setRemoteId(String remoteId) {
    this.remoteId = remoteId;
  }

  @Override
  public String getTitle() {
    return this.title;
  }

  @Override
  public void setTitle(String title) {
    this.title = title;
  }

  @Override
  public String getContent() {
    return this.content;
  }

  @Override
  public void setContent(String content) {
    this.content = content;
  }

  @Override
  public String getFromUser() {
    return this.fromUser;
  }

  @Override
  public void setFromUser(String username) {
    this.fromUser = username;
  }

  @Override
  public String getToUser() {
    return this.toUser;
  }

  @Override
  public void setToUser(String username) {
    this.toUser = username;
  }

  @Override
  public String getDateString() {
    return this.dateString;
  }

  @Override
  public void setDateString(String dateString) {
    this.dateString = dateString;
  }

  @Override
  public double getLongitude() {
    return this.longitude;
  }

  @Override
  public void setLongitude(double longitude) {
    this.longitude = longitude;
  }

  @Override
  public double getLatitude() {
    return this.latitude;
  }

  @Override
  public void setLatitude(double latitude) {
    this.latitude = latitude;
  }

  @Override
  public String getLocationName() {
    return this.locationName;
  }

  @Override
  public void setLocationName(String locationName) {
    this.locationName = locationName;
  }

  @Override
  public boolean getIsActive() {
    return this.isActive;
  }

  @Override
  public void setIsActive(boolean isActive) {
    this.isActive = isActive;
  }

  @Override
  public boolean getIsCompleted() {
    return this.isCompleted;
  }

  @Override
  public void setIsCompleted(boolean isCompleted) {
    this.isCompleted = isCompleted;
  }
}
