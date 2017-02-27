package com.vinkel.remindmewheniamthere.config.di.modules;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import com.vinkel.remindmewheniamthere.AppScope;
import dagger.Module;
import dagger.Provides;
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
}
