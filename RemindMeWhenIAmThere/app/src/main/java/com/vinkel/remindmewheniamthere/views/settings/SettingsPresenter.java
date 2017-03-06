package com.vinkel.remindmewheniamthere.views.settings;


import android.net.Uri;
import com.vinkel.remindmewheniamthere.utils.base.IApplicationSettingsManager;
import com.vinkel.remindmewheniamthere.views.settings.base.ISettingsContracts;
import javax.inject.Inject;

public class SettingsPresenter implements ISettingsContracts.Presenter {

  private ISettingsContracts.View view;
  private IApplicationSettingsManager applicationSettingsManager;

  @Inject
  public SettingsPresenter(
      IApplicationSettingsManager applicationSettingsManager) {
    this.applicationSettingsManager = applicationSettingsManager;
  }

  @Override
  public void setView(ISettingsContracts.View view) {
    this.view = view;
  }

  @Override
  public void start() {
    this.view.setMaxVolume(this.applicationSettingsManager.getMaxAudioVolume());
    this.view.setCurrentVolume(this.applicationSettingsManager.getAlarmVolume());
    this.loadRingtoneTitle();
  }

  @Override
  public void saveAudioVolume(int volume) {
    applicationSettingsManager.setAlarmVolume(volume);
  }

  @Override
  public void saveRingtone(Uri ringtone) {
    this.applicationSettingsManager
        .setRingtoneUri(ringtone);
    this.loadRingtoneTitle();
  }

  public void loadRingtoneTitle() {
    String ringtoneName = this.applicationSettingsManager.getRingtoneTitle();
    this.view.setCurrentRingtone(ringtoneName);
  }
}
