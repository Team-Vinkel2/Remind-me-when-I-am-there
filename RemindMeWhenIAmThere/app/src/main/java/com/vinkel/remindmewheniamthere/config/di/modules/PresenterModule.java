package com.vinkel.remindmewheniamthere.config.di.modules;

import com.vinkel.remindmewheniamthere.data.base.IReminderDatabase;
import com.vinkel.remindmewheniamthere.providers.base.IReminderFactory;
import com.vinkel.remindmewheniamthere.utils.base.IApplicationSettingsManager;
import com.vinkel.remindmewheniamthere.utils.base.IDateTimeHelper;
import com.vinkel.remindmewheniamthere.utils.base.IReminderManager;
import com.vinkel.remindmewheniamthere.utils.base.IServiceHelper;
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
  ISignUpContracts.Presenter provideSignUpPresenter() {
    return new SignUpPresenter();
  }

  @Inject
  @Provides
  ISignInContracts.Presenter provideSignInPresenter() {
    return new SignInPresenter();
  }

  @Inject
  @Provides
  IAddReminderContracts.Presenter provideAddReminderPresenter(
      IReminderFactory reminderFactory,
      IReminderDatabase reminderDatabase,
      IReminderManager reminderManager,
      IServiceHelper serviceHelper,
      IDateTimeHelper dateTimeHelper) {
    return new AddReminderPresenter(
        reminderFactory,
        reminderDatabase,
        reminderManager,
        serviceHelper,
        dateTimeHelper);
  }

  @Inject
  @Provides
  ISettingsContracts.Presenter provideSettingsPresenter() {
    return new SettingsPresenter();
  }
}
