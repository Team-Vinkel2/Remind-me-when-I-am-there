package com.vinkel.remindmewheniamthere.utils;


import android.content.SharedPreferences;

import com.vinkel.remindmewheniamthere.utils.base.IUserSession;

import javax.inject.Inject;

public class UserSession implements IUserSession {

    private final SharedPreferences sharedPreferences;

    @Inject
    public UserSession(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    @Override
    public String getUsername() {
        String username = sharedPreferences.getString("username", null);
        return username;
    }

    @Override
    public void setUsername(String username) {
        sharedPreferences.edit().putString("username", username).commit();
    }

    @Override
    public boolean isUserLoggedIn() {
        String username = getUsername();
        return username != null;
    }

    @Override
    public void clearSession() {
        setUsername(null);
    }
}
