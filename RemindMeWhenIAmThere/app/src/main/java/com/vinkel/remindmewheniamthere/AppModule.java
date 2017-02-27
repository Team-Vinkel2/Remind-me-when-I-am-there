package com.vinkel.remindmewheniamthere;

import android.app.Application;
import android.content.Context;
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
}
