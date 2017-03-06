package com.vinkel.remindmewheniamthere.network;


import com.vinkel.remindmewheniamthere.config.base.IApiConfig;
import com.vinkel.remindmewheniamthere.config.di.annotations.UserModel;
import com.vinkel.remindmewheniamthere.config.di.annotations.UserModelArray;
import com.vinkel.remindmewheniamthere.models.base.IUser;
import com.vinkel.remindmewheniamthere.models.json.ApiError;
import com.vinkel.remindmewheniamthere.network.base.BaseData;
import com.vinkel.remindmewheniamthere.network.base.IUserData;
import com.vinkel.remindmewheniamthere.providers.base.IHttpResponse;
import com.vinkel.remindmewheniamthere.utils.base.IJsonParser;
import com.vinkel.remindmewheniamthere.utils.base.IRequester;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.Callable;
import javax.inject.Inject;

public class UserData extends BaseData<IUser> implements IUserData {

  @Inject
  public UserData(IRequester httpRequester,
                  IApiConfig apiConfig,
                  IJsonParser jsonParser,
                  @UserModel Class<? extends IUser> classSingle,
                  @UserModelArray Class<? extends IUser[]> classArray) {
    super(httpRequester, apiConfig, jsonParser, classSingle, classArray);
  }

  @Override
  public Observable<IUser> signIn(final IUser user) {
    return Observable.defer(new Callable<ObservableSource<? extends IHttpResponse>>() {
      @Override
      public ObservableSource<? extends IHttpResponse> call() throws Exception {
        String userString = jsonParser.toJson(user);

        return httpRequester.postJson(apiConfig.getLoginUrl(), userString, null);
      }
    })
        .observeOn(Schedulers.io())
        .flatMap(new Function<IHttpResponse, ObservableSource<IUser>>() {
          @Override
          public ObservableSource<IUser> apply(IHttpResponse response) throws Exception {
            if (response.getCode() >= apiConfig.getApiErrorCode()) {
              throw new ApiError(parseApiError(response.getBody()));
            }

            IUser loggedInUser = jsonParser.fromJson(response.getBody(), classSingle);
            return Observable.just(loggedInUser);
          }
        });
  }

  @Override
  public Observable<IUser> signUp(final IUser user) {
    return Observable.defer(new Callable<ObservableSource<? extends IHttpResponse>>() {
      @Override
      public ObservableSource<? extends IHttpResponse> call() throws Exception {
        String userString = jsonParser.toJson(user);

        return httpRequester.postJson(apiConfig.getSignUpUrl(), userString, null);
      }
    })
        .observeOn(Schedulers.io())
        .flatMap(new Function<IHttpResponse, ObservableSource<IUser>>() {
          @Override
          public ObservableSource<IUser> apply(IHttpResponse response) throws Exception {
            if (response.getCode() >= apiConfig.getApiErrorCode()) {
              throw new ApiError(parseApiError(response.getBody()));
            }

            IUser registeredUser = jsonParser.fromJson(response.getBody(), classSingle);
            return Observable.just(registeredUser);
          }
        });
  }

  @Override
  public Observable<String[]> getBuddies(final String authToken) {
    return Observable.defer(new Callable<ObservableSource<? extends IHttpResponse>>() {
      @Override
      public ObservableSource<? extends IHttpResponse> call() throws Exception {

        Map<String, String> headers = new TreeMap<>();
        headers.put(apiConfig.getAuthHeaderName(), authToken);

        return httpRequester.getJson(apiConfig.getGetMyBuddiesUrl(), headers);
      }
    })
        .observeOn(Schedulers.io())
        .flatMap(new Function<IHttpResponse, ObservableSource<String[]>>() {
          @Override
          public ObservableSource<String[]> apply(IHttpResponse response) throws Exception {
            if (response.getCode() >= apiConfig.getApiErrorCode()) {
              throw new ApiError(parseApiError(response.getBody()));
            }

            String[] users = jsonParser.fromJson(response.getBody(), String[].class);
            return Observable.just(users);
          }
        });
  }

  @Override
  public Observable<IUser[]> searchUsers(final String authToken, final String partialName) {
    return Observable.defer(new Callable<ObservableSource<? extends IHttpResponse>>() {
      @Override
      public ObservableSource<? extends IHttpResponse> call() throws Exception {

        Map<String, String> headers = new TreeMap<>();
        headers.put(apiConfig.getAuthHeaderName(), authToken);

        return httpRequester.getJson(apiConfig.getSearchUsersUrl(partialName), headers);
      }
    })
        .observeOn(Schedulers.io())
        .flatMap(new Function<IHttpResponse, ObservableSource<IUser[]>>() {
          @Override
          public ObservableSource<IUser[]> apply(IHttpResponse response) throws Exception {
            if (response.getCode() >= apiConfig.getApiErrorCode()) {
              throw new ApiError(parseApiError(response.getBody()));
            }

            IUser[] buddies = jsonParser.fromJson(response.getBody(), classArray);
            return Observable.just(buddies);
          }
        });
  }
}
