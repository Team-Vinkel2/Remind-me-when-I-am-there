package com.vinkel.remindmewheniamthere.network.base;


import com.vinkel.remindmewheniamthere.models.base.IUser;

import io.reactivex.Observable;

public interface IUserData {

  Observable<IUser> signIn(IUser user);

  Observable<IUser> signUp(IUser user);

  Observable<String[]> getBuddies(String authToken);

  Observable<IUser[]> searchUsers(String authToken, String partialName);
}
