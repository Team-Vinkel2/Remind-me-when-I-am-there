package com.vinkel.remindmewheniamthere.config;

import com.vinkel.remindmewheniamthere.config.base.IApiConfig;

public class ApiConfig implements IApiConfig {
  @Override
  public int getApiErrorCode() {
    return 400;
  }

  @Override
  public String getBaseUrl() {
    return "https://remindmewheniamthere.herokuapp.com/";
  }

  @Override
  public String getSignUpUrl() {
    return this.getBaseUrl() + "register";
  }

  @Override
  public String getLoginUrl() {
    return this.getBaseUrl() + "login";
  }

  @Override
  public String getPasswordResetUrl() {
    return this.getBaseUrl() + "reset-password";
  }

  @Override
  public String getSendBuddyRequestUrl() {
    return this.getBaseUrl() + "send-buddy-request";
  }

  @Override
  public String getConfirmBuddyRequestUrl() {
    return this.getBaseUrl() + "confirm-buddy-request";
  }

  @Override
  public String getCheckStatusBetweenUsersUrl() {
    return this.getBaseUrl() + "check-status-between-users";
  }

  @Override
  public String getGetMyBuddiesUrl() {
    return this.getBaseUrl() + "my-buddies";
  }

  @Override
  public String getGetMyBuddiesRequestsUrl() {
    return this.getBaseUrl() + "my-buddy-requests";
  }

  @Override
  public String getSearchUsersUrl(String partialName) {
    return this.getBaseUrl() + "search-users/" + partialName;
  }

  @Override
  public String getCreateReminderForBuddyUrl() {
    return this.getBaseUrl() + "create-reminder-for-buddy";
  }

  @Override
  public String getGetMyRemindersUrl() {
    return this.getBaseUrl() + "my-reminders";
  }

  @Override
  public String getGetMyPendingRemindersUrl() {
    return this.getBaseUrl() + "my-pending-reminders";
  }

  @Override
  public String getGetReminderByIdUrl(String id) {
    return this.getBaseUrl() + "get-reminder/" + id;
  }

  @Override
  public String getAcceptReminderUrl(String reminderId) {
    return this.getBaseUrl() + "accept-reminder/" + reminderId;
  }

  @Override
  public String getApiErrorJsonKey() {
    return "error";
  }

  @Override
  public String getApiErrorMessageKey() {
    return "message";
  }

  @Override
  public String getAuthHeaderName() {
    return "auth-token";
  }
}
