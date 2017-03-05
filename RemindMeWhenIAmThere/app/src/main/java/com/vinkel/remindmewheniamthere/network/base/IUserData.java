package com.vinkel.remindmewheniamthere.network.base;


import com.vinkel.remindmewheniamthere.models.base.IUser;

import io.reactivex.Observable;

public interface IUserData {

    Observable<IUser> signIn(String username, String password);

    Observable<IUser> signUp(String username, String password);
}
