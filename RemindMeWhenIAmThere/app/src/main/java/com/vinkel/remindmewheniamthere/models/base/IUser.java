package com.vinkel.remindmewheniamthere.models.base;

public interface IUser {
  String getId();

  void setId(String id);


  String getFirstName();


  void setFirstName(String firstName);


  String getUsername();


  void setUsername(String username);


  String getEmail();


  void setEmail(String email);


  String getPassword();


  void setPassword(String password);


  String getAuthtoken();

  String getEmailStatus();

}
