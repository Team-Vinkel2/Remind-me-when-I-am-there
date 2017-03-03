package com.vinkel.remindmewheniamthere.config.di.modules;

import com.vinkel.remindmewheniamthere.utils.base.IApplicationSettingsManager;
import com.vinkel.remindmewheniamthere.views.home.HomePresenter;
import com.vinkel.remindmewheniamthere.views.home.base.IHomeContracts;
import com.vinkel.remindmewheniamthere.views.sign_in.SignInPresenter;
import com.vinkel.remindmewheniamthere.views.sign_in.base.ISignInContracts;
import com.vinkel.remindmewheniamthere.views.sign_up.SignUpPresenter;
import com.vinkel.remindmewheniamthere.views.sign_up.base.ISignUpContracts;

import dagger.Module;
import dagger.Provides;
import javax.inject.Inject;

@Module
public class PresenterModule {

  @Inject
  @Provides
  IHomeContracts.Presenter provideHomePresenter(
      IApplicationSettingsManager applicationSettingsManager) {
    return new HomePresenter(applicationSettingsManager);
  }

  @Inject
  @Provides
  ISignUpContracts.Presenter provideSignUpPresenter (){
    return new SignUpPresenter();
  }

  @Inject
  @Provides
  ISignInContracts.Presenter provideSignInPresenter (){
    return new SignInPresenter();
  }
}
