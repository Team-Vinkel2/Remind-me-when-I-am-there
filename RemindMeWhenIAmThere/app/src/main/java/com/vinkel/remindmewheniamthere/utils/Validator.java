package com.vinkel.remindmewheniamthere.utils;


import com.vinkel.remindmewheniamthere.utils.base.IValidator;

public class Validator implements IValidator {

    private static final int MIN_USERNAME_LENGTH = 4;
    private static final int MAX_USERNAME_LENGTH = 16;
    private static final int MIN_PASSWORD_LENGTH = 4;
    private static final int MAX_PASSWORD_LENGTH = 16;


    @Override
    public boolean isUsernameValid(String username) {
        int usernameLength = username.length();
        if (usernameLength < MIN_USERNAME_LENGTH || usernameLength > MAX_USERNAME_LENGTH) {
            return false;
        }
        return true;
    }

    @Override
    public boolean isPasswordValid(String password) {
        int passwordLength = password.length();
        if (passwordLength < MIN_PASSWORD_LENGTH || passwordLength > MAX_PASSWORD_LENGTH) {
            return false;
        }
        return true;
    }
}
