package com.vinkel.remindmewheniamthere.config.di.modules;

import com.vinkel.remindmewheniamthere.config.ApiConfig;
import com.vinkel.remindmewheniamthere.config.base.IApiConfig;
import com.vinkel.remindmewheniamthere.config.di.annotations.UserModel;
import com.vinkel.remindmewheniamthere.config.di.annotations.UserModelArray;
import com.vinkel.remindmewheniamthere.models.User;
import com.vinkel.remindmewheniamthere.network.UserData;
import com.vinkel.remindmewheniamthere.network.base.IUserData;
import com.vinkel.remindmewheniamthere.providers.HttpResponseFactory;
import com.vinkel.remindmewheniamthere.providers.base.IHttpResponseFactory;
import com.vinkel.remindmewheniamthere.utils.Requester;
import com.vinkel.remindmewheniamthere.utils.base.IJsonParser;
import com.vinkel.remindmewheniamthere.utils.base.IRequester;
import dagger.Module;
import dagger.Provides;
import javax.inject.Inject;

@Module
public class RemoteDataModule {

  private IUserData userData;
  private IRequester requester;
  private IHttpResponseFactory responseFactory;
  private IApiConfig apiConfig;

  @Inject
  @Provides
  IUserData provideUserData(
      IRequester requester,
      IApiConfig apiConfig,
      IJsonParser jsonParser,
      @UserModel Class<User> userModelType,
      @UserModelArray Class<User[]> userModelArrayType) {
    if (this.userData == null) {
      this.userData = new UserData(requester, apiConfig, jsonParser, userModelType, userModelArrayType);
    }

    return this.userData;
  }

  @Provides
  IHttpResponseFactory provideHttpResponseFactory() {
    if (this.responseFactory == null) {
      this.responseFactory = new HttpResponseFactory();
    }

    return this.responseFactory;
  }

  @Inject
  @Provides
  IRequester provideRequester(IHttpResponseFactory responseFactory) {
    if (this.requester == null) {
      this.requester = new Requester(responseFactory);
    }

    return this.requester;
  }

  @Provides
  IApiConfig provideApiConfig() {
    if (this.apiConfig == null) {
      this.apiConfig = new ApiConfig();
    }

    return this.apiConfig;
  }

}
