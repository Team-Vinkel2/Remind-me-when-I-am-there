package com.vinkel.remindmewheniamthere.config.di.modules;

import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.location.LocationManager;
import android.media.AudioManager;
import android.media.RingtoneManager;
import android.net.Uri;
import com.vinkel.remindmewheniamthere.config.di.annotations.AppContext;
import com.vinkel.remindmewheniamthere.config.di.annotations.UnscopedIntentFactory;
import com.vinkel.remindmewheniamthere.providers.IntentFactory;
import com.vinkel.remindmewheniamthere.providers.base.IIntentFactory;
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
  private IIntentFactory intentFactory;

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
  AlarmManager provideAlarmManager() {
    return (AlarmManager) this.application.getSystemService(Context.ALARM_SERVICE);
  }

  @Provides
  ActivityManager provideActivityManager() {
    return (ActivityManager) this.application.getSystemService(Context.ACTIVITY_SERVICE);
  }

  @Provides
  LocationManager provideLocationManager() {
    return (LocationManager) this.application.getSystemService(Context.LOCATION_SERVICE);
  }

  @Provides
  @Named("defaultRingtoneUri")
  Uri provideDefaultRingtoneUri() {
    return RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
  }

  @SuppressWarnings("CheckStyle")
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

  @Inject
  @Provides
  @UnscopedIntentFactory
  IIntentFactory provideIntentFactoryForUncontexted(@AppContext Context appContext) {
    if(this.intentFactory == null) {
      this.intentFactory = new IntentFactory(appContext);
    }

    return this.intentFactory;
  }

}
