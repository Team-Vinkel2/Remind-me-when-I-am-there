package com.vinkel.remindmewheniamthere.utils.base;


public interface IUserSession {
  String getUsername();

  void setUsername(String username);

  boolean isUserLoggedIn();

  void clearSession();

  String getAuthToken();

  void setAuthToken(String authToken);

  String getEmail();

  void setEmail(String email);

  String getFirstName();

  void setFirstName(String firstName);
}
