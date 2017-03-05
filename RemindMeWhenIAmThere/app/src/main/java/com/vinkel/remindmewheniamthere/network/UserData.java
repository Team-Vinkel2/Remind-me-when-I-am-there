package com.vinkel.remindmewheniamthere.network;


import com.vinkel.remindmewheniamthere.models.base.IUser;
import com.vinkel.remindmewheniamthere.network.base.IUserData;
import com.vinkel.remindmewheniamthere.utils.base.IRequster;

import io.reactivex.Observable;

public class UserData implements IUserData {
    private final IRequster httpRequester;


    @Override
    public Observable<IUser> signIn(String username, String password) {
        return null;
    }

    @Override
    public Observable<IUser> signUp(String username, String password) {
        return null;
    }
}
