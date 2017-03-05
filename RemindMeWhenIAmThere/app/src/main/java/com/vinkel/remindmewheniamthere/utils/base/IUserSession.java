package com.vinkel.remindmewheniamthere.utils.base;


public interface IUserSession {
    String getUsername();

    void setUsername(String username);

    boolean isUserLoggedIn();

    void clearSession();
}
