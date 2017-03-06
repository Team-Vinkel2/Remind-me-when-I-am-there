package com.vinkel.remindmewheniamthere.config.di.modules;

import com.vinkel.remindmewheniamthere.network.base.IUserData;
import com.vinkel.remindmewheniamthere.providers.base.IReminderFactory;
import com.vinkel.remindmewheniamthere.providers.base.IUserFactory;
import com.vinkel.remindmewheniamthere.utils.base.IApplicationSettingsManager;
import com.vinkel.remindmewheniamthere.views.add_reminder.AddReminderPresenter;
import com.vinkel.remindmewheniamthere.views.add_reminder.base.IAddReminderContracts;
import com.vinkel.remindmewheniamthere.views.home.HomePresenter;
import com.vinkel.remindmewheniamthere.views.home.base.IHomeContracts;
import com.vinkel.remindmewheniamthere.views.settings.SettingsPresenter;
import com.vinkel.remindmewheniamthere.views.settings.base.ISettingsContracts;
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
      IApplicationSettingsManager applicationSettingsManager, IReminderFactory reminderFactory) {
    return new HomePresenter(applicationSettingsManager, reminderFactory);
  }

  @Inject
  @Provides
  ISignUpContracts.Presenter provideSignUpPresenter(IUserData userData, IUserFactory factory) {
    return new SignUpPresenter(userData, factory);
  }

  @Inject
  @Provides
  ISignInContracts.Presenter provideSignInPresenter(IUserData userData, IUserFactory factory) {
    return new SignInPresenter(userData, factory);
  }

  @Inject
  @Provides
  IAddReminderContracts.Presenter provideAddReminderPresenter() {
    return new AddReminderPresenter();
  }

  @Inject
  @Provides
  ISettingsContracts.Presenter provideSettingsPresenter() {
    return new SettingsPresenter();
  }
}
