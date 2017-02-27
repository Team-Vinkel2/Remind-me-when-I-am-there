package com.vinkel.remindmewheniamthere.utils;

import android.content.SharedPreferences;
import android.net.Uri;
import com.vinkel.remindmewheniamthere.utils.base.IApplicationSettingsManager;
import com.vinkel.remindmewheniamthere.utils.base.IUriParser;
import java.util.Set;
import javax.inject.Inject;

public class ApplicationSettingsManager implements IApplicationSettingsManager {

  private static final String RINGTONE_URI_KEY_NAME = "ringtone_uri";
  private static final String ALARM_VOLUME_KEY_NAME = "alarm_volume";
  private static final String IS_FIRST_LAUNCH_KEY_NAME = "is_first_launch";
  private static final int DEFAULT_ALARM_VOLUME_SCALE_COEFFICIENT = 2;

  @SuppressWarnings("CheckStyle")
  private final int MAX_AUDIO_VOLUME;

  @SuppressWarnings("CheckStyle")
  public final Uri DEFAULT_RINGTONE_URI;

  public SharedPreferences sharedPreferences;
  private final IUriParser uriParser;

  @Inject
  public ApplicationSettingsManager(
      SharedPreferences sharedPreferences,
      Uri defaultRingtoneUri,
      IUriParser uriParser,
      int maxAudioVolume) {
    this.MAX_AUDIO_VOLUME = maxAudioVolume;
    this.DEFAULT_RINGTONE_URI = defaultRingtoneUri;
    this.uriParser = uriParser;
    this.sharedPreferences = sharedPreferences;
  }


  @Override
  public Uri getRingtoneUri() {
    String ringtoneUriString = this.sharedPreferences.getString(RINGTONE_URI_KEY_NAME, null);
    if (ringtoneUriString == null) {
      return this.DEFAULT_RINGTONE_URI;
    }
    return uriParser.parseUri(ringtoneUriString);
  }

  @Override
  public int getAlarmVolume() {
    return this.sharedPreferences.getInt(ALARM_VOLUME_KEY_NAME, MAX_AUDIO_VOLUME / DEFAULT_ALARM_VOLUME_SCALE_COEFFICIENT);
  }

  @Override
  public boolean getIsFirstLaunch() {
    return this.sharedPreferences.getBoolean(IS_FIRST_LAUNCH_KEY_NAME, true);
  }

  @Override
  public void setRingtoneUri(Uri ringtoneUri) {
    String ringtoneUriString = ringtoneUri.getPath();
    this.addItemToSharedPreferences(RINGTONE_URI_KEY_NAME, ringtoneUriString);
  }

  @Override
  public void setAlarmVolume(int volume) {
    this.addItemToSharedPreferences(ALARM_VOLUME_KEY_NAME, volume);
  }

  @Override
  public void setIsFirstLaunch(boolean isFirstTimeLaunched) {
    this.addItemToSharedPreferences(IS_FIRST_LAUNCH_KEY_NAME, isFirstTimeLaunched);
  }

  private void addItemToSharedPreferences(String key, Object value) {
    SharedPreferences.Editor editor = this.sharedPreferences.edit();
    if (value instanceof String) {
      editor.putString(key, (String) value);
    } else if (value instanceof Integer) {
      editor.putInt(key, (Integer)
          value);
    } else if (value instanceof Boolean) {
      editor.putBoolean(key, (Boolean) value);
    } else if (value instanceof Float) {
      editor.putFloat(key, (Float) value);
    } else if (value instanceof Long) {
      editor.putLong(key, (Long) value);
    }

    editor.apply();
  }

  private void addStringSetToSharedPreferences(String key, Set<String> set) {
    SharedPreferences.Editor editor = this.sharedPreferences.edit();
    editor.putStringSet(key, set);
    editor.apply();
  }
}
