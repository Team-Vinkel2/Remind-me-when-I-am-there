package com.vinkel.remindmewheniamthere.providers;


import com.vinkel.remindmewheniamthere.models.User;
import com.vinkel.remindmewheniamthere.models.base.IUser;
import com.vinkel.remindmewheniamthere.providers.base.IUserFactory;

public class UserFactory implements IUserFactory {

  @Override
  public IUser getSignUpUser(String username, String firstname, String password, String email) {
    IUser user = new User();
    user.setEmail(email);
    user.setFirstName(firstname);
    user.setPassword(password);
    user.setUsername(username);
    return user;
  }

  @Override
  public IUser getSignInUser(String username, String password) {
    IUser user = new User();
    user.setUsername(username);
    user.setPassword(password);
    return user;
  }
}
