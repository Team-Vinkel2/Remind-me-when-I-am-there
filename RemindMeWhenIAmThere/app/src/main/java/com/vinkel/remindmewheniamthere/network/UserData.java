package com.vinkel.remindmewheniamthere.network;


import com.vinkel.remindmewheniamthere.models.base.IUser;
import com.vinkel.remindmewheniamthere.network.base.IUserData;
import com.vinkel.remindmewheniamthere.providers.base.IHttpResponse;
import com.vinkel.remindmewheniamthere.utils.base.IJsonParser;
import com.vinkel.remindmewheniamthere.utils.base.IRequster;
import com.vinkel.remindmewheniamthere.utils.base.IUserSession;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class UserData implements IUserData {
    private static String SIGN_UP_URL = "http://localhost:3001/register";
    private final IRequster httpRequester;
    private final IJsonParser jsonParser;
    private final IUserSession userSession;
    private final Type userModelType;

    @Inject
    public  UserData(IRequster requster, IJsonParser jsonParser, IUserSession userSession, Type userModelType) {
        this.httpRequester = requster;
        this.jsonParser = jsonParser;
        this.userSession = userSession;
        this.userModelType = userModelType;
    }


    @Override
    public Observable<IUser> signIn(String username, String password) {
        return null;
    }

    @Override
    public Observable<IUser> signUp(String username, String password) {
        Map<String, String> userData = new HashMap<>();
        userData.put("username", username);
        userData.put("password", password);

        return httpRequester
                .post(SIGN_UP_URL, userData)
                .map(new Function<IHttpResponse, IUser>() {
                    @Override
                    public IUser apply(IHttpResponse iHttpResponse) throws Exception {
                        String responseBody = iHttpResponse.getBody().toString();
                        String userJson = jsonParser.getDirectMember(responseBody, "result");
                        IUser resultUser = jsonParser.fromJson(userJson, userModelType);

                        return resultUser;
                    }
                });
    }
}
