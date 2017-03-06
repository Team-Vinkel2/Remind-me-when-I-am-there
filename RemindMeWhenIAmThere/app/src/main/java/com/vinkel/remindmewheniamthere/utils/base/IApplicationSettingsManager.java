package com.vinkel.remindmewheniamthere.utils.base;

import android.media.Ringtone;
import android.net.Uri;

public interface IApplicationSettingsManager {
  Uri getRingtoneUri();

  int getAlarmVolume();

  boolean getIsFirstLaunch();

  void setRingtoneUri(Uri ringtoneUri);

  void setAlarmVolume(int volume);

  void setIsFirstLaunch(boolean isFirstTimeLaunched);

  int getMaxAudioVolume();

  Ringtone getRingtone();

  String getRingtoneTitle();
}
