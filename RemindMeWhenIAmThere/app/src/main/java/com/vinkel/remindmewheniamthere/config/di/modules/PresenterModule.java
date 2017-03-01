package com.vinkel.remindmewheniamthere.config.di.modules;

import com.vinkel.remindmewheniamthere.utils.base.IApplicationSettingsManager;
import com.vinkel.remindmewheniamthere.views.home.HomePresenter;
import com.vinkel.remindmewheniamthere.views.home.base.IHomeContracts;
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
}
