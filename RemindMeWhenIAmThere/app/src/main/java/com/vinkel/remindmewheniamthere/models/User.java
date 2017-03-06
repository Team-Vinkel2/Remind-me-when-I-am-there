package com.vinkel.remindmewheniamthere.models;

import com.google.gson.annotations.SerializedName;
import com.vinkel.remindmewheniamthere.models.base.IUser;

public class User implements IUser {
  @SerializedName("_id")
  private String id;
  @SerializedName("first_name")
  private String firstName;
  private String username;
  private String password;
  private String email;
  private String authtoken;
  @SerializedName("email_status")
  private String emailStatus;


  @Override
  public String getId() {
    return id;
  }

  @Override
  public void setId(String id) {
    this.id = id;
  }

  @Override
  public String getFirstName() {
    return firstName;
  }

  @Override
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public void setUsername(String username) {
    this.username = username;
  }

  @Override
  public String getEmail() {
    return email;
  }

  @Override
  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public String getAuthtoken() {
    return authtoken;
  }

  @Override
  public String getEmailStatus() {
      return emailStatus;
  }

}
