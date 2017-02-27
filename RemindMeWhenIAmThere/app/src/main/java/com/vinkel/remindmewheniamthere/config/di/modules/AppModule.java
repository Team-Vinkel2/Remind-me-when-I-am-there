package com.vinkel.remindmewheniamthere.config.di.modules;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.RingtoneManager;
import android.net.Uri;
import com.vinkel.remindmewheniamthere.config.di.annotations.AppScope;
import com.vinkel.remindmewheniamthere.utils.ApplicationSettingsManager;
import com.vinkel.remindmewheniamthere.utils.base.IApplicationSettingsManager;
import com.vinkel.remindmewheniamthere.utils.base.IUriParser;
import dagger.Module;
import dagger.Provides;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

@Module
public class AppModule {

  private final Application application;

  public AppModule(Application application) {
    this.application = application;
  }

  @Provides
  @Singleton
  @AppScope
  Context providesApplicationContext() {
    return this.application;
  }

  @Provides
  @Singleton
  @AppScope
  SharedPreferences providePrivateSharedPreferences() {
    return this.application.getSharedPreferences(this.application.getPackageName(), Context.MODE_PRIVATE);
  }

  @Inject
  @Provides
  @Singleton
  @AppScope
  IApplicationSettingsManager provideApplicationSettingsManager(
      @AppScope SharedPreferences sharedPreferences,
      @Named("defaultRingtoneUri") Uri defaultRingtoneUri,
      IUriParser uriParser,
      @Named("maxAudioVolume") int maxAudioVolume) {
    return new ApplicationSettingsManager(sharedPreferences, defaultRingtoneUri, uriParser, maxAudioVolume);
  }

  @Provides
  @Singleton
  @AppScope
  AudioManager provideAudioManager() {
    return (AudioManager) this.application.getSystemService(Context.AUDIO_SERVICE);
  }

  @Provides
  @Named("maxAudioVolume")
  int provideMaxAudioStreamVolume(@AppScope AudioManager audioManager) {
    return audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
  }

  @Provides
  @Named("defaultRingtoneUri")
  Uri provideDefaultRingtoneUri() {
    return RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
  }
}
