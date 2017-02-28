package com.vinkel.remindmewheniamthere.config.di.modules;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.RingtoneManager;
import android.net.Uri;
import com.vinkel.remindmewheniamthere.config.di.annotations.AppContext;
import com.vinkel.remindmewheniamthere.utils.ApplicationSettingsManager;
import com.vinkel.remindmewheniamthere.utils.base.IApplicationSettingsManager;
import com.vinkel.remindmewheniamthere.utils.base.IUriParser;
import dagger.Module;
import dagger.Provides;
import javax.inject.Inject;
import javax.inject.Named;

@Module
public class AppModule {

  private final Application application;
  private IApplicationSettingsManager applicationSettingsManagerInstance;

  public AppModule(Application application) {

    this.application = application;
  }


  @Provides
  @AppContext
  Context providesApplicationContext() {
    return this.application;
  }

  @Provides
  SharedPreferences providePrivateSharedPreferences() {
    return this.application.getSharedPreferences(this.application.getPackageName(), Context.MODE_PRIVATE);
  }

  @Provides
  AudioManager provideAudioManager() {
    return (AudioManager) this.application.getSystemService(Context.AUDIO_SERVICE);
  }

  @Provides
  @Named("maxAudioVolume")
  int provideMaxAudioStreamVolume(AudioManager audioManager) {
    return audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
  }

  @Provides
  @Named("defaultRingtoneUri")
  Uri provideDefaultRingtoneUri() {
    return RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
  }

  @Inject
  @Provides
  public IApplicationSettingsManager provideApplicationSettingsManager(
      SharedPreferences sharedPreferences,
      @Named("defaultRingtoneUri") Uri defaultRingtoneUri,
      IUriParser uriParser,
      @Named("maxAudioVolume") int maxAudioVolume
  ) {
    if (this.applicationSettingsManagerInstance == null) {
      this.applicationSettingsManagerInstance = new ApplicationSettingsManager(sharedPreferences, defaultRingtoneUri, uriParser, maxAudioVolume);
    }
    return this.applicationSettingsManagerInstance;
  }
}
