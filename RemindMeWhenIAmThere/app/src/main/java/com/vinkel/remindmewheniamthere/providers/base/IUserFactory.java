package com.vinkel.remindmewheniamthere.providers.base;


import com.vinkel.remindmewheniamthere.models.base.IUser;

public interface IUserFactory {
  IUser getSignUpUser(String username, String firstname, String password, String email);

  IUser getSignInUser(String username, String password);
}
