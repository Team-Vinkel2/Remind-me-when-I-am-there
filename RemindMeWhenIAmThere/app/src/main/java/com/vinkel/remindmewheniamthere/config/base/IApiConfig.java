package com.vinkel.remindmewheniamthere.config.base;

public interface IApiConfig {

  int getApiErrorCode();

  String getBaseUrl();

  String getSignUpUrl();

  String getLoginUrl();

  String getPasswordResetUrl();

  String getSendBuddyRequestUrl();

  String getConfirmBuddyRequestUrl();

  String getCheckStatusBetweenUsersUrl();

  String getGetMyBuddiesUrl();

  String getGetMyBuddiesRequestsUrl();

  String getSearchUsersUrl(String partialName);

  String getCreateReminderForBuddyUrl();

  String getGetMyRemindersUrl();

  String getGetMyPendingRemindersUrl();

  String getGetReminderByIdUrl(String id);

  String getAcceptReminderUrl(String reminderId);

  String getApiErrorJsonKey();

  String getApiErrorMessageKey();

  String getAuthHeaderName();
}
