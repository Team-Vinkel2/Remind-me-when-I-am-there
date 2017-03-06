package com.vinkel.remindmewheniamthere.views.settings;


import com.vinkel.remindmewheniamthere.utils.base.IApplicationSettingsManager;
import com.vinkel.remindmewheniamthere.views.add_reminder.base.IAddReminderContracts;
import com.vinkel.remindmewheniamthere.views.settings.base.ISettingsContracts;

import javax.inject.Inject;

public class SettingsPresenter implements ISettingsContracts.Presenter {

  private ISettingsContracts.View view;
  private IApplicationSettingsManager applicationSettingsManager;

  @Inject
  public SettingsPresenter(IApplicationSettingsManager applicationSettingsManager) {
    this.applicationSettingsManager = applicationSettingsManager;
  }

  @Override
  public void setView(ISettingsContracts.View view) {
    this.view = view;
  }

  @Override
  public void start() {

  }

  @Override
  public void saveAudioVolume(int volume) {
    applicationSettingsManager.setAlarmVolume(volume);
  }
}
