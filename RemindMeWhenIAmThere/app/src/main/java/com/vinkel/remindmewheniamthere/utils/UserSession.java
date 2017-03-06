package com.vinkel.remindmewheniamthere.utils;


import android.content.SharedPreferences;

import com.vinkel.remindmewheniamthere.utils.base.IUserSession;

import javax.inject.Inject;

public class UserSession implements IUserSession {
  private static final String USERNAME_KEY = "username";
  private static final String AUTH_TOKEN_KEY = "authtoken";
  private static final String EMAIL_KEY = "email";
  private static final String FIRST_NAME_KEY = "username";
  private final SharedPreferences sharedPreferences;

  @Inject
  public UserSession(SharedPreferences sharedPreferences) {
    this.sharedPreferences = sharedPreferences;
  }

  @Override
  public String getUsername() {
    String username = sharedPreferences.getString(USERNAME_KEY, null);
    return username;
  }

  @Override
  public void setUsername(String username) {
    sharedPreferences.edit().putString(USERNAME_KEY, username).apply();
  }

  @Override
  public boolean isUserLoggedIn() {
    String username = getUsername();
    String authToken = getAuthToken();
    String firstName = getFirstName();
    String email = getEmail();

    return username != null && authToken != null  && firstName != null && email != null;
  }

  @Override
  public void clearSession() {
    setUsername(null);
    setEmail(null);
    setAuthToken(null);
    setFirstName(null);
  }

  @Override
  public String getAuthToken() {
    String authtoken = sharedPreferences.getString(AUTH_TOKEN_KEY, null);
    return authtoken;
  }

  @Override
  public void setAuthToken(String authToken) {
    sharedPreferences.edit().putString(AUTH_TOKEN_KEY, authToken).apply();
  }

  @Override
  public String getEmail() {
    String email = sharedPreferences.getString(EMAIL_KEY, null);
    return email;
  }

  @Override
  public void setEmail(String email) {
    sharedPreferences.edit().putString(EMAIL_KEY, email).apply();
  }

  @Override
  public String getFirstName() {
    String firstName = sharedPreferences.getString(FIRST_NAME_KEY, null);
    return firstName;
  }

  @Override
  public void setFirstName(String firstName) {
    sharedPreferences.edit().putString(FIRST_NAME_KEY, firstName).apply();
  }
}
